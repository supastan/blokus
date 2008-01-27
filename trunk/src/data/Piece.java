package data;


public class Piece implements Cloneable {
	
	/** Block types */
	enum Type {
		one_block,
		two_blocks,
		three_in_a_row,
	}
	
	/** Flip axis */
	enum Axis {
		X,
		Y,
	}
	
	/** Rotate direction */
	enum Direction {
		clockwise,
		counter,
	}
	
	public Piece(Type type) {
	}
	
	/**
	 * Returns the horizontal length of the Piece.
	 * @return the horizontal length of the Piece.
	 */
	public int getLength() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Returns the vertical height of the Piece.
	 * @return the vertical height of the Piece.
	 */
	public int getHeight() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Returns the number of blocks.
	 * @return the number of blocks.
	 */
	public int getNumBlocks() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Flip the Piece about the specified axis.
	 * @param axis axis.
	 */
	public void flip(Axis axis) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Rotate the Piece 90 degrees in the specified direction.
	 * @param dir direction of rotation
	 */
	public void rotate(Direction dir) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	/**
	 * Returns the exact copy of the Piece.
	 */
	public Piece clone() {
		throw new UnsupportedOperationException();
	}
}
