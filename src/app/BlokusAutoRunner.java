package app;

import java.util.Observable;
import java.util.Observer;

import data.AILikesMiddle;
import data.AILookAhead;
import data.AIRandom;
import data.AImidBlocksCorners;
import data.Bulletin;
import data.ComputerPlayer;
import data.Game;
import data.Player;
import data.Game.MessageType;

public class BlokusAutoRunner implements Observer
{
	Game _game;
	
	int gamesPlayed = 0;
	
	int topScore = -100;
	
	String topPlayer;
	
	int worstScore = 100;
	
	String worstPlayer;
	
	int gamesToPlay = 100;
	
	double[] players = new double[4];
	
	public void start()
	{
		System.out.println("Now running game " + gamesPlayed + " of " + gamesToPlay);
		_game = new Game();
		_game.reset();
		
		Bulletin.getBoard().addObserver(this);
		_game.addObserver(this);

		// HACK: add some players
		Player player1 = new ComputerPlayer(1, new AILikesMiddle());
		_game.addPlayer(player1);
		
		Player player2 = new ComputerPlayer(2, new AImidBlocksCorners(15, 10, 10));
		_game.addPlayer(player2);
		
		Player player3 = new ComputerPlayer(3, new AIRandom());
		_game.addPlayer(player3);
		
		Player player4 = new ComputerPlayer(4, new AILookAhead(_game));
		_game.addPlayer(player4);

		_game.start();
	}
	
	public synchronized void update(Observable obj, Object arg)
	{
		if (obj instanceof Bulletin)
		{
			Bulletin b = (Bulletin) obj;
			
			/*
			if(b.getLastType() == MessageType.Score)
			{
				System.out.println(b.getLastMsg());
			}
			*/
			
			if(b.getLastType() == MessageType.GameOver)
			{
				int aScore;
				for (int x = 0; x < 4; x++)
				{
					aScore = _game.getPlayer(x).getScore();
					players[x] += aScore;
					if (aScore > topScore)
					{
						topScore = aScore;
						topPlayer = _game.getPlayer(x).getName();
					}
					else if (aScore < worstScore)
					{
						worstScore = aScore;
						worstPlayer = _game.getPlayer(x).getName();
					}
				}
				
				gamesPlayed++;
				System.out.println("Completed game #" + gamesPlayed + "   ");
				
				if(gamesPlayed == gamesToPlay)
				{
					for (int x = 0; x < 4; x++)
					{
						System.out.println ("score for Player" + (x + 1) + " ("
								+ _game.getPlayer(x).getName() + ") "
								+ (players[x]/gamesPlayed));
					}
					System.out.println("Averaged over " + gamesPlayed + " games");
					System.out.println("The best score was " + topScore + 
							" by " + topPlayer);
					System.out.println("The worst score was " + worstScore + 
							" by " + worstPlayer); 		
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
