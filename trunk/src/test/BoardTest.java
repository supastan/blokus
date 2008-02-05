package test;

import static org.junit.Assert.fail;

import org.junit.Test;

import data.Board;
import data.Piece;

/**
 * JUnit test cases for data.Board class.
 * 
 * @author Yong Shin
 *
 */

public class BoardTest {

	@Test
	public void testPlace()
	{
		System.out.println("place test");
		Board b = new Board();
		printBoard(b);
		Piece.Type[] types = Piece.Type.values();
		for (int i = 0; i < types.length; i++)
		{
			try
			{
				b.place(new Piece(types[i]), 0, 0, 1);
				System.out.println("I placed a: " + types[i]);
				printBoard(b);
				b = new Board();
			}
			catch(RuntimeException e)
			{
				System.out.println("can't place a: " + types[i]);
			}
		}
			
		//fail("Not yet implemented");
	}

	
	private void printSeparatorLine() {
		for (int i=0; i < Board.X_DIMENSION; i++) {
			System.out.print(" -");
		}
		System.out.println();
	}

	private void printBoardLine(Board board, int row) {
		for (int i=0; i < Board.X_DIMENSION; i++) {
			
			System.out.print("|" + board.getBlock(row, i));
		}
		System.out.println("|");
	}
	
	private void printBoard(Board board) {
		printSeparatorLine();
		for (int row=0; row < Board.Y_DIMENSION; row++) {
			printBoardLine(board, row);
		}
		printSeparatorLine();
	}
}
