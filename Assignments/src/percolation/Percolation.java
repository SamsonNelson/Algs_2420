package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * 
 * @author Samson Nelson
 * Assignment Percolation
 * Feb 4th 2017
 * 
 */

public class Percolation {
	
	private int n;
	//Use unionFindP for percolates
	private WeightedQuickUnionUF unionFindP;
	//Use unionFindF for isFull to avoid backwash
	private WeightedQuickUnionUF unionFindF;
	boolean[][] indexArray;
	
	public Percolation(int n) {
		this.n = n;
		indexArray = new boolean[n][n];
		initializeUF();
	}
	
	private void initializeUF() {
		//Initialize with two dummy nodes at the top and bottom
		this.unionFindP = new WeightedQuickUnionUF((n*n) + 2);
		//Initialize with one dummy node at the top
		this.unionFindF = new WeightedQuickUnionUF((n*n) + 1);
		
		for (int i = 0; i <= n; i++) {
			//Connect top row to dummy node 0
			unionFindP.union(0, i);
			unionFindF.union(0, i);
			
			//Connect bottom row to dummy node n+1
			unionFindP.union((n*n) - n + i + 1, (n*n) + 1);
		}
	}

	private void union(int x, int y) {
		unionFindP.union(x, y);
		unionFindF.union(x, y);
	}
	
	private int xyTo1d(int x, int y) {
		validateIndexes(y, y);
		return ((x*n) + y + 1);
	}
	
	public void open(int x, int y) {
		validateIndexes(x, y);
		indexArray[x][y] = true;
		if (y>0 && isOpen(x, y - 1)) {
			union(xyTo1d(x, y - 1), xyTo1d(x, y));
		}
		if (y<n-1 && isOpen(x, y + 1)) {
			union(xyTo1d(x, y + 1), xyTo1d(x, y));
		}
		if (x>0 && isOpen(x - 1, y)) {
			union(xyTo1d(x - 1, y), xyTo1d(x, y));
		}
		if (x<n-1 && isOpen(x + 1, y)) {
			union(xyTo1d(x + 1, y), xyTo1d(x, y));
		}
	}
	
	public boolean isOpen(int x, int y) {
		validateIndexes(x, y);
		return indexArray[x][y];		
	}
	
	public boolean isFull(int x, int y) {
		validateIndexes(x, y);
		
		if (isOpen(x, y)) {
			return unionFindF.connected(0, xyTo1d(x, y));
		}
		return false;
	}
	
	public boolean percolates() {
		return unionFindP.connected(0, (n*n) + 1);
	}

	public int numberOfOpenSites() {
		return unionFindP.count();
	}
	
	private void validateIndexes(int x, int y) {
		if (x < 0 || x >= n) throw new IndexOutOfBoundsException("row index x " + x + " must be between 0 and " + (n-1) + " n = " + n);
		if (y < 0 || y >= n) throw new IndexOutOfBoundsException("row index y " + y + " must be between 0 and " + (n-1) + " n = " + n);

	}
}
