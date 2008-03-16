package data;

import java.util.ArrayList;


public class ComputerPlayer extends Player {

	AI thisAI;
	
	/** 
	 * Creates an instance of Computer player.
	 */
	public ComputerPlayer(int index, AI ai) {
		super(ai.toString(), index);
		
		thisAI  = ai;
		
		_hand = new Hand();
		
	}
	
	@Override
	public Move getNextMove(Board board)
	{
		Move aMove;
		try
		{
			aMove = thisAI.nextMove(board, this);
		}
		catch(RuntimeException e)
		{
			this.setHasMoreMoves(false);
			aMove = new Move(Move.Type.Skip, null, 0, 0 );
		}
		
		return aMove;
	}
	
	public AI getAI()
	{
		return thisAI;
	}
	
	@Override
	public Player clone() {
		ComputerPlayer copy = new ComputerPlayer(this.getIndex(), thisAI);
		copy._hand = this._hand.clone();
		
		return copy;
	}

}
