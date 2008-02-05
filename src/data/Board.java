package data;




public class Board {

	public static final int X_DIMENSION = 20;
	
	public static final int Y_DIMENSION = 20;

	int[][] _blocks;
	
	
	/** 
	 * Creates an empty board.
	 */
	public Board()
	{
		_blocks = new int[Y_DIMENSION][X_DIMENSION];
		_blocks[0][0] = 5;
		_blocks[0][19] = 6;
		_blocks[19][19] = 7;
		_blocks[19][0] = 8;
	}
	
	public synchronized int getBlock(int row, int col) {
		return _blocks[row][col];
	}
	
	/**
	 * Reset Board state.
	 */
	public synchronized void reset() {
		_blocks = new int[Y_DIMENSION][X_DIMENSION];
		_blocks[0][0] = 5;
		_blocks[0][19] = 6;
		_blocks[19][19] = 7;
		_blocks[19][0] = 8;
	}
	
	/**
	 * Places a piece on the board. 
	 * 
	 * @param piece Blokus piece to place.
	 * @param index Player index.
	 */
	public synchronized void place(Piece piece, int row, int col, int index)
	{
		int bRow, bCol;
		if (BoardAnalyzer.canPlace(this, piece, row - 1, col - 1, index))
		{
			int placement = 0;
			for (int i=0; i < piece.getHeight(); i++)
			{
				bRow = row + i - 1;
				for (int j=0; j < piece.getWidth(); j++) 
				{
					bCol = col + j - 1;
					if (bRow >= 0 && bCol >= 0 && piece.hasBlock(i, j) && 
							(_blocks[bRow][bCol] == 0 || _blocks[bRow][bCol] == (index + 4))) 
					{
						placement = piece.getBlockType(i, j);
						if (placement == 3)
						{
							placement = 4;
						}
						else
						{
							placement = 0;
						}
						_blocks[bRow][bCol] = placement + index;
					}
				}
			}
		} else {
			throw new RuntimeException("Can't place the piece on the board.");
		}
		
	}

	@Override
	public synchronized Board clone() {
		Board copy = new Board();
		copy._blocks = _blocks.clone();
		return copy;
	}
}
