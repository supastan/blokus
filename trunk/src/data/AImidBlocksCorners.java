package data;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class AImidBlocksCorners extends AI
{
	PriorityQueue<WeightedMove> weightedMoves;

	public AImidBlocksCorners()
	{
		weightedMoves = new PriorityQueue<WeightedMove>();
	}

	@Override
	Move nextMove(Board board, Player player) throws RuntimeException
	{
		int midWeight;
		int blockWeight;
		int cornerWeight;
		Move aMove;
		
		
		weightedMoves.clear();
		
		ArrayList<Move> moves = super.getAvailableMoves(board, player);
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
		/*
		System.out.println("AI MBC moves: " + weightedMoves.size() + " top candidate: "
				+ weightedMoves.peek().getWeight() + "  move: " + 
				weightedMoves.peek().getMove().toString());  */
		
		return weightedMoves.peek().getMove();
	}
	
	public String toString()
	{
		return ("Mid/blocks/corners AI");
	}

}
