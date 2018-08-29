package LRUCaches;

import java.util.HashMap;
import java.util.LinkedList;

public class LRUGeneric <T1>{
	LinkedList<T1> listOfKeys = new LinkedList<T1>();
	HashMap<T1, T1> map = new HashMap<>();
	int capacity;

	public LRUGeneric(int capacity) {
		this.capacity = capacity;
	}

	void refer(T1 key) {
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
		for(T1 i : listOfKeys) {
			System.out.print(i + ";  ");
		}
	}
	
	public static void main(String[] args) {
		LRUGeneric lru = new LRUGeneric(5);
		lru.refer(1);
		lru.refer(false);
		lru.refer(2.5);
		lru.refer('z');
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

/*===============================================

5 минут;  z;  2.5;  false;  1;  
6 утра;  5 минут;  z;  2.5;  false;  
7 сорок;  6 утра;  5 минут;  z;  2.5;  

*/
