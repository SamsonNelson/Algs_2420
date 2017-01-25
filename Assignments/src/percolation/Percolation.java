package percolation;

import edu.princeton.cs.algs4.UF;

/**
 * 
 * @author Samson Nelson
 * Assignment Percolation
 * Feb 4th 2017
 * 
 */

public class Percolation {
	
	private int n;
	private UF unionFind;

	public Percolation(int n) {
		this.n = n;
		this.unionFind = new UF((n*n) + 2);
		initializeUF();
	}
	
	private void initializeUF() {
		for (int i = 0; i < n; i++) {
			unionFind.union(0, i);
			unionFind.union((n*n) + n + 1 + i, n*n);
		}
	}
	
	private int xyTo1d(int x, int y) {
		return ((x*n) + y + 1);
	}
	
	public void open(int x, int y) {
	}
	
	public boolean isOpen(int x, int y) {
		return false;
	}
	
	public boolean isFull(int x, int y) {
		return false;
	}
	
	public boolean percolates() {
		return false;
	}

	public String numberOfOpenSites() {
		return null;
	}

}
