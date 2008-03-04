package data;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class AILikesMiddle extends AI
{
	
	PriorityQueue<WeightedMove> weightedMoves;

	public AILikesMiddle()
	{
		weightedMoves = new PriorityQueue<WeightedMove>();
	}

	@Override
	Move nextMove(Board board, Player player) throws RuntimeException
	{
		int weight;
		Move aMove;
		
		weightedMoves.clear();
		
		ArrayList<Move> moves = super.getAvailableMoves(board, player);
		for(int x = 0; x < moves.size(); x++)
		{
			aMove = moves.get(x);
			weight = 0;
			
			weight = -10 * Math.round(20 - distToMiddle(aMove.getRow(), aMove.getColumn()));;
			
			weightedMoves.add(new WeightedMove(moves.get(x), weight));	
		}
		/*
		System.out.println("AI middle moves: " + weightedMoves.size() + " top candidate: "
				+ weightedMoves.peek().getWeight() + "  move: " + 
				weightedMoves.peek().getMove().toString());  */
		
		return weightedMoves.peek().getMove();
	}
	
	public String toString()
	{
		return ("Middle preference AI");
	}

}
