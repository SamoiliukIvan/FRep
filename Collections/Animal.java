package Collections;


import java.util.Iterator;

public class Animal {
// поля класса
	String name;

// конструктор
	Animal(String name) {
		this.name = name;
	}
// геттер и сеттер
	String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}
// toString
	@Override
	public String toString() {
		return "Animal name = " + name;
	}
}

