//package SudokuSolver;

/**
 * SudokuSolver class
 * Run this file to solve Sudoku
 * 
 * @author ingrid
 *
 */
public class SudokuSolver {
	
	private static Stack<Gameboard> stack = new LinkedList<Gameboard>();
		
	public static void main(String[] args)
	{
		Gameboard board = new Gameboard();
		
		stack.push(board);
		
		solve();
	}
	
	
	/**
	 * Attempts to solve the board
	 * 
	 * Brute force approach: finds most constrained spot and attempts to solve by placing each possible value until
	 * the board is solved
	 */
	public static void solve()
	{
		boolean solved = false;
		while(solved == false)
		{			
			// unsolveable
			if(stack.isEmpty())
			{
				System.out.println("UNSOLVEABLE");
				return;
			}
			
			Gameboard board = stack.pop();
			
			// solved
			if(board.solved())
			{
				System.out.println("SOLVED" + "\n" + "\n" + board);
				solved = true;
			}
			
			// NOT solved
			// find most constrained
			int[] mostConstrained = board.getMostConstrained();
			int row = mostConstrained[0];
			int col = mostConstrained[1];
			
			// make sure it's solveable
			if(row + col >= 0)
			{
				// push possible boards onto stack
				for(int i = 1; i < 10; i++)
				{
					if(board.canPlace(row, col, i))	
					{
						Gameboard newBoard = new Gameboard(board);
						newBoard.place(row, col, i);
						stack.push(newBoard);
					}
				}
			}
		}
	}

}
