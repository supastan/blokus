package test;

import java.util.ArrayList;

import org.junit.Test;

import data.Board;
import data.BoardAnalyzer;
import data.HumanPlayer;
import data.Move;
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
				System.out.println("P1 placed a: " + types[i]);
			}
			catch(RuntimeException e)
			{
				System.out.println("P1 can't place a: " + types[i]);
			}
			
			try
			{
				b.place(new Piece(types[i]), 0, 19, 2);
				System.out.println("P2 placed a: " + types[i]);
			}
			catch(RuntimeException e)
			{
				System.out.println("P2 can't place a: " + types[i]);
			}
			
			try
			{
				b.place(new Piece(types[i]), 19, 19, 3);
				System.out.println("P3 placed a: " + types[i]);
			}
			catch(RuntimeException e)
			{
				System.out.println("P3 can't place a: " + types[i]);
			}
			
			try
			{
				b.place(new Piece(types[i]), 19, 0, 4);
				System.out.println("P4 placed a: " + types[i]);
			}
			catch(RuntimeException e)
			{
				System.out.println("P4 can't place a: " + types[i]);
			}
			
			printBoard(b);
			b = new Board();
	

		}
	}
	
	@Test
	public void testMovesList()
	{
		System.out.println("place test");
		Board b = new Board();
		printBoard(b);
		
		HumanPlayer p1 = new HumanPlayer(1);
		HumanPlayer p2 = new HumanPlayer(2);
		HumanPlayer p3 = new HumanPlayer(3);
		HumanPlayer p4 = new HumanPlayer(4);
		
		
		ArrayList<Move> moves1 = BoardAnalyzer.getAvailableMoves(b, p1);
		ArrayList<Move> moves2 = BoardAnalyzer.getAvailableMoves(b, p2);
		ArrayList<Move> moves3 = BoardAnalyzer.getAvailableMoves(b, p3);
		ArrayList<Move> moves4 = BoardAnalyzer.getAvailableMoves(b, p4);
			
		
		System.out.println("Total moves for p1: " + moves1.size());
		for (int x=0; x< moves1.size(); x++)
		{
			System.out.print(moves1.get(x).getPiece().getType() + ", ");
		}
		
		System.out.println("\nTotal moves for p2: " + moves2.size());
		for (int x=0; x< moves2.size(); x++)
		{
			System.out.print(moves2.get(x).getPiece().getType() + ", ");
		}
		
		System.out.println("\nTotal moves for p3: " + moves3.size());
		for (int x=0; x< moves3.size(); x++)
		{
			System.out.print(moves3.get(x).getPiece().getType() + ", ");
		}
		
		System.out.println("\nTotal moves for p4: " + moves4.size());
		for (int x=0; x< moves4.size(); x++)
		{
			System.out.print(moves4.get(x).getPiece().getType() + ", ");
		}
		
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
