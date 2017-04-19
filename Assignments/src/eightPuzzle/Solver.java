package eightPuzzle;

/**
 * Samson Nelson
 * March 25th 2017
 * 
 * Assignment A04 8Puzzle
 */

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
    private SearchNode lastNode = null;

    // Find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        
        MinPQ<SearchNode> mainPQ = new MinPQ<SearchNode>();
        MinPQ<SearchNode> twinPQ = new MinPQ<SearchNode>();
        mainPQ.insert(new SearchNode(initial, null));
        twinPQ.insert(new SearchNode(initial.twin(), null));

        boolean isMainPQSolvable = false;
        boolean isTwinPQSolvable = false;
        
        while (!isMainPQSolvable && !isTwinPQSolvable) {
            lastNode = expandPQ(mainPQ);
            isTwinPQSolvable = lastNode != null;
            isMainPQSolvable = expandPQ(twinPQ) != null;
        }
    }

    // Search Node
    private class SearchNode implements Comparable<SearchNode> {
    	private Board board;
    	private SearchNode previousSearchNode;
    	private int moves = 0;
    	
    	public SearchNode(Board board, SearchNode previousSearchNode) {
    		this.board = board;
    		this.previousSearchNode = previousSearchNode;
    		this.moves = previousSearchNode == null ? 0 : previousSearchNode.moves + 1;
    	}
    	
		@Override
		public int compareTo(SearchNode searchNode) {
			int manhattanDiff = this.board.manhattan() - searchNode.board.manhattan();
            int movesDiff = this.moves - searchNode.moves;
            return manhattanDiff + movesDiff;
		}
       
    }

    // Expands Node PQ with neighbouring nodes
    private SearchNode expandPQ(MinPQ<SearchNode> queue) {
        if (queue.isEmpty())
            return null;

        SearchNode currentNode = queue.delMin();
        if (currentNode.board.isGoal())
            return currentNode;

        for (Board board : currentNode.board.neighbors()) {
            if (currentNode.previousSearchNode == null || !board.equals(currentNode.previousSearchNode.board))
                queue.insert(new SearchNode(board, currentNode));
        }

        return null;
    }

    // Checks wheather the initial board is solvable
    public boolean isSolvable() {
    	 return lastNode != null;
    }

    // Minimum number of moves to solve initial board; -1 if unsolvable
    public int moves() {
    	return lastNode != null ? lastNode.moves : -1;
    }

 // Sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (lastNode == null)
            return null;

        Stack<Board> boards = new Stack<Board>();
        for (SearchNode tail = lastNode; tail != null; tail = tail.previousSearchNode) {
            boards.push(tail.board);
        }

        return boards;
    }

    // Solves a slider puzzle (optional)
    public static void main(String[] args) {
    	
    	Board miniBoard = new Board(new int[][] { { 0, 1, 3 }, { 4, 2, 5 }, { 7, 8, 6 } });
    	
    	System.out.println("MiniBoard " + miniBoard.toString() + "\n");
    	Solver solver = new Solver(miniBoard);
    	
    	System.out.println("Is Solvable " + solver.isSolvable() + " Moves = " + solver.moves() + "\n");
    	System.out.println("Solution " +  solver.solution());
    }
}
