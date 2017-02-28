package books;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.introcs.In;

/**
 * 
 * @author Samson Nelson
 * A00 Books
 * January 21st 2017
 * 
 */

// Year and Author

public class Book implements Comparable<Book> {
	
	private String title;
	private String author;
	private int year;
	
	public Book(String title, String author, int year) {
		this.title = title;
		this.author = author;
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public int getYear() {
		return year;
	}

	@Override
	public String toString() {
		return title + " by " + author + " (" + year + ")";
	}
	
//	@Override
//	public String toString() {
//		return "(" + year + ") " + title + " by " + author;
//	}
	
	public static List<Book> getList (String filename) {
		List<Book> books = new ArrayList();
		In in = new In(filename);
		while (in.hasNextLine()) {

			String line = in.readLine();
			try {
				String[] entry = line.split(",");
				Book book = new Book(entry[0], entry[1], Integer.parseInt(entry[2]));
				books.add(book);
			} catch (Exception e) {
				System.out.println("Problem reading line: " + line);
			}
		}
		return books;
	}

	@Override
	public int compareTo(Book o) {
//		 return Comparators.NAME.compare(this, o);
		return getTitle().compareTo(o.getTitle());
	}	
	
//	public static Comparator<Book> NAME = new Comparator<Book>();
//	 @Override
//     public int compareToAuthor(Book o) {
//			return getAuthor().compareTo(o.getAuthor());
//     }
	
	// http://stackoverflow.com/questions/14154127/collections-sortlistt-comparator-super-t-method-example
	
	
	
	// How do you compare by Author and Year in a generic way?
	
//	@Override
//	public int compareTo(Book o) {
//		return getAuthor().compareTo(o.getAuthor());
//	}
	
//	@Override
//	public int compareTo(Book o) {
//		return o.getYear() - o.getYear();
//	}
	
}
