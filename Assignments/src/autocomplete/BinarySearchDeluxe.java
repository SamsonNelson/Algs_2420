package autocomplete;
/**
 * Samson Nelson
 * A03 Autocomplete
 * March 4th 2017
 * 
 */

import java.util.Arrays;
import java.util.Comparator;

public class BinarySearchDeluxe {

	// Return the index of the first key in a[] that equals the search key, or
	// -1 if no such key.
	public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
		int lo = 0;
		int hi = a.length - 1;
		int startIndex = -1;
		while (lo <= hi) {
			// Key is in a[lo..hi] or not present.
			int mid = lo + (hi - lo) / 2;
			if (comparator.compare(key, a[mid]) < 0)
				hi = mid - 1;
			else if (comparator.compare(key, a[mid]) > 0)
				lo = mid + 1;
			else {
				startIndex = mid;
				hi = mid - 1;
			}
		}
		return startIndex;
	}

	// Return the index of the last key in a[] that equals the search key, or -1
	// if no such key.
	public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
		int lo = 0;
		int hi = a.length - 1;
		int lastIndex = -1;
		while (lo <= hi) {
			// Key is in a[lo..hi] or not present.
			int mid = lo + (hi - lo) / 2;
			if (comparator.compare(key, a[mid]) < 0)
				hi = mid - 1;
			else if (comparator.compare(key, a[mid]) > 0)
				lo = mid + 1;
			else {
				lastIndex = mid;
				lo = mid + 1;
			}
		}
		return lastIndex;
	}

	public static void main(String[] args) {
		String[] list = new String[] { "a", "b", "c", "c", "c", "d", "e", "f", "g" };
		Arrays.sort(list);
		int firstTermIndex = firstIndexOf(list, "c", String.CASE_INSENSITIVE_ORDER);
		System.out.printf("key = %s found firstIndexOf %s\n", "c", firstTermIndex);
		int lastTermIndex = lastIndexOf(list, "c", String.CASE_INSENSITIVE_ORDER);
		System.out.printf("key = %s found lastIndexOf %s\n", "c", lastTermIndex);

		 Term[] terms = new Term[]{new Term("Shanghai", 14608512), 
				 new Term("Buenos Aires", 91874334),
				 new Term("Buenos Noches", 9180000),
				 new Term("Shangrella", 14608000),
				 new Term("BuenAs NochAs", 9180099),
				 new Term("ShanHello", 14608099),
		 };
		 Arrays.sort(terms);
		
		 Term key = new Term("buen", Double.MIN_VALUE);
		 firstTermIndex = firstIndexOf(terms, key, Term.byPrefixOrder(key.query().length()));
		 lastTermIndex = lastIndexOf(terms, key, Term.byPrefixOrder(key.query().length()));
		 System.out.printf("key = %s found at First Index %s and Last Index %s\n", key.query(), firstTermIndex, lastTermIndex);
		 
		 System.out.println();
		 
		 Term key1 = new Term("Shang", Double.MIN_VALUE);
		 firstTermIndex = firstIndexOf(terms, key1, Term.byPrefixOrder(key1.query().length()));
		 lastTermIndex = lastIndexOf(terms, key1, Term.byPrefixOrder(key1.query().length()));
		 System.out.printf("key = %s found at First Index %s and Last Index %s\n", key1.query(), firstTermIndex, lastTermIndex);
	}
}
