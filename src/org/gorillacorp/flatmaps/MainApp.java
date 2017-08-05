package org.gorillacorp.flatmaps;

import java.util.ArrayList;
import java.util.List;

public class MainApp {

	public static void main(String[] args) {

		// We may want to map an object to more than one other object. In that case, we
		// do not use the
		// map() method but rather the flatMap().
		// For example, suppose we have a number of employees working for two different
		// departments, and we want to print information of all of them:

		Employee employee1 = new Employee("Bob Strutsmann", 237);
		Employee employee2 = new Employee("Frap Goldemprott", 25);
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
	}

}
