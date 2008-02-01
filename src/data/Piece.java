package data;


public class Piece implements Cloneable {
	
	public static final int MAX_X_BLOCKSIZE = 5;
	
	public static final int MAX_Y_BLOCKSIZE = 5;
	
	/** Block types */
	enum Type {
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
	enum Axis {
		X,
		Y,
	}
	
	/** Rotate direction */
	enum Direction {
		clockwise,
		counter,
	}
	
	private int[][] _blocks;
	
	private int _length;
	
	private int _height;
	
	private int _numBlocks;
	
	public Piece(Type type) {
		_blocks = new int[MAX_Y_BLOCKSIZE][MAX_X_BLOCKSIZE];
		
		// assign initial block properties
		switch(type) {
		
		case I:
			_numBlocks = 5; _length = 1; _height = 5;
			_blocks[1][1] = _blocks[1][2] = _blocks[1][3] = _blocks[1][4] = _blocks[1][5] = 1;
			break;
			
		case L:
			_numBlocks = 5; _length = 2; _height = 4;
			_blocks[1][1] = _blocks[1][2] = _blocks[1][3] = _blocks[1][4] = _blocks[2][4] = 1;
			break;
			
		case U:
			_numBlocks = 5; _length = 3; _height = 2;
			_blocks[1][1] = _blocks[1][2] = _blocks[2][2] = _blocks[3][1] = _blocks[3][2] = 1;
			break;
			
		case Z:
			_numBlocks = 5; _length = 3; _height = 3;
			_blocks[1][1] = _blocks[2][1] = _blocks[2][2] = _blocks[2][3] = _blocks[3][3] = 1;
			break;
			
		case T:
			_numBlocks = 5; _length = 3; _height = 3;
			_blocks[1][1] = _blocks[2][1] = _blocks[2][2] = _blocks[2][3] = _blocks[3][1] = 1;
			break;
			
		case X:
			_numBlocks = 5; _length = 3; _height = 3;
			_blocks[1][2] = _blocks[2][1] = _blocks[2][2] = _blocks[2][3] = _blocks[3][2] = 1;
			break;
			
		case W:
			_numBlocks = 5; _length = 3; _height = 3;
			_blocks[1][3] = _blocks[2][2] = _blocks[2][3] = _blocks[3][1] = _blocks[3][2] = 1;
			break;
			
		case V:
			_numBlocks = 5; _length = 3; _height = 3;
			_blocks[1][3] = _blocks[2][3] = _blocks[3][1] = _blocks[3][2] = _blocks[3][3] = 1;
			break;
			
		case F:
			_numBlocks = 5; _length = 3; _height = 3;
			_blocks[1][2] = _blocks[2][1] = _blocks[2][2] = _blocks[2][3] = _blocks[3][1] = 1;
			break;
			
		case P:
			_numBlocks = 5; _length = 2; _height = 3;
			_blocks[1][1] = _blocks[1][2] = _blocks[1][3] = _blocks[2][1] = _blocks[2][2] = 1;
			break;
			
		case Y:
			_numBlocks = 5; _length = 4; _height = 2;
			_blocks[1][2] = _blocks[2][1] = _blocks[2][2] = _blocks[3][2] = _blocks[4][2] = 1;
			break;
			
		case N:
			_numBlocks = 5; _length = 4; _height = 2;
			_blocks[1][2] = _blocks[2][1] = _blocks[2][2] = _blocks[3][1] = _blocks[4][1] = 1;
			break;
			
		case One:
			_numBlocks = 5; _length = 4; _height = 2;
			_blocks[1][2] = _blocks[2][1] = _blocks[2][2] = _blocks[3][1] = _blocks[4][1] = 1;
			break;
			
		case Two:
			break;
			
		case Three:
			break;
			
		case ShortI:
			break;
			
		case ShortT:
			break;
			
		case ShortL:
			break;
			
		case ShortZ:
			break;
			
		case Square:
			break;
			
		case Crooked3:
			break;
			
		default:
			throw new RuntimeException("Unknown piece type: " + type);
		}
	}
	
	/**
	 * Returns the horizontal length of the Piece.
	 * @return the horizontal length of the Piece.
	 */
	public int getLength() {
		return _length;
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
	
	public int[][] getBlocks() {
		return _blocks.clone();
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
