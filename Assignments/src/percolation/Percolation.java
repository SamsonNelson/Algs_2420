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
	boolean[][] indexArray;
	
	public Percolation(int n) {
		this.n = n;
		this.unionFind = new UF((n*n) + 2);
		indexArray = new boolean[n][n];
		initializeUF();
	}
	
	// Connecting top row to "Top 0"
	private void initializeUF() {
		for (int i = 0; i <= n; i++) {
			unionFind.union(0, i);
			unionFind.union((n*n) - n + i + 1, (n*n) + 1);
		}
	}
	
	// Takes 2D array and converts to 1D array
	private int xyTo1d(int x, int y) {
		validateIndexes(y, y);
		return ((x*n) + y + 1);
	}
	
	// Validate the indices of the site
	public void open(int x, int y) {
		validateIndexes(x, y);
		indexArray[x][y] = true;
		if (y>0 && isOpen(x, y - 1)) {
			unionFind.union(xyTo1d(x, y - 1), xyTo1d(x, y));
		}
		if (y<n-1 && isOpen(x, y + 1)) {
			unionFind.union(xyTo1d(x, y + 1), xyTo1d(x, y));
		}
		if (x>0 && isOpen(x - 1, y)) {
			unionFind.union(xyTo1d(x - 1, y), xyTo1d(x, y));
		}
		if (x<n-1 && isOpen(x + 1, y)) {
			unionFind.union(xyTo1d(x + 1, y), xyTo1d(x, y));
		}
		
	}
	
	// Checks to see if site is open
	// Use "Top 0" to see if its open
	public boolean isOpen(int x, int y) {
		validateIndexes(x, y);
		return indexArray[x][y];		
	}
	
	public boolean isFull(int x, int y) {
		validateIndexes(x, y);
		
		boolean leftOpen = false;
		if (y > 0 && isOpen(x, y-1)) {
			leftOpen = true;
		}
		
		boolean rightOpen = false;
		if (y < n-1 && isOpen(x, y+1)) {
			leftOpen = true;
		}
		
		boolean topOpen = false;
		if (x > 0 && isOpen(x-1, y)) {
			leftOpen = true;
		}
		
		if (isOpen(x, y) && (leftOpen || rightOpen || topOpen)) {
			return unionFind.connected(0, xyTo1d(x, y));
		}
		return false;
		
	}
	
	// Checks to see if "Top 0" connects to bottom "n*n+2"
	// If statement that returns true if "Top 0" is connected to the bottom
	public boolean percolates() {
		return unionFind.connected(0, (n*n) + 1);
	}

	// Returns number of open sites
	public int numberOfOpenSites() {
		return unionFind.count();
	}

	private void validateIndexes(int x, int y) {
		if (x < 0 || x >= n) throw new IndexOutOfBoundsException("row index " + x + " must be between 0 and " + (n-1));
		if (y < 0 || y >= n) throw new IndexOutOfBoundsException("row index " + y + " must be between 0 and " + (n-1));

	}
	
}
