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
		return getTitle().compareTo(o.getTitle());
	}	
	
}