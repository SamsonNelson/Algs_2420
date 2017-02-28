package queue;

import java.util.Iterator;
/**
 * Samson Nelson
 */

import edu.princeton.cs.introcs.StdIn;

public class Subset {

	public static void main(String[] args) {

		int numberOfInput = Integer.parseInt(args[0]);
		System.out.println(numberOfInput);

		RandomziedQueue rq = new RandomziedQueue<>();

		// Type in strings that you want to be read according to args[0]
		for (int i = 0; i < numberOfInput; i++) {
			rq.enqueue(StdIn.readString());
		}
		printList(rq.iterator());
	}

	private static void printList(Iterator iterator) {
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		System.out.println("Finished printing list \n");
	}
}
