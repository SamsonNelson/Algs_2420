package queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.introcs.StdRandom;

/**
 * Samson Nelson
 */
public class RandomziedQueue<Item> implements Iterable<Item> {

	private Item[] a;         // array of items
    private int n;            // number of elements on stack


    /**
     * Initializes an empty stack.
     */
    public RandomziedQueue() {
        a = (Item[]) new Object[2];
        n = 0;
    }

    /**
     * Is this stack empty?
     * @return true if this stack is empty; false otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of items in the stack.
     * @return the number of items in the stack
     */
    public int size() {
        return n;
    }


    // resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= n;

        // textbook implementation
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;

       // alternative implementation
       // a = java.util.Arrays.copyOf(a, capacity);
    }

    /**
     * Adds the item to this stack.
     * @param item the item to add
     */
    public void enqueue(Item item) {
        if (n == a.length) resize(2*a.length);    // double size of array if necessary
        a[n++] = item;                            // add item
    }

    /**
     * Removes and returns the item most recently added to this stack.
     * @return the item most recently added
     * @throws java.util.NoSuchElementException if this stack is empty
     */
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        int index = StdRandom.uniform(size());
        Item item = a[index];
        a[index] = null;
        shiftLeft(index);
        n--;
        // shrink size of array if necessary
        if (n > 0 && n == a.length/4) resize(a.length/2);
        return item;
    }
    
    private void shiftLeft(int index) {
    	Item[] temp = (Item[]) new Object[a.length];
    	for (int i = 0, j = 0; i < n; i++) {
    		if (index != i) {
    			temp[j] = a[i];
    			j++;
			} 
		}
    	a = temp;
    }
    
    public Item sample() {
    	int index = StdRandom.uniform(size());
    	return a[index];
    }
    
    /**
     * Rearrange the elements of an array in random order.
     */
    public void shuffle() {
        int N = n;
        for (int i = 0; i < N; i++) {
            int r = i + StdRandom.uniform(N-i);     // between i and N-1
            Item temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }
    
    /**
     * Returns an iterator to this stack that iterates through the items in LIFO order.
     * @return an iterator to this stack that iterates through the items in LIFO order.
     */
    @Override
	public Iterator<Item> iterator() {
        return new ItemIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ItemIterator implements Iterator<Item> {
        private int i;

        public ItemIterator() {
        	shuffle();
            i = 0;
        }

        @Override
		public boolean hasNext() {
            return i < n;
        }

        @Override
		public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
		public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return a[i++];
        }
    }
   
    public static void main(String[] args) {
    	RandomziedQueue d = new RandomziedQueue();
    	d.enqueue("A");
    	d.enqueue("B");
    	d.enqueue("C");
    	d.enqueue("D");
    	d.enqueue("E");
    	d.enqueue("F");
    	printList(d.iterator());
    	d.dequeue();
    	printList(d.iterator());
    }
    
    private static void printList(Iterator iterator) {
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		System.out.println("Finished printing list \n");
	}

}
