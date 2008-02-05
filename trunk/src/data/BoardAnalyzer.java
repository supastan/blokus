package data;

import java.util.List;

public class BoardAnalyzer {

	/**
	 * Returns list of all available moves for given player for the current
	 * board configuration.
	 * 
	 * @param board
	 * @param player
	 * @return
	 */
	public static List<Move> getAvailableMoves(Board board, Player player) {
		return null;
	}

	/**
	 * Determines if a Piece can be placed legally at a specific location on the Board.
	 * 
	 * @param board
	 * @param piece
	 * @param row
	 * @param col
	 * @param player
	 * @return
	 */
	public static boolean canPlace(Board board, Piece piece, int row, int col, int player)
	{
		boolean cornerMatched = false;
		int bRow = 0;
		int bCol = 0;
	
		for (int i= 0; i < piece.getHeight(); i++)
		{
			for (int j= 0; j < piece.getWidth(); j++)
			{
				if (piece.hasBlock(i, j))
				{
					bRow = row + i;
					bCol = col + j;
					//System.out.println("row: " + bRow + "    col: " +bCol);
					
					if (bRow >= 0 && bCol >= 0 && board._blocks[bRow][bCol] != 0)
					{
						if(board._blocks[bRow][bCol] < 5)
						{
							return false;
						}
						
						if(board._blocks[bRow][bCol] - 4 == player && piece.getBlockType(i, j) == 2)
						{
							cornerMatched = true;
						}
					}
					
				}
			}
		}
		
		return cornerMatched;
	}
}
