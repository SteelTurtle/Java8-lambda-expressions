package org.gorillacorp.simple_functional_interface;

public class MainApp {

	public static void main(String[] args) {
		final String string1 = "Hello, ";
		final String string2 = "is it me you're looking for?";

		// Define a lambda from the StringAttacher functional interface.
		// Notice that the "return" statement is unnecessary because it is
		// automatically inferred when (as in this case) the lambda body consists of a
		// single statement
		// operating on values of the same type, thus returning a third value of
		// the same type.
		StringAttacher stringAttacher = (s1, s2) -> s1 + s2;

		System.out.println(stringOperator(stringAttacher, string1, string2));

	}

	// A candidate method for a Lambda expression! The method operate on a
	// StringAttacher interface by using
	// the concatenateString() method of that interface. The implementation of
	// concatenateString() is
	// effectively specified in the main method, as shown above.
	public final static String stringOperator(StringAttacher stringAttacher, String string1, String string2) {
		return stringAttacher.concatenateStrings(string1, string2);
	}

}

// Simple functional interface with a method that takes two strings and
// concatenate them.
interface StringAttacher {
	String concatenateStrings(String string1, String string2);
}
