package ElementSymbolTable;


import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;

/**
 * ****************************************************************************
 * Compilation: javac ArrayST.java Execution: java ArrayST < input.txt
 * Dependencies: StdIn.java StdOut.java Data files:
 * http://algs4.cs.princeton.edu/31elementary/tinyST.txt
 *
 *
 * Symbol table implementation with unordered array. Uses repeated doubling to
 * resize the array.
 *
 * % java ArrayST < tiny.txt S 0 H 5 X 7 R 3 C 4 L 11 A 8 M 9 P 10 E 12
 *
 *****************************************************************************
 */
public class ArrayST<Key, Value> {

    private static final int INIT_SIZE = 8;

    private Value[] vals;   // symbol table values
    private Key[] keys;   // symbol table keys
    private int N = 0;      // number of elements in symbol table

    public ArrayST() {
        keys = (Key[]) new Object[INIT_SIZE];
        vals = (Value[]) new Object[INIT_SIZE];
    }

    // return the number of key-value pairs in the symbol table
    public int size() {
        return N;
    }

    // is the symbol table empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // resize the parallel arrays to the given capacity
    private void resize(int capacity) {
        Key[] tempk = (Key[]) new Object[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            tempk[i] = keys[i];
        }
        for (int i = 0; i < N; i++) {
            tempv[i] = vals[i];
        }
        keys = tempk;
        vals = tempv;
    }

    // insert the key-value pair into the symbol table
    public void put(Key key, Value val) {

        // to deal with duplicates
        delete(key);

        // double size of arrays if necessary
        if (N >= vals.length) {
            resize(2 * N);
        }

        // add new key and value at the end of array
        vals[N] = val;
        keys[N] = key;
        N++;
    }

    public Value get(Key key) {
        for (int i = 0; i < N; i++) {
            if (keys[i].equals(key)) {
                return vals[i];
            }
        }
        return null;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < N; i++) {
            queue.enqueue(keys[i]);
        }
        return queue;
    }

    // remove given key (and associated value)
    public void delete(Key key) {
        for (int i = 0; i < N; i++) {
            if (key.equals(keys[i])) {
                keys[i] = keys[N - 1];
                vals[i] = vals[N - 1];
                keys[N - 1] = null;
                vals[N - 1] = null;
                N--;
                if (N > 0 && N == keys.length / 4) {
                    resize(keys.length / 2);
                }
                return;
            }
        }
    }

    /**
     * *************************************************************************
     * Test routine.
     * *************************************************************************
     */
    public static void main(String[] args) {
        ArrayST<String, String> st = new ArrayST<String, String>();

//reading in file
        In in = new In("/Users/samsonnelson/Documents/WorkSpace_algs4_Spring_2017/Exercises/src/ElementSymbolTable/Terms.txt");
        
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] tokens = line.split("-");
            
            String key = tokens[0];
            String val = tokens[1];
            st.put(key, val);
        }
        StdOut.println("<-------------Computer Terms--------------->");
        for (String s : st.keys()) {
            StdOut.println(s + "-" + st.get(s));
        }
        
        System.out.println(st.size());
        
    }
}
