package Collections;

import java.util.Iterator;

public class DefaultListRunner {

	public static void main(String[] args) {

		String element1 = "First";
		Integer element2 = 2;
		Animal element3 = new Animal("Third");

		DefaultList test = new DefaultList();

		test.list.add(element1);
		test.list.add(element2);
		test.list.add(element3);

		Iterator<Object> iterator = test.iterator();
		ListIterator listIterator = test.listIterator();
		for (Object o : test.list) {
			System.out.println(o.toString());
		}
		
		iterator.hasNext();
		iterator.next();
		
		for (Object o : test.list) {
			System.out.println(o.toString());
		}
		

		Iterator<Object> it = test.list.iterator();
		while (it.hasNext())
		System.out.println(it.next());
		
		listIterator.hasPrevious();
		listIterator.hasPrevious();
		listIterator.hasPrevious();
		listIterator.hasPrevious();
		listIterator.hasPrevious();
	}
}
