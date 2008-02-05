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
		
		switch(player) {
		
		case 2:
			col = col - piece.getWidth() + 3;
			break;
		
		case 3:
			col = col - piece.getWidth() + 3;
			row = row - piece.getHeight() + 3;
			break;
		
		case 4:
			row = row - piece.getHeight() + 3;
			break;
			
		default:
			//player one doesn't need adjusting.
		}
	
		for (int i= 0; i < piece.getHeight(); i++)
		{
			for (int j= 0; j < piece.getWidth(); j++)
			{
				if (piece.hasBlock(i, j))
				{
					bRow = row + i;
					bCol = col + j;
					/**
					System.out.println("row: " + bRow + "    col: " + bCol + 
							"     boardstate: " + board.getBlock(bRow, bCol) +
							"     piecestate: " + piece.getBlockType(i, j) +
							"     player: " + player);
					*/
					if (inRange(bRow, bCol) && board.getBlock(bRow, bCol) != 0)
					{
						if(board.getBlock(bRow, bCol) < 5)
						{
							return false;
						}
						
						if((board.getBlock(bRow, bCol) - 4 == player) 
								&& (piece.getBlockType(i, j) == 2))
						{
							cornerMatched = true;
						}
					}
					
				}
			}
		}
		return cornerMatched;
	}
	
	private static boolean inRange(int row, int col)
	{
		return (row >= 0 && row <= 19 && col >= 0 && col <= 19);
	}
}
