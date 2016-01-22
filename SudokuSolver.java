package SudokuSolver;

public class SudokuSolver {
	
	static Stack<Gameboard> stack = new LinkedList<Gameboard>();

	public static void main(String[] args)
	{
		Gameboard board = stack.pop();
		
		solveBoard();
	}
	
	
	/**
	 * precondition- board is solvable
	 * @return
	 */
	private static Gameboard solveBoard()
	{
		while(true)
		{
			Gameboard board = stack.pop();
			
			if(board.solved())
			{
				return board;
			}
			
			int[] temp = board.getMostConstrained();
			int rowMC = temp[0];
			int colMC = temp[1];
			
			//TODO finish
			
			/* impossible to solve
			else if(rowMC == -1 && colMC == -1)
			{
				throw 
			}*/
			
			else
			{
				board = stack.pop();
				board.solveBoard();
			}
		}
		
	}
	

}
