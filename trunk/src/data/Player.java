package data;

import java.util.concurrent.ArrayBlockingQueue;

abstract public class Player {

	/** Player move queue size.  Just need one for thread synchronization. */
	private static final int QUEUE_SIZE = 1;
	
	/**
	 * Queue used to force Game's thread to wait for user input.
	 */
	private ArrayBlockingQueue<Move> _moveQueue;
	
	/** Stores player's hand */
	protected Hand _hand;
	
	protected boolean _abortRequested;
	
	/** Player's name */
	private String _name;
	
	/** Player's score */
	private int _score;
	
	/**
	 * Creates an instance of Player.
	 * 
	 * @param name Player's display name.
	 */
	public Player(String name) {
		_name = name;
		_moveQueue = new ArrayBlockingQueue<Move>(QUEUE_SIZE);
	}
	
	/**
	 * Returns player's hand.
	 * 
	 * @return player's hand.
	 */
	public Hand getHand() {
		return _hand;
	}
	
	/**
	 * Returns player's name.
	 * 
	 * @return player's name.
	 */
	public String getName() {
		return _name;
	}
	
	/**
	 * Returns player's score.
	 * 
	 * @return player's score.
	 */
	public int getScore() {
		return _score;
	}
	
	/**
	 * Adds score to player's total score.
	 * 
	 * @param score score to add.
	 */
	public void addScore(int score) {
		_score += score;
	}

	/**
	 * Reset player's state.
	 */
	public void reset() {
		_hand.reset();
		_score = 0;
		_abortRequested = false;
	}
	
	/**
	 * Removes a Move from the MoveQueue.  This method blocks the
	 * Game thread until a Move becomes available in the Queue.
	 * 
	 * @param monitor instance of GameMonitor.
	 * @return next Move
	 */
	public Move getNextMove(Board board) {
		Move move = null;
		try {
			move = _moveQueue.take();
		} catch (InterruptedException ex) {
			// do nothing
		}
		return move;
	}
	
	/**
	 * Places a Move to the MoveQueue.  This method is called by UI
	 * compoments to place user's input into the Queue.
	 * 
	 * @param move next Move.
	 */
	public void putNextMove(Move move) {
		try {
			_moveQueue.put(move);
		} catch (InterruptedException ex) {
			// do nothing
		}
	}
	
	public void abort() {
		_abortRequested = true;
	}
}
