package org.gorillacorp.simple_lambda_thread;

import java.util.ArrayList;
import java.util.List;

public class MainApp {

	public static void main(String[] args) {
		// Instantiate and start a simple thread with a lambda expression
		new Thread(() -> {
			System.out.println("This is a Runnable, Java8 style!");
			System.out.println("...and another line...");
			System.out.println("...and another one!");
		}).start();

		// Another funny experiment: create a class and an array list of class objects.
		// Then, instantiate and run "on the fly" the Comparator object specified by the
		// sort() method of the array list and iterate through the array list to get the
		// name of each person
		Person person1 = new Person("Bob", 45);
		Person person2 = new Person("Rob", 56);
		Person person3 = new Person("Old", 245);
		Person person4 = new Person("Fold", 23);

		List<Person> people = new ArrayList<>();

		people.add(person1);
		people.add(person2);
		people.add(person3);
		people.add(person4);

		people.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
		people.forEach(p -> System.out.println(p.getName()));

	}
}

class Person {
	private String name;
	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

}