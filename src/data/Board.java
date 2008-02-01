package data;

import java.awt.Dimension;



public class Board {

	public static final int X_DIMENSION = 20;
	
	public static final int Y_DIMENSION = 20;

	int[][] _blocks;
	
	/** 
	 * Creates an empty board.
	 */
	public Board() {
		_blocks = new int[X_DIMENSION][Y_DIMENSION];
	}
	
	public int[][] getBlocks() {
		return _blocks.clone();
	}
	
	/**
	 * Reset Board state.
	 */
	public void reset() {
	}
	
	/**
	 * Places a piece on the board. 
	 * 
	 * @param piece Blokus piece to place.
	 * @param location location of the upper left hand corner of the piece.
	 * @return true if the piece can be placed at the specified location, else false.
	 */
	public boolean place(Piece piece, Dimension location) {
		throw new UnsupportedOperationException();
	}

	public Board clone() {
		return null;
	}
}
