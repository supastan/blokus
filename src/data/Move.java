package data;

import java.awt.Dimension;

public class Move {

	private Piece _piece;
	
	private Dimension _location;
	
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
	public Move(Type type, Piece piece, Dimension location) {
		_type = type;
		_piece = piece;
		_location = location;
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
	
	public Dimension getLocation() {
		return _location;
	}
}
