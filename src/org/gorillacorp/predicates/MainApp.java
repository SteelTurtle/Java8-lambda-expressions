package org.gorillacorp.predicates;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class MainApp {

	public static void main(String[] args) {

		Employee person1 = new Employee("Bob", 45);
		Employee person2 = new Employee("Rob", 56);
		Employee person3 = new Employee("Old", 245);
		Employee person4 = new Employee("Fold", 23);

		List<Employee> employees = new ArrayList<>();

		employees.add(person1);
		employees.add(person2);
		employees.add(person3);
		employees.add(person4);
		// Normally, the forEach() method of a list expects a Consumer<> functional
		// interface (A Consumer
		// execute some operation on the passed object T and return void)
		employees.forEach(employee -> {
			System.out.println(employee.getName());
			System.out.println(employee.getAge());
		});

		// Here we put in practice the lambda expression using a Predicate to test for
		// an employee age
		printEmployeesByAge(employees, "Employees over 50", employee -> employee.getAge() > 50);
		printEmployeesByAge(employees, "Employees below 50", employee -> employee.getAge() <= 50);
	}

	// Let's use the Predicate<Employee> functional interface to check for a
	// true/false condition (A Predicate<> act on a single object T and returns a
	// boolean value). The for-each loop in Java can be used to accept a Predicate<>
	private static void printEmployeesByAge(List<Employee> employees, String ageText,
			Predicate<Employee> ageCondition) {
		System.out.println("****************");
		System.out.println(ageText);
		System.out.println("****************");
		for (Employee employee : employees) {
			if (ageCondition.test(employee)) {
				System.out.println(employee.getName());
			}
		}
	}

}
