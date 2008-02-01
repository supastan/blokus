package data;


public class Move {

	private Piece _piece;
	
	private int _row;
	
	private int _col;
	
	/** Move type enumeration */
	public enum Type {
		Normal,
		Skip,
		Quit,
	}
	
	/** Move type */
	private Type _type;
	
	/** 
	 * Creates a Move of specified type.
	 * 
	 * @param type Move Type
	 */
	public Move(Type type, Piece piece, int row, int col) {
		_type = type;
		_piece = piece;
		_row = row;
		_col = col;
	}
	
	/**
	 * Returns the Type of this Move.
	 * 
	 * @return Type of this Move.
	 */
	public Type getType() {
		return _type;
	}
	
	public Piece getPiece() {
		return _piece;
	}
	
	public int getRow() {
		return _row;
	}

	public int getColumn() {
		return _col;
	}
}
