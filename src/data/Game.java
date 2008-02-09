package data;

import java.util.ArrayList;
import java.util.Observable;


public class Game extends Observable {

	public static final int MAX_NUM_PLAYERS = 4;
	
	public static final String STARTED_EVENT = "Started";
	
	public static final String UPDATED_EVENT = "Updated";
	
	public static final String GAMEOVER_EVENT = "GameOver";
	
	/** Message type enumeration */
	public enum MessageType {
		Normal,
		Error,
		GameOver,
	}
	
	private Board _board;
	
	private ArrayList<Player> _players;
	
	private int _curPlayerIdx;
	
	private boolean _isRunning;
	
	private Thread _monitor;
	
	private synchronized void setRunningStatus(boolean status) {
		_isRunning = status;
	}
	
	/**
	 * Sets current player index to a new value.
	 * 
	 * @param idx new player index
	 */
	private synchronized void setCurrentPlayerIndex(int idx) {
		if (idx < 0 || idx >= _players.size() ) {
			throw new IndexOutOfBoundsException("idx=" + idx);
		}
		_curPlayerIdx = idx;
	}
	
	
	/**
	 * Processes player's move.
	 * 
	 * @param move player's move
	 */
	private void processPlayerMove(Move move) {

		switch (move.getType()) {
		case Normal:
			// place the Piece on the board
			_board.place(move.getPiece(), move.getRow(), move.getColumn(), _players.get(_curPlayerIdx));
			break;
			
		case Skip:
			// do nothing
			break;
			
		case Quit:
			// this is processed in Monitor.run() method.
			break;
		}
		
	}

	
	public Game() {
		_board = new Board();
		_players = new ArrayList<Player>();
	}
	
	public synchronized boolean isRunning() {
		return _isRunning;
	}
	
	public synchronized boolean hasMoreMoves() {
		for (Player player : _players) {
			if (player.hasMoreMoves())
				return true;
		}
		return false;
	}
	
	/**
	 * Returns player for the specified index.
	 * 
	 * @param idx player index
	 * @return player for the specified index.
	 */
	public synchronized Player getPlayer(int index) {
		return _players.get(index);
	}
	
	public synchronized Board getBoard() {
		return _board;
	}
	
	/**
	 * Returns index for current player.
	 * 
	 * @return current player index
	 */
	public synchronized int getCurrentPlayerIndex() {
		return _curPlayerIdx;
	}

	public synchronized Player getCurrentPlayer() {
		return _players.get(_curPlayerIdx);
	}
	
	public synchronized void addPlayer(Player player) {
		// check if we're already running
		if (isRunning()) {
			Bulletin.getBoard().appendMsg(MessageType.Error, "Player cannot be added after game has started.");
			return;
		}
		
		// check if there is room for another player
		if (_players.size() == MAX_NUM_PLAYERS ) {
			Bulletin.getBoard().appendMsg(MessageType.Error, "Table is full.");
			return;
		}

		// check if the player already added
		if (_players.contains(player)) {
			Bulletin.getBoard().appendMsg(MessageType.Error, "Player is already at the table.");
			return;
		}

		_players.add(player);
	}

	public synchronized void removePlayer(int index) {
		// check if we're already running
		if (isRunning()) {
			Bulletin.getBoard().appendMsg(MessageType.Error, "A game has already started.");
			return;
		}
		
		_players.remove(index);
	}

	public synchronized void reset() {
		// remove old data
		_board.reset();
		_players.clear();
		
		// set defaults
		_isRunning = false;
		_curPlayerIdx = 0;
	}
	
	public void start() {
		
		// check if we're already running
		if (isRunning()) {
			Bulletin.getBoard().appendMsg(MessageType.Error, "A game has already started.");
			return;
		}

		Bulletin.getBoard().appendMsg(MessageType.Normal, "New game has started.");
		
		// start game monitor thread
		_monitor = new Thread(new Monitor());
		_monitor.start();
	}
	
	public void abort() {
		
		// check if there is a game running
		if (!isRunning()) {
			Bulletin.getBoard().appendMsg(MessageType.Error, "There is no running game.");
			return;
		}
		
		// get reference to current player
		int idx = getCurrentPlayerIndex();
		Player player = getPlayer(idx);

		// request player to abort
		player.abort();
		
		// wait for monitor thread to die
		try {
			_monitor.join();
		} catch (InterruptedException ex) {
			// do nothing
		}

		Bulletin.getBoard().appendMsg(MessageType.GameOver, "Player aborted game.");
		
		// update game status
		setRunningStatus(false);
		
		// notify observers about change
		setChanged();
		notifyObservers(GAMEOVER_EVENT);
	}

	private class Monitor implements Runnable {
		
		public void run() {

			// update game status
			setRunningStatus(true);
			
			// notify observers to refresh
			setChanged();
			notifyObservers(STARTED_EVENT);

			while (hasMoreMoves()) {
				
				// get reference to current player
				int idx = getCurrentPlayerIndex();
				Player player = getPlayer(idx);
				
				// obtain and process user's move 
				Move move = player.waitForNextMove(_board);
				if (move.getType() == Move.Type.Quit) {
					break;
				} else {
					processPlayerMove(move);
				}
				Bulletin.getBoard().appendMsg(MessageType.Normal, "Player " +player.getIndex() +
						" made a move: " + move);
				
				// change turn
				int curPlayerIdx = getCurrentPlayerIndex();
				curPlayerIdx++;
				setCurrentPlayerIndex(curPlayerIdx % _players.size());

				// notify observers about change
				setChanged();
				notifyObservers(UPDATED_EVENT);
			}
			
			Bulletin.getBoard().appendMsg(MessageType.GameOver, "Game Over");
			
			setChanged();
			notifyObservers(UPDATED_EVENT);
		}
	}
}
