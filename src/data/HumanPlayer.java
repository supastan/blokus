package data;


public class HumanPlayer extends Player {
	
	/** Name of the Human player */
	private static final String NAME = "User";
	
	/**
	 * Creates an instance of Human player.
	 */
	public HumanPlayer() {
		super(NAME);
		
		_hand = new Hand();
	}

}
