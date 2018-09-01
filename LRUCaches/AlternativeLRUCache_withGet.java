package LRUCaches;

import java.util.LinkedHashMap;
import java.util.Map;

//=================================================================
class LRUMap<K, V> extends LinkedHashMap<K, V> {
	// поле для capacity
	private int capacity;

	// конструктор
	public LRUMap(int capacity) {
		super(capacity);
		this.capacity = capacity;
	}

	// автоматически удаляем самую старую запись
	@Override
	protected boolean removeEldestEntry(Map.Entry eldestKey) {
		return size() > capacity;
	}
}

//=================================================================
public class AlternativeLRUCache_withGet {
	// объект класса LRUMap
	LRUMap<String, Integer> map;

	// конструктор
	public AlternativeLRUCache_withGet(int capacity) {
		map = new LRUMap<String, Integer>(capacity);
	}

	// получаем значение по ключу
	public int get(String key) {
		// если мапа пустая или у ключа нет значения, возвращаем -1
		if (map == null || map.get(key) == null) {
			return -1;
		}
		// если мапа не пустая и у ключа есть значение,
		// то получаем значение, соответствующее ключу
		int value = map.get(key);
		// обновляем запись с теми же ключом и значением -
		// теперь запись становится самой новой
		map.put(key, value);
		// возвращаем значение
		return value;
	}

	public void put(String key, Integer value) {
		// если мапа не инициализирована объектом
		if (map == null) {
			System.err.println("Мапа не инициализирована");
			return;
		}
		this.get(key);
		map.put(key, value);
	}

	void toShow() {
		int i = 1;
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println(i + " место - Песня \"" + entry.getKey() + "\"" + " : " + entry.getValue() + " минуты.");
			++i;
		}
	}

//-----------------------------------------------------------------
	public static void main(String[] args) {
		AlternativeLRUCache_withGet lru2 = new AlternativeLRUCache_withGet(3);

		// проверяем на вставку пока кеш не заполнен
		lru2.put("1 раз в год", 2);
		lru2.put("2 берега", 2);
		lru2.put("3 белых коня", 2);
		lru2.put("4 таракана и сверчок", 3);
		lru2.put("5 минут", 4);
		lru2.toShow(); // вывод на консоль

		System.out.println();

		// проверяем на вставку и ротацию, когда кеш заполнен
		lru2.put("6 утра", 3);
		lru2.put("7 сорок", 4);
		lru2.toShow(); // вывод на консоль

		System.out.println();

		// проверяем на получение значения по ключу
		System.out.println(lru2.get("7 сорок"));
		System.out.println(lru2.get("авп"));
	}
}
/*===============консоль======================
 
1 место - Песня "3 белых коня" : 2 минуты.
2 место - Песня "4 таракана и сверчок" : 3 минуты.
3 место - Песня "5 минут" : 4 минуты.

1 место - Песня "5 минут" : 4 минуты.
2 место - Песня "6 утра" : 3 минуты.
3 место - Песня "7 сорок" : 4 минуты.

4
-1

*/

