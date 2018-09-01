package LRUCaches;

import java.util.HashMap;
import java.util.Map;

public class LRU {

	class Node<String, Double> {
		// ссылка на предыдущий и последующий объекты Node
		Node<String, Double> previous;
		Node<String, Double> next;

		String key;
		Double value;

		public Node(Node<String, Double> previous, Node<String, Double> next, String key, Double value) {
			this.previous = previous;
			this.next = next;
			this.key = key;
			this.value = value;
		}
	}

	// хешмапа, принимающая в качестве параметров ключ и объект Node
	private HashMap<String, Node<String, Double>> cache;
	// более давно используемый
	private Node<String, Double> leastRecentlyUsed;
	// менее давно используемый
	private Node<String, Double> mostRecentlyUsed;

	private int capacity;
	private int currentSize;

	public LRU(int capacity) {
		this.capacity = capacity;
		currentSize = 0;
		leastRecentlyUsed = new Node<String, Double>(null, null, null, null);
		mostRecentlyUsed = leastRecentlyUsed;
		cache = new HashMap<String, Node<String, Double>>();
	}

	public Double get(String key) {
		Node<String, Double> tempNode = cache.get(key);
		if (tempNode == null) {
			return null;
		}
		// если ключ недавно использовался, возвращаем его
		else if (tempNode.key == mostRecentlyUsed.key) {
			return mostRecentlyUsed.value;
		}

		// получаем слудующий и предыдущий ноды
		Node<String, Double> nextNode = tempNode.next;
		Node<String, Double> previousNode = tempNode.previous;

		// если ключ использовался давно
		if (tempNode.key == leastRecentlyUsed.key) {
			nextNode.previous = null;
			leastRecentlyUsed = nextNode;
		}

		// если мы всередине, то обновляем соседние элементы
		else if (tempNode.key != mostRecentlyUsed.key) {
			previousNode.next = nextNode;
			nextNode.previous = previousNode;
		}

		// обновляем последний использованный нод и идущий следом
		tempNode.previous = mostRecentlyUsed;
		mostRecentlyUsed.next = tempNode;
		mostRecentlyUsed = tempNode;
		mostRecentlyUsed.next = null;

		// возвращаем искомое значение
		return tempNode.value;

	}

	public void put(String key, Double value) {

		Node<String, Double> myNode = new Node<String, Double>(mostRecentlyUsed, null, key, value);
		mostRecentlyUsed.next = myNode;
		cache.put(key, myNode);
		mostRecentlyUsed = myNode;

		// если текущий размер уже равен размеру кеша
		if (currentSize >  capacity) {
			// то удаляем наименее используемый элемент
			cache.remove(leastRecentlyUsed.key);
			// и обновляем соседние
			leastRecentlyUsed = leastRecentlyUsed.next;
			leastRecentlyUsed.previous = null;
		}

		// если же текущий размер меньше размера кеша
		else if (currentSize < capacity) {
			// и не содержит других элементов
			if (currentSize == 0) {
				// то предыдущий элемент равен переданному
				leastRecentlyUsed = myNode;
			}
			//currentSize++;
		}
	}

	void toShow() {
		int i = 1;
		for (Map.Entry<String, Node<String, Double>> entry : cache.entrySet()) {
			System.out.println(i + " место - Песня \"" + entry.getValue().key + "\"" + " : " + entry.getValue().value + " минуты.");
			++i;
		}
	}

	public static void main(String[] args) {
		LRU lru = new LRU(5);
		lru.put("1 раз в год", 2.3);
		lru.put("2 берега", 2.1);
		lru.put("3 белых коня", 3.2);
		lru.put("4 таракана и сверчок", 4.1);
		lru.put("1 раз в год", 3.5);
		
		lru.toShow();
		System.out.println();
		lru.put("1 раз в год", 4.4);
		lru.toShow();
		System.out.println();
		lru.put("7 сорок", 5.1);
		lru.toShow();
		System.out.println();
		System.out.println(lru.get("3 белых коня"));
		System.out.println(lru.get("test"));
	}
}
/*
 * ====================================================================
 * 
 * 5 минут; 4 таракана и сверчок; 3 белых коня; 2 берега; 1 раз в год; 6 утра; 5
 * минут; 4 таракана и сверчок; 3 белых коня; 2 берега; 7 сорок; 6 утра; 5
 * минут; 4 таракана и сверчок; 3 белых коня;
 * 
 */
