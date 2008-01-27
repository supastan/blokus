package data;

import java.util.ArrayList;
import java.util.Observable;


public class Game extends Observable {

	public static final int MAX_NUM_PLAYERS = 4;
	
	/** Game level enumeration */
	public enum GameLevel {
		Easy,
		Hard,
	}
	
	/** Message type enumeration */
	public enum MessageType {
		Normal,
		Error,
		GameOver,
	}
	
	/** Game level */
	private GameLevel _level;
	
	private Board _board;
	
	private ArrayList<Player> _players;
	
	private boolean _isGameOver;
	
	private int _playerIdx;
	
	private void run() {

		while (!_isGameOver) {
			
			// get reference to current player
			int idx = getCurrentPlayerIndex();
			Player player = getPlayer(idx);
			
			// obtain and process user's move 
			boolean changeTurn = false;
			Move move = player.getNextMove(_board);
			changeTurn = processPlayerMove(move);

			// change turn
			if (changeTurn) {
				int curPlayerIdx = getCurrentPlayerIndex();
				curPlayerIdx++;
				setCurrentPlayerIndex(curPlayerIdx % _players.size());
			}

			// notify observers about change
			setChanged();
			notifyObservers();
		}
		
	}
	
	/**
	 * Processes player's move.
	 * 
	 * @param move player's move
	 * @return true if the opponent must take the next turn.
	 */
	private boolean processPlayerMove(Move move) {

		boolean changeTurn = false;
		switch (move.getType()) {
		case Normal:
			break;
			
		case Skip:
			changeTurn = true;
			break;
			
		case Quit:
			break;
		}
		
		return changeTurn;
	}

	
	public Game() {
		_level = GameLevel.Easy;
		_board = new Board();
		_players = new ArrayList<Player>();
	}
	
	
	/**
	 * Returns game level.
	 * 
	 * @return game level.
	 */
	public GameLevel getGameLevel() {
		return _level;
	}
	
	/**
	 * Sets game level to new value.
	 * 
	 * @param level new level
	 */
	public void setGameLevel(GameLevel level) {
		_level = level;
		StatusBoard.getStatusBoard().appendMessage(MessageType.Normal, "Game level is now set to " + _level + ".");
	} 
	
	public void reset() {
		_board.reset();
		_players.clear();
		_isGameOver = false;
		
		// set defaults
		_playerIdx = 0;
	}
	
	/**
	 * Returns player for the specifed index.
	 * 
	 * @param idx player index
	 * @return player for the specifed index.
	 */
	public Player getPlayer(int idx) {
		return _players.get(idx);
	}
	
	/**
	 * Returns index for current player.
	 * 
	 * @return current player index
	 */
	public int getCurrentPlayerIndex() {
		return _playerIdx;
	}
	
	/**
	 * Sets current player index to a new value.
	 * 
	 * @param idx new player index
	 */
	public void setCurrentPlayerIndex(int idx) {
		if (idx < 0 || idx >= _players.size() ) {
			throw new IndexOutOfBoundsException("idx=" + idx);
		}
		_playerIdx = idx;
	}
	
	public void start() {
		StatusBoard.getStatusBoard().appendMessage(MessageType.Normal, "New game has started.");

		// create players
		_players.add(new HumanPlayer());
		_players.add(new ComputerPlayer());
		_players.add(new ComputerPlayer());
		_players.add(new ComputerPlayer());
		
		// notify observers to refresh
		setChanged();
		notifyObservers();

		// run game
		run();
	}
	
	public void abort() {
		// get reference to current player
		int idx = getCurrentPlayerIndex();
		Player player = getPlayer(idx);

		player.abort();
		
	}
	
	public boolean isGameOver() {
		return _isGameOver;
	}

}
