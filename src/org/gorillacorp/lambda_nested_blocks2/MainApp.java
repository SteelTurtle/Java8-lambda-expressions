package org.gorillacorp.lambda_nested_blocks2;

import java.util.ArrayList;
import java.util.List;

import org.gorillacorp.lambda_nested_blocks2.MainApp;
import org.gorillacorp.lambda_nested_blocks2.StringAttacher;
import org.gorillacorp.lambda_nested_blocks2.StringChanger;

public class MainApp {

	public static void main(String[] args) {

		Person person1 = new Person("Bob", 45);
		Person person2 = new Person("Rob", 56);
		Person person3 = new Person("Old", 245);
		Person person4 = new Person("Fold", 23);

		List<Person> people = new ArrayList<>();

		people.add(person1);
		people.add(person2);
		people.add(person3);
		people.add(person4);

		// The For loop create a temporary variable at each iteration representing a
		// Person object in the array list.
		// But this temporary variable is effectively the representation of a FINAL
		// object; this is why the For
		// cycle is working. Lambdas do have the same constraint of anonymous inner
		// classes: any variable declared outside
		// the lambda block must never change after it is initialized or it must be
		// explicitly declared as "final"
		for (Person person : people) {
			System.out.println(person.getName());
			new Thread(() -> System.out.println(person.getAge())).start();
		}

	}

	public final static String stringOperator(StringAttacher stringAttacher, String string1, String string2) {
		return stringAttacher.concatenateStrings(string1, string2);
	}
}

class StringChanger {
	public String getConcatenatedStrings() {

		StringAttacher stringAttacher = (s1, s2) -> {
			String resultString = s1 + s2;
			return resultString;
		};

		return MainApp.stringOperator(stringAttacher, "Hello", "Lambda");

	}
}

interface StringAttacher {
	String concatenateStrings(String string1, String string2);
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