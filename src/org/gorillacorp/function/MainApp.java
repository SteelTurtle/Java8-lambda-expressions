package org.gorillacorp.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class MainApp {

	public static void main(String[] args) {

		Employee person1 = new Employee("Bob McAllister", 45);
		Employee person2 = new Employee("Rob Cancer", 56);
		Employee person3 = new Employee("Old Fart", 245);
		Employee person4 = new Employee("Fold Frappington", 23);

		List<Employee> employees = new ArrayList<>();

		employees.add(person1);
		employees.add(person2);
		employees.add(person3);
		employees.add(person4);

		// *********FUNCTION INTERFACE**********
		// A Function<> Interface accepts an input value type 'T' and a result value
		// type 'R'
		// It is extremely useful to instantiate 'on the fly' utility methods that
		// can be called as lambdas and do not require us to create a class or an
		// interface
		// to define them.
		// Hey, C function pointers, anyone?....
		// Notice that (as usual) we do not need curly brackets and the 'return'
		// statement
		// within the lambda because we are returning a String...
		Function<Employee, String> getEmployeeSurname = (employee) -> employee.getName()
				.substring(employee.getName().indexOf(' ') + 1);

		String employee4Surname = getEmployeeSurname.apply(employees.get(3));
		System.out.println(employee4Surname);

		System.out.println("***********FUNCTION<> part 2****************");
		// Or we can use a Function<> in a more canonical way for a lambda, as an
		// argument of a method:
		Function<Employee, String> getEmployeeFirstName = (employee) -> employee.getName().substring(0,
				employee.getName().indexOf(' '));
		Random random = new Random();
		for (Employee employee : employees) {
			// randomly decide to print or not the employee name
			if (random.nextBoolean()) {
				System.out.println(getNameOfEmployee(employee, getEmployeeFirstName));
			}
			// ...or if the test does not pass let's randomly print the surname of the
			// employees
			else {
				System.out.println(getNameOfEmployee(employee, getEmployeeSurname));
			}

		}

	}

	private static String getNameOfEmployee(Employee employee, Function<Employee, String> nameFunction) {
		return nameFunction.apply(employee);
	}

}
