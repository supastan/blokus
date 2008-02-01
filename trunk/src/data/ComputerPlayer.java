package data;


public class ComputerPlayer extends Player {

	/** Name of the player */
	private static final String NAME = "Computer";
	
	/** 
	 * Creates an instance of Computer player.
	 */
	public ComputerPlayer(int index) {
		super(NAME, index);
		
		_hand = new Hand();
		
	}
	
	@Override
	public Move getNextMove(Board board) {
		Move move = new Move(Move.Type.Skip, null, 0, 0);
		
		
		return move;
	}

}
