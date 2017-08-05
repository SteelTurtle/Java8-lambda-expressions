package org.gorillacorp.flatmaps_and_collect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainApp {

	public static void main(String[] args) {

		// We may want to map an object to more than one other object. In that case, we
		// do not use the
		// map() method but rather the flatMap().
		// For example, suppose we have a number of employees working for two different
		// departments, and we want to print information of all of them:

		Employee employee1 = new Employee("Bob Strutsmann", 237);
		Employee employee2 = new Employee("Frap Goldemprott", 23);
		Employee employee3 = new Employee("Hugh McJannitor", 23);
		Employee employee4 = new Employee("Ulricht Erbamhal", 77);
		Employee employee5 = new Employee("Saul McToksplasmosis", 57);

		Department morgue = new Department("Morgue");
		morgue.addEmployee(employee1);
		morgue.addEmployee(employee2);
		morgue.addEmployee(employee3);

		Department psychiatry = new Department("Psychiatry");
		psychiatry.addEmployee(employee4);
		psychiatry.addEmployee(employee5);

		// Let's make a list of all the departments we are interested in:
		List<Department> departments = new ArrayList<>();
		departments.add(morgue);
		departments.add(psychiatry);

		// the "mapper" accepted by the flatmap() method is a Function<> with an input
		// type of
		// "Department" and output type Stream<?>
		departments.stream().flatMap(department -> department.getEmployees().stream()).forEach(System.out::println);

		// ***********THE COLLECT (TERMINAL) METHOD*************//
		// What if instead of printing a block of strings we want to dispose of them,
		// and
		// use this list at a later stage? Well, we can use the terminal collect()
		// method that turn the Stream into a List<>
		System.out.println("***********Example of collect()**************");
		List<String> stringsList = Arrays.asList("delta", "alpha", "daphne", "zelda", "omega", "bravo", "gamma",
				"theta", "yota", "sigma", "tau", "dragon", "terra", "cleopatra", "samarcand", "aleste");

		// first implementation of the collect() method
		List<String> collectedStrings1 = stringsList.stream().map(String::toUpperCase)
				.filter(s -> s.toLowerCase().startsWith("a")).sorted().collect(Collectors.toList());

		collectedStrings1.forEach(System.out::println);

		// second implementation of the collect() method, a bit more complicated: it
		// accepts a Supplier,
		// an Accumulator in the form of a BiConsumer, and a Combiner, this also in the
		// form of Biconsumer.
		// The Supplier (1st argument) will create a new ArrayList to contain the
		// strings, the Accumulator (2nd argument)
		// will add the strings one by one to the array and the Combiner (3rd argument)
		// is an optional operation
		// that can be triggered by the JVM at RunTime in case it evaluates the
		// possibility to perform the operation itself
		// more efficiently
		System.out.println("***********Another xample of collect()**************");
		List<String> collectedStrings2 = stringsList.stream().map(String::toUpperCase)
				.filter(s -> s.toLowerCase().startsWith("d")).sorted()
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

		collectedStrings2.forEach(System.out::println);

		// ********THE GROUPBY() METHOD*************//
		System.out.println(
				"********Let's use the collector.groupBy() method to make maps ordered maps of employees (by age)******");
	}

}
