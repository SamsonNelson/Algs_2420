package ElementSymbolTable;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class Main {

	public static void main(String[] args) {

		ArrayST<String, String> st = new ArrayST<String, String>();

		// reading in file
		In in = new In(
				"/Users/samsonnelson/Documents/WorkSpace_algs4_Spring_2017/Exercises/src/ElementSymbolTable/Terms.txt");

		while (in.hasNextLine()) {
			String line = in.readLine();
			String[] tokens = line.split("-");

			String key = tokens[0];
			String val = tokens[1];
			st.put(key, val);
		}

		StdOut.println("Enter Word: ");
		String word = StdIn.readString();
		
		for (String s : st.keys()) {
			if (s.equals(word)) {
				StdOut.println("Definition - " + st.get(s));
			}
		}
	}

}
