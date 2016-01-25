// https://drive.google.com/open?id=164YMGnMI6ZMPMfVnBC_5N-iMUpU7WSk-Yb83Ggr98mE&authuser=0

package SudokuSolver;


/**
 * Runner / test class for Gameboard
 * 
 * @author ingrid
 */
public class SudokuRunner {
	
	public static void main(String[] args)
	{
		// ****************TEST GAMEBOARD**
		Gameboard board = new Gameboard();
		
		// Copy constructor
		Gameboard newBoard = board;
		System.out.println(newBoard);
		
		System.out.println("TOSTRING()");
		System.out.println(board.toString());
		
		System.out.println("GET(R, C)");
		System.out.println(board.get(0, 0));
		System.out.println(board.get(8, 6) + "\n");
		//System.out.println(board.get(9, 0));
		
		// canPlace, place
		
		//System.out.println(board.get(8, 0));
		//System.out.println(board.canPlace(8, 0, 1));
		
		board.place(8, 0, 1);
		System.out.println(board);
		
		// same value (should return false)
		System.out.println(board.canPlace(7, 1, 1)); // same square
		System.out.println(board.canPlace(8, 3, 1)); // same row
		System.out.println(board.canPlace(2, 0, 1)); // same column

		// different value (should return true)
		System.out.println(board.canPlace(7, 1, 2));
		System.out.println(board.canPlace(8, 3, 6));
		System.out.println(board.canPlace(2, 0, 2));
		
		
		System.out.println("\n");
	}

}
