package data;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class AImidBlocksCorners extends AI
{
	PriorityQueue<WeightedMove> weightedMoves;
	int bwFactor;
	int mwFactor;
	int cwFactor;

	public AImidBlocksCorners(int mw, int bw, int cw)
	{
		weightedMoves = new PriorityQueue<WeightedMove>();
		bwFactor = bw;
		mwFactor = mw;
		cwFactor = cw;
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
		//System.out.println("I'm considering " + moves.size() + " moves");
		for(int x = 0; x < moves.size(); x++)
		{
			aMove = moves.get(x);
			midWeight = 0;
			blockWeight = 0;
			cornerWeight = 0;
			
			midWeight = mwFactor * Math.round(20 - distToMiddle(aMove.getRow(), aMove.getColumn()));
			
			blockWeight = aMove.getPiece().getNumBlocks() * bwFactor;
			
			cornerWeight = aMove.getPiece().getPlayableSpots() * cwFactor;
			
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
		return ("Mid/blocks/corners AI, " + "M" + mwFactor + ", B" + bwFactor + ", C" + cwFactor);
	}

}
