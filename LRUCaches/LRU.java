package LRUCaches;

import java.util.HashMap;
import java.util.LinkedList;

public class LRU {
// список для хранения ключей кеша
	private LinkedList<String> listOfKeys = new LinkedList<String>();

	private HashMap<String, String> map = new HashMap<>();
	private int capacity;

	public LRU(int capacity) {
		this.capacity = capacity;
	}

	void refer(String key) {
		if (map.containsKey(key) == false) {
			if (listOfKeys.size() == capacity) {
				listOfKeys.removeLast();
			}
		} else {
			listOfKeys.remove(map.get(key));
		}

		listOfKeys.addFirst(key);
		map.replace(key, listOfKeys.getFirst());
	}
	
	void display() {
		for(String i : listOfKeys) {
			System.out.print(i + ";  ");
		}
	}
	
	public static void main(String[] args) {
		LRU lru = new LRU(5);
		lru.refer("1 раз в год");
		lru.refer("2 берега");
		lru.refer("3 белых коня");
		lru.refer("4 таракана и сверчок");
		lru.refer("5 минут");
		lru.display();
		System.out.println();
		lru.refer("6 утра");
		lru.display();
		System.out.println();
		lru.refer("7 сорок");
		lru.display();
	}
}
/* ====================================================================

5 минут;  4 таракана и сверчок;  3 белых коня;  2 берега;  1 раз в год;  
6 утра;  5 минут;  4 таракана и сверчок;  3 белых коня;  2 берега;  
7 сорок;  6 утра;  5 минут;  4 таракана и сверчок;  3 белых коня; 
 
*/

