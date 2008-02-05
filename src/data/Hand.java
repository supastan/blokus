package data;

import java.util.ArrayList;
import java.util.Iterator;


public class Hand {
	
	private ArrayList<Piece> aHand = new ArrayList<Piece>();

	/**
	 * Creates an instance of a Hand.
	 * 
	 */
	public Hand() 
	{
		reset();
		addAllNewPieces();
	}
	
	public void reset()
	{
		aHand.clear();	
	}
	
	public int piecesLeft()
	{
		return aHand.size();
	}
	
	public Iterator getIterator()
	{
		return aHand.iterator();
	}
	
	private void addAllNewPieces()
	{
		Piece.Type[] types = Piece.Type.values();
		for (int i = 0; i < types.length; i++)
		{
			aHand.add(new Piece(types[i]));
		}
	}
	
	
}
