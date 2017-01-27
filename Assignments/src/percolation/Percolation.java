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
	
	// Connecting top row to "Top 0"
	private void initializeUF() {
		for (int i = 0; i < n; i++) {
			unionFind.union(0, i);
			unionFind.union((n*n) + n + 1 + i, n*n);
		}
	}
	
	// Takes 2D array and converts to 1D array
	private int xyTo1d(int x, int y) {
		return ((x*n) + y + 1);
	}
	
	// Validate the indices of the site
	// Mark site is open - return value to 1D array
	// Union Find operations to its open neighbors (TODO Figure out how to account for vertical and horizontal neighbors
	public void open(int x, int y) {
		unionFind.union(x, y); // Union with "Top 0"
							   // Return true to array to keep track 
		return;
	}
	
	// Checks to see if site is open
	// Use "Top 0" to see if its open
	public boolean isOpen(int x, int y) {
		return unionFind.connected(x, y); // See if tile is connected to "Top 0"
		
	}
	
	// Checks to see if site is in a component that connects to "Top 0"
	public boolean isFull(int x, int y) {
		return unionFind.connected(x, y); // 
	}
	
	// Checks to see if "Top 0" connects to bottom "n*n+2"
	// If statement that returns true if "Top 0" is connected to the bottom
	public boolean percolates() {
		return false;
	}

	// Returns number of open sites
	public int numberOfOpenSites() {
		return unionFind.count();
	}

}
