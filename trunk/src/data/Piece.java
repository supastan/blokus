package data;

import java.util.Arrays;

/**
 * Represents a Blokus game piece.
 * @author Yong Shin
 *
 */
public class Piece implements Cloneable {
	
	/** Maximum block sizes */
	public static final int MAX_X_BLOCKSIZE = 5;
	public static final int MAX_Y_BLOCKSIZE = 5;

	/** Default representative character. Used in toString() method. */
	public static final char DEFAULT_CHAR = '*';
	
	/** Block types */
	public enum Type {
		I,
		L,
		U,
		Z,
		T,
		X,
		W,
		V,
		F,
		P,
		Y,
		N,
		One,
		Two,
		Three,
		ShortI,
		ShortT,
		ShortL,
		ShortZ,
		Square,
		Crooked3,
	}
	
	/** Flip axis */
	public enum Axis {
		X,
		Y,
	}
	
	/** Rotate direction */
	public enum Direction {
		Clockwise,
		CounterClockwise,
	}
	
	/** Contains blocks. All blocks have the same capacity (i.e. MAX_Y_BLOCKSIZE by MAX_X_BLOCKSIZE) */
	private int[][] _blocks;
	
	/** Piece's type */
	private Type _type;
	
	/** Piece's horizontal width */
	private int _width;
	
	/** Piece's vertical height */
	private int _height;
	
	/** Number of blocks */
	private int _numBlocks;
	
	/** Representative character. Used in toString(). */
	private char _char;
	
	/** Specifies that the Piece is invariant under any flips. */
	private boolean _flipInvariant;
	
	/** Specifies that the Piece is invariant under any rotation. */
	private boolean _rotateInvariant;
	
	/** 
	 * Creates a new Piece of the given type.
	 * @param type Piece's type.
	 */
	public Piece(Type type) {
		
		_blocks = new int[MAX_Y_BLOCKSIZE][MAX_X_BLOCKSIZE];
		_type = type;
		_char = DEFAULT_CHAR;
		_flipInvariant = false;
		_rotateInvariant = false;
		
		// assign initial block properties
		switch(type) {
		
		case I:
			_numBlocks = 5; _height = 5; _width = 1; 
			_blocks[0][0] = _blocks[1][0] = _blocks[2][0] = _blocks[3][0] = _blocks[4][0] = 1;
			_flipInvariant = true;
			break;
			
		case L:
			_numBlocks = 5; _height = 4; _width = 2;
			_blocks[0][0] = _blocks[1][0] = _blocks[2][0] = _blocks[3][0] = _blocks[3][1] = 1;
			break;
			
		case U:
			_numBlocks = 5; _height = 2; _width = 3;
			_blocks[0][0] = _blocks[0][2] = _blocks[1][0] = _blocks[1][1] = _blocks[1][2] = 1;
			break;
			
		case Z:
			_numBlocks = 5; _height = 3; _width = 3;
			_blocks[0][0] = _blocks[0][1] = _blocks[1][1] = _blocks[2][1] = _blocks[2][2] = 1;
			break;
			
		case T:
			_numBlocks = 5; _height = 3; _width = 3;
			_blocks[0][0] = _blocks[0][1] = _blocks[0][2] = _blocks[1][1] = _blocks[2][1] = 1;
			break;
			
		case X:
			_numBlocks = 5; _height = 3; _width = 3;
			_blocks[0][1] = _blocks[1][0] = _blocks[1][1] = _blocks[1][2] = _blocks[2][1] = 1;
			_flipInvariant = _rotateInvariant = true;
			break;
			
		case W:
			_numBlocks = 5; _height = 3; _width = 3;
			_blocks[0][2] = _blocks[1][1] = _blocks[1][2] = _blocks[2][0] = _blocks[2][1] = 1;
			break;
			
		case V:
			_numBlocks = 5; _height = 3; _width = 3;
			_blocks[0][2] = _blocks[1][2] = _blocks[2][0] = _blocks[2][1] = _blocks[2][2] = 1;
			break;
			
		case F:
			_numBlocks = 5; _height = 3; _width = 3;
			_blocks[0][1] = _blocks[0][2] = _blocks[1][0] = _blocks[1][1] = _blocks[2][1] = 1;
			break;
			
		case P:
			_numBlocks = 5; _height = 3; _width = 2;
			_blocks[0][0] = _blocks[0][1] = _blocks[1][0] = _blocks[1][1] = _blocks[2][0] = 1;
			break;
			
		case Y:
			_numBlocks = 5; _height = 2; _width = 4;
			_blocks[0][1] = _blocks[1][0] = _blocks[1][1] = _blocks[1][2] = _blocks[1][3] = 1;
			break;
			
		case N:
			_numBlocks = 5; _height = 2; _width = 4;
			_blocks[0][1] = _blocks[0][2] = _blocks[0][3] = _blocks[1][0] = _blocks[1][1] = 1;
			break;
			
		case One:
			_numBlocks = 1; _height = 1; _width = 1;
			_blocks[0][0] = 1;
			_flipInvariant = _rotateInvariant = true;
			break;
			
		case Two:
			_numBlocks = 2; _height = 1; _width = 2;
			_blocks[0][0] = _blocks[0][1] = 1;
			_flipInvariant = true;
			break;
			
		case Three:
			_numBlocks = 3; _height = 1; _width = 3;
			_blocks[0][0] = _blocks[0][1] = _blocks[0][2] = 1;
			_flipInvariant = true;
			break;
			
		case ShortI:
			_numBlocks = 4; _height = 4; _width = 1;
			_blocks[0][0] = _blocks[1][0] = _blocks[2][0] = _blocks[3][0] = 1;
			_flipInvariant = true;
			break;
			
		case ShortT:
			_numBlocks = 4; _height = 2; _width = 3;
			_blocks[0][0] = _blocks[0][1] = _blocks[0][2] = _blocks[1][1] = 1;
			break;
			
		case ShortL:
			_numBlocks = 4; _height = 3; _width = 2;
			_blocks[0][0] = _blocks[1][0] = _blocks[2][0] = _blocks[2][1] = 1;
			break;
			
		case ShortZ:
			_numBlocks = 4; _height = 2; _width = 3;
			_blocks[0][0] = _blocks[0][1] = _blocks[1][1] = _blocks[1][2] = 1;
			break;
			
		case Square:
			_numBlocks = 4; _height = 2; _width = 2;
			_blocks[0][0] = _blocks[0][1] = _blocks[1][0] = _blocks[1][1] = 1;
			_flipInvariant = _rotateInvariant = true;
			break;
			
		case Crooked3:
			_numBlocks = 3; _height = 2; _width = 2;
			_blocks[0][0] = _blocks[1][0] = _blocks[1][1] = 1;
			break;
			
		default:
			throw new RuntimeException("Unknown piece type: " + type);
		}
	}
	
	/**
	 * Returns Piece's type.
	 * @return Piece's type.
	 */
	public Type getType() {
		return _type;
	}
	
	/**
	 * Returns the horizontal width of the Piece.
	 * @return the horizontal width of the Piece.
	 */
	public int getWidth() {
		return _width;
	}
	
	/**
	 * Returns the vertical height of the Piece.
	 * @return the vertical height of the Piece.
	 */
	public int getHeight() {
		return _height;
	}
	
	/**
	 * Returns the number of blocks.
	 * @return the number of blocks.
	 */
	public int getNumBlocks() {
		return _numBlocks;
	}
	
	/**
	 * Returns the blocks array.
	 * @return the blocks array.
	 */
	public int[][] getBlocks() {
		return _blocks.clone();
	}
	
	/**
	 * Returns the representative character of the Piece.
	 * @return the representative character of the Piece.
	 */
	public char getRepresentingChar() {
		return _char;
	}
	
	/**
	 * Sets the representative character of the Piece.
	 * @param ch new character
	 */
	public void setRepresentingChar(char ch) {
		_char = ch;
	}
	
	/**
	 * Specifies if the Piece is invariant under any flips.
	 * @return true if the Piece is invariant under any flips.
	 */
	public boolean isFlipInvariant() {
		return _flipInvariant;
	}
	
	/**
	 * Specifies if the Piece is invariant under any rotation.
	 * @return true if the Piece is invariant under any rotation.
	 */
	public boolean isRotateInvariant() {
		return _rotateInvariant;
	}
	
	/**
	 * Flip the Piece about the specified axis.
	 * @param axis axis.
	 */
	public void flip(Axis axis) {
		
		int[][] temp = new int[MAX_Y_BLOCKSIZE][MAX_X_BLOCKSIZE];
		
		if (axis == Axis.X) {
			
			// flip about x-axis
			for (int row = 0; row < _height; row++) {
				for (int col = 0; col < _width; col++) {
					temp[_height-row-1][col] = _blocks[row][col];
				}
			}
			
		} else {
			
			// flip about y-axis
			for (int row = 0; row < _height; row++) {
				for (int col = 0; col < _width; col++) {
					temp[row][_width-col-1] = _blocks[row][col];
				}
			}
			
		}
		
		_blocks = temp;
	}
	
	/**
	 * Rotate the Piece 90 degrees in the specified direction.
	 * @param dir direction of rotation
	 */
	public void rotate(Direction dir) {
		
		int[][] temp = new int[MAX_Y_BLOCKSIZE][MAX_X_BLOCKSIZE];
		
		if (dir == Direction.Clockwise) {
			
			// rotate in clockwise direction
			for (int row = 0; row < _height; row++) {
				for (int col = 0; col < _width; col++) {
					temp[col][_height-row-1] = _blocks[row][col];
				}
			}
			
		} else {
			
			// rotate in counter-clockwise direction
			for (int row = 0; row < _height; row++) {
				for (int col = 0; col < _width; col++) {
					temp[_width-col-1][row] = _blocks[row][col];
				}
			}
			
		}
		
		_blocks = temp;
		int prevWidth = _width;
		_width = _height;
		_height = prevWidth;
	}
	
	/**
	 * Returns the exact copy of the Piece.
	 */
	@Override
	public Piece clone() {
		Piece copy = new Piece(_type);
		copy._width = _width;
		copy._height = _height;
		copy._blocks = _blocks.clone();
		return copy;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = true;
		if (obj == null || !obj.getClass().equals(this.getClass())) {
			return false;
		}
		if (obj == this) {
			return true;
		} else {
			Piece other = (Piece) obj;
			if (_width != other._width)
				result = false;
			if (_height != other._height)
				result = false;
			if (_numBlocks != other._numBlocks)
				result = false;
			if (_char != other._char)
				result = false;
			if (_type != other._type)
				result = false;
			if (!Arrays.deepEquals(_blocks, other._blocks))
				result = false;
		}
		return result;
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int row = 0; row < _height; row++) {
			for (int col = 0; col < _width; col++) {
				if (_blocks[row][col] == 1)
					sb.append(_char);
				else
					sb.append(' ');
			}
			sb.append('\n');
		}
		return sb.toString();
	}
}
