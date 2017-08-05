package org.gorillacorp.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
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
		Function<Employee, String> getEmployeeSurname = employee -> employee.getName()
				.substring(employee.getName().indexOf(' ') + 1);

		String employee4Surname = getEmployeeSurname.apply(employees.get(3));
		System.out.println(employee4Surname);

		System.out.println("***********FUNCTION<> part 2****************");
		// Or we can use a Function<> in a more canonical way for a lambda, as an
		// argument of a method:
		Function<Employee, String> getEmployeeFirstName = employee -> employee.getName().substring(0,
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

		System.out.println("*************CHAINING FUNCTION<> TOGETHER*********");
		// There is no limit to the possibility to chain Functions:
		// when chaining Functions, each Function produces a result consumed by the
		// subsequent Function
		// Chaining is done through the 'andThen()' method.
		// Let's say we want to print in upper-case the the name of each
		// employee. and then get the first name only:
		Function<Employee, String> fullNameToUppercase = employee -> employee.getName().toUpperCase();
		Function<String, String> getFirstname = name -> name.substring(0, name.indexOf(' '));
		Function<Employee, String> cutAndGetString = fullNameToUppercase.andThen(getFirstname);
		System.out.println(cutAndGetString.apply(employees.get(0)));

		// For more articulated operations, we can use BiFunction<> interface, which
		// concatenates different
		// functions in an arbitrary order. Example: we want to get the name and the age
		// of an employee; this
		// means we need two input parameters (the employee object's upper-case name(T)
		// and the employee age (U))
		BiFunction<String, Employee, String> getFullNameToUpperCaseAndThenGetAge = (fullName, employee) -> fullName
				.concat(" " + employee.getAge());
		System.out.println("************BiFunction<> example**************");
		System.out.println(getFullNameToUpperCaseAndThenGetAge.apply(employees.get(3).getName(), employees.get(3)));
		// But... how to concatenate BiFunctions<> with Functions<>? Well, we cannot because the Function<>
		// do expect exactly ONE argument as input. So, the only possibility to concatenate BiFuction<> and
		// Function<> is when he BiFunction<> is the very first lambda in the stream of operations: 
	}

	private static String getNameOfEmployee(Employee employee, Function<Employee, String> nameFunction) {
		return nameFunction.apply(employee);
	}

}
