package autocomplete;

import java.util.Comparator;

public class Term implements Comparable<Term> {

	// Initialize a term with the given query string and weight.
	public Term(String query, double weight) {

	}

	// Compare the terms in descending order by weight.
	public static Comparator<Term> byReverseWeightOrder() {
		return null;

	}

	// Compare the terms in lexicographic order but using only the first r
	// characters of each query.
	public static Comparator<Term> byPrefixOrder(int r) {
		return null;

	}

	// Compare the terms in lexicographic order by query.
	@Override
	public int compareTo(Term that) {
		return 0;

	}

	// Return a string representation of the term in the following format:
	// the weight, followed by a tab, followed by the query.
	@Override
	public String toString() {
		return null;

	}
}
