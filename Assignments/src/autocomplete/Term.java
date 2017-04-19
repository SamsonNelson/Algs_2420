package autocomplete;
/**
 * Samson Nelson
 * A03 Autocomplete
 * March 4th 2017
 * 
 */

import java.util.Comparator;

public class Term implements Comparable<Term> {
	private String query;
	private double weight;

	// Initialize a term with the given query string and weight.
	public Term(String query, double weight) {
		this.query = query;
		this.weight = weight;
	}

	// Compare the terms in descending order by weight.
	public static Comparator<Term> byReverseWeightOrder() {
		return new Comparator<Term>() {

			@Override
			public int compare(Term o1, Term o2) {
				return (int) (o1.weight - o2.weight);
			}
		};
	}

	// Compare the terms in lexicographic order but using only the first r
	// characters of each query.
	public static Comparator<Term> byPrefixOrder(int r) {
		return new Comparator<Term>() {

			@Override
			public int compare(Term o1, Term o2) {
				return o1.query.substring(0,r).compareToIgnoreCase(o2.query.substring(0,r));
//				if (queryCompareTo == 0) {
//					return (int) (o1.weight - o2.weight);
//				}
//				return queryCompareTo;
			}
		};
	}

	// Compare the terms in lexicographic order by query.
	@Override
	public int compareTo(Term that) {
		return query.compareToIgnoreCase(that.query);
//		if (queryCompareTo == 0) {
//			return (int) (weight - that.weight);
//		}
//		return queryCompareTo;
	}

	// Return a string representation of the term in the following format:
	// the weight, followed by a tab, followed by the query.
	@Override
	public String toString() {
		return String.format("Query: %s Weight: %.0f", query, weight);
	}

	public String query() {
		return query;
	}
	
	public static void main(String[] args) {
		Term term1 = new Term("Shanghai, China", 14608512);
		Term term1a = new Term("Shanghai, China", 146);
		Term term2 = new Term("Buenos Aires, Argentina", 13076300);
		Term term3 = new Term("Shanghello, China", 00000000);
		Term term4 = new Term("Aaenas Noches, Argentina", 99999999);

		System.out.printf("Shanghai compareTo Buenous = %s\n", "Shanghai".compareTo("Buenos"));
		System.out.printf("Shanghai compareTo shanghai = %s\n", "Shanghai".compareToIgnoreCase("shanghai"));
		System.out.printf("Buenos compareTo Shangai = %s\n", "Buenos".compareTo("Shanghai"));

		System.out.printf("\ncompareTo\n");
		System.out.printf("%s compareTo %s = %d\n", term1, term1, term1.compareTo(term1));
		System.out.printf("%s compareTo %s = %d\n", term1, term1a, term1.compareTo(term1a));
		System.out.printf("%s compareTo %s = %d\n", term1, term2, term1.compareTo(term2));
		System.out.printf("%s compareTo %s = %d\n", term2, term1, term2.compareTo(term1));
		System.out.printf("%s compareTo %s = %d\n", term2, term2, term2.compareTo(term2));

		System.out.printf("\nbyReverseWeightOrder\n");
		System.out.printf("%s byReverseWeightOrder %s = %d\n", term1, term1, Term.byReverseWeightOrder().compare(term1, term1));
		System.out.printf("%s byReverseWeightOrder %s = %d\n", term1, term2, Term.byReverseWeightOrder().compare(term1, term2));
		System.out.printf("%s byReverseWeightOrder %s = %d\n", term2, term1, Term.byReverseWeightOrder().compare(term2, term1));
		System.out.printf("%s byReverseWeightOrder %s = %d\n", term2, term2, Term.byReverseWeightOrder().compare(term2, term2));

		System.out.printf("\nbyPrefixOrder\n");
		System.out.printf("%s byPrefixOrder %s = %d\n", term1, term2, Term.byPrefixOrder(5).compare(term1, term2));
		System.out.printf("%s byPrefixOrder %s = %d\n", term1, term3, Term.byPrefixOrder(9).compare(term1, term3));
		System.out.printf("%s byPrefixOrder %s = %d\n", term2, term4, Term.byPrefixOrder(5).compare(term2, term4));
	}

}
