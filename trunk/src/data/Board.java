package data;




public class Board {

	public static final int X_DIMENSION = 20;
	
	public static final int Y_DIMENSION = 20;

	int[][] _blocks;
	
	
	/** 
	 * Creates an empty board.
	 */
	public Board() {
		_blocks = new int[Y_DIMENSION][X_DIMENSION];
	}
	
	public synchronized int getBlock(int row, int col) {
		return _blocks[row][col];
	}
	
	/**
	 * Reset Board state.
	 */
	public synchronized void reset() {
		_blocks = new int[Y_DIMENSION][X_DIMENSION];
	}
	
	/**
	 * Places a piece on the board. 
	 * 
	 * @param piece Blokus piece to place.
	 * @param index Player index.
	 */
	public synchronized void place(Piece piece, int row, int col, int index) {
		
		if (BoardAnalyzer.canPlace(this, piece, row, col, index)) {
			for (int i=0; i < piece.getHeight(); i++) {
				for (int j=0; j < piece.getWidth(); j++) {
					if (piece.hasBlock(i, j)) {
						_blocks[row+i][col+j] = index;
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
