package app;

import gui.BlokusUI;
import data.Game;

public class BlokusApp {

	/** Stores reference to BlokusUI */
	private BlokusUI _ui;
	
	/** Stores reference to Game */
	private Game _game;
	
	/** 
	 * Creates a new Blokus game and sets up the Game. 
	 */
	public BlokusApp() {
		_game = new Game();
		_ui = new BlokusUI(_game);
		_ui.display();
	}

	/**
	 * Starts game by showing UI.
	 */
	public void start() {
		_game.reset();
		_game.start();
		_ui.refresh();
	}

	/**
	 * Program execution entry point.
	 * 
	 * @param args command line arguments (unused)
	 */
	public static void main(String[] args) {
		BlokusApp app = new BlokusApp();
		app.start();
	}
		
}
