package books;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Samson Nelson
 * A00 Books
 * January 21st 2017
 * 
 */

public class BookApp {

	public static void main(String[] args) {

		String fileName = "src/books/books.csv";
//		String fileName = args[0];
		List<Book> books = Book.getList(fileName);
		
		System.out.println("Number of books read in: " + books.size() + "\n");
		Collections.sort(books);
		System.out.println("Sorted book list:");
		for (Iterator iterator = books.iterator(); iterator.hasNext();) {
			Book book = (Book) iterator.next();
			System.out.println(book);
		}
		
		System.out.println();
		
		Collections.reverse(books);
		System.out.println("Reverse order:");
		for (Iterator iterator = books.iterator(); iterator.hasNext();) {
			Book book = (Book) iterator.next();
			System.out.println(book);
		}
		
	}

}
