package LRUCaches;

import java.util.LinkedHashMap;
import java.util.Map;
//=================================================================
public class AtternativeLRU {

	private LinkedHashMap<String, Double> map;
	private int capacity;
	
	public LinkedHashMap<String, Double> getMap() {
		return map;
	}

// при вызове метода put атоматически удаляется самый старый элемент
	public AtternativeLRU(int capacity) {
		this.capacity = capacity;
		map = new LinkedHashMap<String, Double>(capacity) {
			protected boolean removeEldestEntry(Map.Entry<String, Double> eldest) {
				return size() > capacity;
			}
		};
	}

	void toShow() {
		int i = 1;
	for(Map.Entry<String, Double> entry : map.entrySet()) {
		System.out.println(i + " место - Песня \"" + entry.getKey() + "\"" +  " : " + entry.getValue() + " минуты." );
		++i;
	}
}

// переопределяем методы hashCode() и equals()
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + capacity;
		result = prime * result + ((map == null) ? 0 : map.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AtternativeLRU other = (AtternativeLRU) obj;
		if (capacity != other.capacity)
			return false;
		if (map == null) {
			if (other.map != null)
				return false;
		} else if (!map.equals(other.map))
			return false;
		return true;
	}
//--------------------------------------------------------------	
public static void main(String[] args) {
		
		AtternativeLRU lru = new AtternativeLRU(5);
		
// проверяем на вставку пока кеш не заполнен
		lru.getMap().put("1 раз в год", 2.3);
		lru.getMap().put("2 берега", 2.1);
		lru.getMap().put("3 белых коня", 2.5);
		lru.getMap().put("4 таракана и сверчок", 3.15);
		lru.getMap().put("5 минут", 4.5);
		lru.toShow(); 	// вывод на консоль
		
		System.out.println();
		
// проверяем на вставку и ротацию, когда кеш заполнен
		lru.getMap().put("6 утра", 3.45);
		lru.getMap().put("7 сорок", 4.2);
		lru.toShow();	// вывод на консоль
		
		System.out.println();
		
// проверяем на получение значения по ключу
		System.out.println(lru.getMap().get("7 сорок"));
		System.out.println(lru.getMap().get("авп"));
	}
}

/*===============консоль======================
 
1 место - Песня "1 раз в год" : 2.3 минуты.
2 место - Песня "2 берега" : 2.1 минуты.
3 место - Песня "3 белых коня" : 2.5 минуты.
4 место - Песня "4 таракана и сверчок" : 3.15 минуты.
5 место - Песня "5 минут" : 4.5 минуты.

1 место - Песня "3 белых коня" : 2.5 минуты.
2 место - Песня "4 таракана и сверчок" : 3.15 минуты.
3 место - Песня "5 минут" : 4.5 минуты.
4 место - Песня "6 утра" : 3.45 минуты.
5 место - Песня "7 сорок" : 4.2 минуты.

4.2
null

 */





