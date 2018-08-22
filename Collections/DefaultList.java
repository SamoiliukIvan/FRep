package Collections;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DefaultList implements MyList, ListIterable {
	// поле класса
		ArrayList<Object> list = new ArrayList<Object>();

	// конструктор
		public DefaultList() {
		}

	// внутренний класс IteratorImpl
		private class IteratorImpl implements Iterator<Object> {

			IteratorImpl() {
				index++;
			}

	// поля внутреннего класса
			int index = 0;
			private boolean check = false;

			public boolean hasNext() {
				if (index > 0) {
					System.out.println("Next element exists");
					return true;
				}
				return false;
			}

			public Object next() {
				if (hasNext()) {
					check = true;
					System.out.println("Element selects");
					return this;
				}
				throw new NoSuchElementException();
			}

			public void remove() {
				if (check == true) {
					index--;
					// ((IteratorImpl) next()).remove();
					// как удалить элемент???
					check = false; // после выполнения метода remove() флаг меняется на false
					System.out.println("Element removed");
				} else if (check == false) { // если прежде был метод remove() или не было метода next(), то флаг равен
												// false
					throw new IllegalStateException();
				}
			}
		}

	// внутренний класс ListIteratorImpl
		public class ListIteratorImpl extends IteratorImpl implements ListIterator {
			ListIteratorImpl() {
				index++;
			}
			int index = 0;
			private boolean check = false;
			
//			@Override
//			public boolean hasNext() {
//				if (index > 0) {
//					System.out.println("Next element exists");
//					return true;
//				}
//				return false;
//			}
	//
//			@Override
//			public Object next() {
//				if (hasNext()) {
//					check = true;
//					System.out.println("Element selects");
//					return this;
//				}
//				throw new NoSuchElementException();
//			}

			@Override
			public boolean hasPrevious() {
				if (index > 0) {
					System.out.println("Previous element exists");
					return true;
				}
				return false;
			}

			@Override
			public Object previous() {
				if (hasPrevious()) {
					check = true;
					System.out.println("Element selects");
					return this;
				}
				throw new NoSuchElementException();
			}

			@Override
			public void set(Object e) {
				if (check == true) {
					((ListIteratorImpl) previous()).set(e);
					check = false; // после выполнения метода remove() флаг меняется на false
					System.out.println("The new value is set");
				} else if (check == false) { // если прежде был метод remove() или не было метода next(), то флаг равен
												// false
					throw new IllegalStateException();
				}
			}

			@Override
			public void remove() {
				if (check == true) {
					index--;
					// list.remove(this.next());
					// как удалить элемент???
					check = false; // после выполнения метода remove() флаг меняется на false
					System.out.println("Element removed");
				} else if (check == false) { // если прежде был метод remove() или не было метода next(), то флаг равен
												// false
					throw new IllegalStateException();
				}
			}
		}

		@Override
		public Iterator<Object> iterator() {

			return new IteratorImpl();
		}

		@Override
		public ListIterator listIterator() {
			return new ListIteratorImpl();
		}
	}

