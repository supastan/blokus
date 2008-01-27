package data;


public class ComputerPlayer extends Player {

	/** Name of the player */
	private static final String NAME = "Computer";
	
	/** 
	 * Creates an instance of Computer player.
	 */
	public ComputerPlayer() {
		super(NAME);
		
		_hand = new Hand();
		
	}
	
	@Override
	public Move getNextMove(Board board) {
		Move move = null;
		
		
		return move;
	}

}
