package queue;

import java.util.Iterator;
/**
 * Samson Nelson
 */
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private Node<Item> first; // top of stack
	private Node<Item> last; // end of stack
	private int n; // size of the stack

	// helper linked list class
	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
		private Node prev;
	}

	/**
	 * Initializes an empty stack.
	 */
	public Deque() {
		first = null;
		n = 0;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return n;
	}

	// add the item to the beginning list
	public void addFirst(Item item) {
		if (item == null) {
			throw new NullPointerException("Item cannot be null");
		}
		Node<Item> oldfirst = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldfirst;
		if (oldfirst != null) {
			oldfirst.prev = first;
		}
		if (last == null) {
			last = first;
		}
		n++;
	}

	// add the item to the end of the list
	public void addLast(Item item) {
		if (item == null) {
			throw new NullPointerException("Item cannot be null");
		}
		Node<Item> oldLast = last;
		last = new Node<Item>();
		last.item = item;
		last.prev = oldLast;
		oldLast.next = last;
		if (first == null) {
			first = last;
		}
		n++;
	}

	// remove item at head of list
	public Item removeFirst() {
		if (isEmpty())
			throw new NoSuchElementException("Deque underflow");
		Item item = first.item; // save item to return
		first = first.next; // delete first node
		if (first != null) {
			first.prev = null;
		}
		n--;
		return item; // return the saved item
	}

	// remove item at tail of list
	public Item removeLast() {
		if (isEmpty())
			throw new NoSuchElementException("Deque underflow");
		Item item = last.item; // save item to return
		last = last.prev;
		if (last != null) {
			last.next = null;
		}
		n--;
		return item; // return the saved item
	}

	/**
     * Returns an iterator to this stack that iterates through the items in LIFO order.
     *
     * @return an iterator to this stack that iterates through the items in LIFO order
     */
    @Override
	public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        @Override
		public boolean hasNext() {
            return current != null;
        }

        @Override
		public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
		public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
    
    public static void main(String[] args) {

	    Deque d = new Deque();
        System.out.println("Is the List Empty " + d.isEmpty());
        d.addFirst("A");
        d.addFirst("B");
        printList(d.iterator());
        System.out.println("Removing " + d.removeFirst()); 
        printList(d.iterator());
        d.addLast("C");
        d.addLast("D");
        printList(d.iterator());
        System.out.println("Removing " + d.removeLast()); 
        printList(d.iterator());

       }
    
    private static void printList(Iterator iterator) {
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		System.out.println("Finished printing list \n");
	}
}
