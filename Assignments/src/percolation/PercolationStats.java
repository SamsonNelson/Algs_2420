package percolation;

import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

/**
 * 
 * @author Samson Nelson
 * Assignment Percolation
 * Feb 4th 2017
 * 
 */

public class PercolationStats {
	
	private int experimentsCount;
    private Percolation pr;
    private double[] fractions;

	// perform trials independent experiments on an n-by-n grid
	public PercolationStats(int n, int t) {
		
		if (n <= 0 || t <= 0) {
            throw new IllegalArgumentException("Given n <= 0 || t <= 0");
        }
		
		experimentsCount = t;
        fractions = new double[experimentsCount];
        for (int expNum = 0; expNum < experimentsCount; expNum++) {
            pr = new Percolation(n);
            int openedSites = 0;
            while (!pr.percolates()) {
                int x = StdRandom.uniform(0, n);
                int y = StdRandom.uniform(0, n);
                if (!pr.isOpen(x, y)) {
                    pr.open(x, y);
                    openedSites++;
                }
            }
            double fraction = (double) openedSites / (n * n);
            fractions[expNum] = fraction;
        }
		
	}   
	//sample mean of percolation threshold
	   public double mean() {
		  return StdStats.mean(fractions);
	}            
	   
	//sample standard deviation of percolation threshold
	   public double stddev() {
		  return StdStats.stddev(fractions);
	}              
	   
	//low  endpoint of 95% confidence interval
	   public double confidenceLo() {
		   return mean() - ((1.96 * stddev()) / Math.sqrt(experimentsCount));
	}               
	   
	//high endpoint of 95% confidence interval
	   public double confidenceHi() {
		   return mean() + ((1.96 * stddev()) / Math.sqrt(experimentsCount));
	}                

	   public static void main(String[] args) {
		   int N = Integer.parseInt(args[0]);
	        int T = Integer.parseInt(args[1]);
	        PercolationStats ps = new PercolationStats(N, T);

	        StdOut.println("mean                    = " + ps.mean());
	        StdOut.println("stddev                  = " + ps.stddev());
	        StdOut.println("confindenceLow          = " + ps.confidenceLo());
	        StdOut.println("confindenceHigh         = " + ps.confidenceHi());
	   } 
}
