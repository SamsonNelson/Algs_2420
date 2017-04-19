package eightPuzzle;

/**
 * Samson Nelson
 * March 25th 2017
 * 
 * Assignment A04 8Puzzle
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {

	private final int N;
	private final int[][] blocks;
	private int[][] goalBlocks;

	public Board(int[][] blocks) {
		this.blocks = blocks;
		N = blocks[0].length;
		initGoalBoard();
	}

	private int[][] copyBoard(int[][] blocks) {
		int boardLength = blocks.length;
		int[][] newBoard = new int[boardLength][boardLength];

		for (int i = 0; i < boardLength; i++) {
			for (int j = 0; j < boardLength; j++) {
				newBoard[i][j] = blocks[i][j];
			}
		}

		return newBoard;
	}

	public int size() {
		return N;
	}

	public int hamming() {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (blocks[i][j] != goalBlocks[i][j] && !isEnd(i, j)) {
					count++;
				}
			}
		}
		return count;
	}

	private boolean isEnd(int i, int j) {
		return i == N - 1 && j == N - 1;
	}

	public int manhattan() {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int value = blocks[i][j];
				if (value != 0 && value != goalValueAt(i, j)) {
					int initialX = (value - 1) / N;
					int initialY = value - 1 - initialX * N;
					int distance = Math.abs(i - initialX) + Math.abs(j - initialY);
					sum += distance;
				}
			}
		}
		return sum;
	}

	private int goalValueAt(int i, int j) {
		if (isEnd(i, j)) {
			return 0;
		}
		// return 1 + i * N + j;
		return goalBlocks[i][j];
	}

	private void initGoalBoard() {
		goalBlocks = new int[N][N];
		int value = 1;
		int maxValue = N * N;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (value == maxValue) {
					goalBlocks[i][j] = 0;
				} else {
					goalBlocks[i][j] = value++;
				}
			}
		}
	}

	public boolean isGoal() {
		return tilesEquals(blocks, goalBlocks);
	}

	//
	// public boolean isSolvable() {
	//
	// }
	//
	@Override
	public boolean equals(Object x) {
		if (x == this)
			return true;
		if (x == null)
			return false;
		if (x.getClass() != this.getClass())
			return false;

		Board that = (Board) x;
		return this.N == that.N && tilesEquals(this.blocks, that.blocks);
	}

	private boolean tilesEquals(int[][] first, int[][] second) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (first[i][j] != second[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	// Gets all neighboring boards
	@SuppressWarnings("unchecked")
	public Iterable<Board> neighbors() {
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (blocks[row][col] == 0) {
					return getNeighbors(row, col);
				}
			}
		}

		return (Iterable<Board>) Collections.emptyIterator();
	}

	// Gets all neighboring boards of one block
	private Iterable<Board> getNeighbors(int freeRow, int freeColumn) {
		List<Board> neighbors = new ArrayList<Board>();

		int[][] shifts = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };

		for (int i = 0; i < shifts.length; i++) {
			int row = freeRow + shifts[i][0];
			int column = freeColumn + shifts[i][1];
			boolean isWithinBounds = (row >= 0) && (row < N) && (column >= 0) && (column < N);

			if (isWithinBounds) {
				int[][] tempBlocks = copyBoard(blocks);
				// changes values between 2 blocks: empty and neighbor
				tempBlocks[row][column] = blocks[freeRow][freeColumn];
				tempBlocks[freeRow][freeColumn] = blocks[row][column];
				neighbors.add(new Board(tempBlocks));
			}
		}

		return neighbors;
	}
	
	// A board that is obtained by exchanging two adjacent
    // blocks in the same row
    public Board twin() {
        int[][] newBoard = copyBoard(blocks);
        boolean blocksSwapped = false;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < (N - 1); j++) {
                if (newBoard[i][j] > 0 && newBoard[i][j + 1] > 0) {
                    int swap = newBoard[i][j];
                    newBoard[i][j] = newBoard[i][j + 1];
                    newBoard[i][j + 1] = swap;
                    blocksSwapped = true;
                    break;
                }
            }

            if (blocksSwapped)
                break;
        }

        return new Board(newBoard);
    }

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(N + "\n");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				s.append(String.format("%2d ", blocks[i][j]));
			}
			s.append("\n");
		}
		return s.toString();
	}

	public static void main(String[] args) {

		Board miniBoard = new Board(new int[][] { { 0, 1, 3 }, { 4, 2, 5 }, { 7, 8, 6 } });
		Board miniBoard2 = new Board(new int[][] { { 0, 1, 3 }, { 4, 2, 5 }, { 7, 6, 8 } });

		// System.out.println(miniBoard.toString());

		Board goalBoard = new Board(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } });
		// System.out.println(goalBoard.toString());
		// System.out.println("miniBoard equal goalBoard " +
		// miniBoard.equals(goalBoard));
		// System.out.println("goalBoard equals goalBoard " +
		// goalBoard.equals(goalBoard));
		//
		// System.out.println();
		//
		// System.out.println("miniBoard Hamming " + miniBoard.hamming());
		// System.out.println("goalBoard Hamming " + goalBoard.hamming());
		//
		// System.out.println();
		//
		// System.out.println("miniBoard manhattan " + miniBoard.manhattan());
		// System.out.println("goalBoard manhattan " + goalBoard.manhattan());
		// System.out.println("miniBoard2 manhattan " + miniBoard2.manhattan());

		System.out.println("Original Mini Board " + miniBoard.toString() + "\n");
		System.out.println("Showing Twins " + miniBoard.twin().toString());
		System.out.println("Getting Neighbors " + miniBoard.neighbors());
		
		

	}

}
