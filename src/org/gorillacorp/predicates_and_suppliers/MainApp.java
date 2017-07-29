package org.gorillacorp.predicates_and_suppliers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

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

		// *****CONSUMER INTERFACE*******
		// Normally, the forEach() method of a list expects a Consumer<> functional
		// interface (A Consumer
		// execute some operation on the passed object T and return void)
		employees.forEach(employee -> {
			System.out.println(employee.getName());
			System.out.println(employee.getAge());
		});

		// ********PREDICATE INTERFACE*********
		// Here we put in practice the lambda expression using a Predicate to test for
		// an employee age
		printEmployeesByAge(employees, "Employees over 50", employee -> employee.getAge() > 50);
		printEmployeesByAge(employees, "Employees below 50", employee -> employee.getAge() <= 50);

		// Another simple example about how to use Predicates... Here is an IntPredicate
		// checking integer values
		System.out.println("*****An IntPredicate example******");
		IntPredicate intPredicate1 = number -> number > 25;
		// Will print 'false'...
		System.out.println(intPredicate1.test(12));

		// Predicates can also be chained together:
		IntPredicate intPredicate2 = number -> number < 76;
		// basically: 'test if 18 is both greater than 25 AND less than 76'
		System.out.println(intPredicate1.and(intPredicate2).test(18));

		// ********SUPPLIER INTERFACE*********
		// A Supplier<> functional interface does not accept any argument, but returns a
		// value at the end of its operations. There are also more specialized Suppliers
		// for
		// specific value types (boolean, double, long). See Java documentation for mere
		// information
		// Example:
		System.out.println("********A Supplier Interface example*****");
		Random random = new Random();
		Supplier<Integer> randomSupplier = () -> random.nextInt(1000);
		for (int i = 0; i < 10; i++) {
			System.out.println(randomSupplier.get());
		}
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
