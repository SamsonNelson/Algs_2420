package wordNet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;

public class Outcast {

	public Outcast(WordNet wordnet) {

	}

	public String outcast(String[] nouns) {
		return null;
	}

	public static void main(String[] args) {
		WordNet wordnet = new WordNet(args[0], args[1]);
		Outcast outcast = new Outcast(wordnet);
		for (int t = 2; t < args.length; t++) {
			In in = new In(args[t]);
			String[] nouns = in.readAllStrings();
			StdOut.println(args[t] + ": " + outcast.outcast(nouns));
		}
	}

}