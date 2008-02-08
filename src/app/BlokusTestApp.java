package app;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import data.AI;
import data.AIRandom;
import data.Board;
import data.Bulletin;
import data.ComputerPlayer;
import data.Game;
import data.Hand;
import data.Move;
import data.Player;

public class BlokusTestApp implements Observer {

	/** Stores reference to Game */
	private Game _game;
	
	private void printSeparatorLine() {
		for (int i=0; i < Board.X_DIMENSION; i++) {
			System.out.print(" -");
		}
		System.out.println();
	}

	private void printBoardLine(Board board, int row) {
		for (int i=0; i < Board.X_DIMENSION; i++) {
			
			System.out.print("|" + board.getBlock(row, i));
		}
		System.out.println("|");
	}
	
	private void printBoard(Board board) {
		printSeparatorLine();
		for (int row=0; row < Board.Y_DIMENSION; row++) {
			printBoardLine(board, row);
		}
		printSeparatorLine();
	}
	
	private void processUserInput() {

		while (true) {

			System.out.print("(s->step, q->quit, n->new game, h->see hand) ? ");
			boolean done = true;
			
			Scanner scan = new Scanner(System.in);
			String input = scan.next();
			
			switch (input.charAt(0)) {
			case 'q':
				System.exit(0);
				break;
				
			case 'n':
				start();
				break;
				
			case 's':
				Move move = _game.getCurrentPlayer().getNextMove(_game.getBoard());
				_game.getCurrentPlayer().putNextMove(move);
				break;
				
			case 'h':
				Hand hand = _game.getCurrentPlayer().getHand();
				Iterator theHand = hand.getIterator(); 
				while (theHand.hasNext())
				{
					System.out.println(theHand.next().toString());
				}
				
			default:
				System.out.println("\nInvalid command.");
				done = false;
			}
			
			if (done)
				return;
				
		}
	}
	
	/** 
	 * Creates a new Blokus game and sets up the Game. 
	 */
	public BlokusTestApp() {
		
		_game = new Game();
		
		// add self as observers
		Bulletin.getBoard().addObserver(this);
		_game.addObserver(this);
	}

	/**
	 * Starts game by showing UI.
	 */
	public void start() {
		_game.reset();

		// HACK: add some players
		
		AI anAI;
		for (int i = 0; i < 4; i++) {
			anAI = new AIRandom();
			Player player = new ComputerPlayer(i+1, anAI);
			player.setAutoProgress(false);
			_game.addPlayer(player);
		}
		
		_game.start();
	}

	@Override
	public synchronized void update(Observable obj, Object arg) {
		if (obj instanceof Bulletin) {
			Bulletin b = (Bulletin) obj;
			System.out.println(b.getLastMsg());
		}
		if (obj instanceof Game) {
			printBoard(_game.getBoard());
			processUserInput();
		}
	}
	
	/**
	 * Program execution entry point.
	 * 
	 * @param args command line arguments (unused)
	 */
	public static void main(String[] args) {
		BlokusTestApp app = new BlokusTestApp();
		app.start();
	}

}
