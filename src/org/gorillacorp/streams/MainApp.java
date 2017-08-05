package org.gorillacorp.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MainApp {

	public static void main(String[] args) {
		List<String> stringsList = Arrays.asList("delta", "alpha", "daphne", "zelda", "omega", "bravo", "gamma",
				"theta", "yota", "sigma", "tau", "dragon", "terra", "cleopatra", "samarcand", "aleste");

		// Let's take a subset of the original string list and operate on it with
		// streams...
		List<String> dStrings = new ArrayList<>();

		// *****SORTING AND PRINTING: THE VERBOSE WAY WITHOUT STREAMS********//
		stringsList.forEach(s -> {
			if (s.toLowerCase().startsWith("d")) {
				// You may not notice, but always keep in mind the scoping rules of lambdas
				// normally prohibit to use a non-final, in-scope, variables to be used inside
				// the lambda
				// expression.
				// Now, the dStrings List<> is not declared "final", but actually we are never
				// changing its content from within the lambda; we just add objects references
				// of type String to it and Strings are always
				// effectively "final".
				// This is why this operation is permitted...
				dStrings.add(s);
			}
		});

		// ...then, let's sort and print the dStrings list
		dStrings.sort((s1, s2) -> s1.compareTo(s2));
		dStrings.forEach(s -> System.out.println(s));

		// **************************************************************//
		// *****SORTING AND PRINTING: THE RIGHT WAY WITH STREAMS********//
		// A Stream is a sequence of generic objects supporting sequential and parallel
		// aggregate operations. Streams can be chained to many different operations,
		// but they must
		// comply with two requirements: these operations must not interfere with each
		// other by changing the stream's object source during the various
		// transformations,
		// AND the transformation operations must be stateless and do not depend on
		// values specified in previous steps
		// of the transformations' chain
		System.out.println("*****Same thing, using streams this time*******");
		dStrings.stream().map(String::toUpperCase).filter(s -> s.toLowerCase().startsWith("d")).sorted()
				.forEach(System.out::println);

		// ****CREATING STREAMS FROM OTHER OBJECTS****//
		// We can also create (and concatenate) Stream like this:
		System.out.println("*************Stream.of and concatenations***********");
		Stream<String> ioCoordinates = Stream.of("A34", "S56", "R09", "P99", "F81", "Z23", "E42");
		Stream<String> inCoordinates = Stream.of("I58", "P99", "M32", "V12", "G06", "W08", "Z23");
		Stream<String> concatenatedStream = Stream.concat(ioCoordinates, inCoordinates);
		// Notice the use of peek(), using a Consumer and "delaying" the terminal
		// operation count() so
		// we can print the distinct elements of the concatenatedStream BEFORE the
		// distinct count number.
		System.out.println("Total DISTINCT elements count in two streams: "
				+ concatenatedStream.distinct().peek(System.out::println).count());

	}
}
