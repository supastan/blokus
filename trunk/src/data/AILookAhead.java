package data;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class AILookAhead extends AI
{
	PriorityQueue<WeightedMove> weightedMoves;
	Game aGame;
	ArrayList<Player> playerSequence;
	WeightedMove bestmove;

	public AILookAhead(Game g)
	{
		weightedMoves = new PriorityQueue<WeightedMove>();
		aGame = g;
	}
	
	@Override
	public Move nextMove(Board board, Player player) throws RuntimeException
	{
		if (player.getHand().piecesLeft() < 18)
		{
			lookAheadStarter(board.clone(), player);
			return bestmove.getMove();
		}
		else
		{
			Board testBoard = board.clone();
			ArrayList<Move> moves = super.getAvailableMoves(testBoard, player);
			bestmove = new WeightedMove(moves.get(1), 0);
		
			int midWeight;
			int blockWeight;
			int cornerWeight;
			Move aMove;
		
			for(int x = 0; x < moves.size(); x++)
			{
				aMove = moves.get(x);
				midWeight = 0;
				blockWeight = 0;
				cornerWeight = 0;
			
				midWeight = 10 * Math.round(20 - distToMiddle(aMove.getRow(), aMove.getColumn()));
			
				blockWeight = aMove.getPiece().getNumBlocks() * 10;
			
				cornerWeight = aMove.getPiece().getPlayableSpots() * 5;
			
				weightedMoves.add(new WeightedMove(moves.get(x), 
					( -1 * (midWeight + blockWeight + cornerWeight))));	
			}
			return weightedMoves.peek().getMove();
		}	
	}
	
	private void lookAheadStarter(Board aBoard, Player player)
	{
		ArrayList<Player> playerSequence = new ArrayList<Player>();
		int currentPlayerIndex = aGame.getCurrentPlayerIndex();
		ArrayList<Move> moves = super.getAvailableMoves(aBoard, player);
		bestmove = new WeightedMove(moves.get(3), 0);
		
		for (int x = 0; x < 4; x++)
		{
			playerSequence.add(aGame.getPlayer((currentPlayerIndex + x) % 4).clone());
		}
		
		int blah = planMoves(playerSequence, aBoard.clone(), 1, moves.get(0));
	}
	
	private int planMoves(ArrayList<Player> p, Board b, int x, Move startMove)
	{
		ArrayList<Move> moves = super.getAvailableMoves(b, p.get(x));
		//System.out.println("subroutine moves for depth " + x + ": " + moves.size());
		if (x<3)
		{	
			//System.out.println("best move is: " + bestmove.getMove().toString());
			for(int y = 0; y < moves.size(); y++)
			{
				//System.out.println("looking at player: " + p.get(x).getIndex() + ", " + p.get(x).toString());
				Move m = moves.get(y);
				Board t = b.clone();
				//System.out.println("Board B");
				//System.out.println(b.toString());
				t.place(m.getPiece(), m.getRow(), m.getColumn(), p.get(x).clone());
				//System.out.println("Board T- the clone");
				//System.out.println(t.toString());
				//System.out.println("Board B");
				//System.out.println(b.toString());
				//System.out.println("spam, y = " + y);
				int blah = planMoves(p, t, x+1, startMove);
			}
		}
		else
		{
			//System.out.println(moves.size() + "versus " + bestmove.getWeight());
			if (moves.size() > bestmove.getWeight())
			{
				bestmove = new WeightedMove(startMove, moves.size());
			}
		}
		
		return 0;
	}
	
	
	public String toString()
	{
		return ("Look Ahead AI");
	}
}
