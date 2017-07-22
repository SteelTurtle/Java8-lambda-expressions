package org.gorillacorp.lambda_nested_blocks;

// Refer to the package "simple_fuctional_interface" for further information about the code down here
public class MainApp {

	public static void main(String[] args) {

		StringChanger stringChanger = new StringChanger();
		String stringResult = stringChanger.getConcatenatedStrings();
		System.out.println(stringResult);

	}

	public final static String stringOperator(StringAttacher stringAttacher, String string1, String string2) {
		return stringAttacher.concatenateStrings(string1, string2);
	}
}

class StringChanger {
	public String getConcatenatedStrings() {
		/*
		 * METHOD ONE: DEFINE AN ANONYMOUS INNER CLASS
		 * 
		 * return MainApp.stringOperator(new StringAttacher() {
		 * 
		 * @Override public String concatenateStrings(String string1, String string2) {
		 * return string1.toUpperCase() + string2.toUpperCase(); } }, "string1",
		 * "string2");
		 */

		// METHOD TWO: LAMBDA
		// Notice that the lambda is used by the JVM as a simple nested block of code
		// inside the StringChanger class;
		// this can be easily verified by retrieving at run time the name of the Lambda
		// code block
		StringAttacher stringAttacher = (s1, s2) -> {
			System.out.println("The Lambda expression class is: " + getClass().getSimpleName());
			String resultString = s1 + s2;
			return resultString;
		};

		System.out.println("The StringChanger class name is: " + getClass().getSimpleName());
		return MainApp.stringOperator(stringAttacher, "Hello", "Lambda");

	}
}

interface StringAttacher {
	String concatenateStrings(String string1, String string2);
}