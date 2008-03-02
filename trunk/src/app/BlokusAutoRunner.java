package app;

import gui.TestPlayfield;

import java.util.Observable;
import java.util.Observer;

import data.AILikesMiddle;
import data.AIRandom;
import data.AImidBlocksCorners;
import data.Bulletin;
import data.ComputerPlayer;
import data.Game;
import data.Move;
import data.Player;
import data.Game.MessageType;

public class BlokusAutoRunner implements Observer
{
	Game _game;
	
	private TestPlayfield playfield;
	
	int gamesPlayed = 1;
	
	int[] players;
	
	public void start()
	{
		_game = new Game();
		_game.reset();
		
		Bulletin.getBoard().addObserver(this);
		_game.addObserver(this);

		// HACK: add some players
		Player player1 = new ComputerPlayer(1, new AILikesMiddle());
		//player1.setAutoProgress(false);
		_game.addPlayer(player1);
		
		Player player2 = new ComputerPlayer(2, new AImidBlocksCorners());
		//player2.setAutoProgress(false);
		_game.addPlayer(player2);
		
		Player player3 = new ComputerPlayer(3, new AILikesMiddle());
		//player3.setAutoProgress(false);
		_game.addPlayer(player3);
		
		Player player4 = new ComputerPlayer(4, new AIRandom());
		//player4.setAutoProgress(false);
		_game.addPlayer(player4);

		playfield = new TestPlayfield(_game);
		_game.start();
	}
	
	public synchronized void update(Observable obj, Object arg)
	{
		if (obj instanceof Bulletin)
		{
			playfield.repaint();
			Bulletin b = (Bulletin) obj;
			System.out.println(b.getLastMsg());
			
			if(b.getLastType() == MessageType.GameOver)
			{
				for (int x = 0; x < 4; x++)
				{
					players[x] += _game.getPlayer(x).getScore();
				}
				
				gamesPlayed++;
				
				if(gamesPlayed == 2)
				{
					for (int x = 1; x < 5; x++)
					{
						System.out.println ("score for P" + x + " " + (players[x]/gamesPlayed));
					}
				}
				else
				{
					start();
				}
			}

		}
	}
	
	public static void main(String[] args) 
	{
		BlokusAutoRunner app = new BlokusAutoRunner();
		app.start();
	}
}
