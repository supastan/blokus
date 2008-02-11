package data;

public class WeightedMove implements Comparable<WeightedMove> {
	
	private Move move;
	
	private int weight;
	
	public WeightedMove(Move m, int w)
	{
		move = m;
		weight = w;
	}
	
	public Move getMove()
	{
		return move;
	}
	
	public int getWeight()
	{
		return weight;
	}
	
	@Override
	public int compareTo(WeightedMove move)
	{
	    return this.getWeight() - move.getWeight();  
	}

}
