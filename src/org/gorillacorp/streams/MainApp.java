package org.gorillacorp.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		// other by changing the stream's object source during the various transformations,
		// AND the transformation operations must be stateless and do not depend on
		// values specified in previous steps
		// of the transformations' chain
		System.out.println("//*****Same thing, using streams this time*******//");
		dStrings.stream().map(String::toUpperCase).filter(s -> s.toLowerCase().startsWith("d")).sorted()
				.forEach(System.out::println);
	}
}
