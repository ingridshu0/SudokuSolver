package SudokuSolver;

import java.util.Scanner;
import java.io.*;

public class Gameboard {
	
	// 
	private int[][] board = new int[9][9];
	
	// Constructor
	public Gameboard()
	{
		Integer[] vals = readCSV();
		
		/*
		// test- print array (vals)
		String str = "";
		// counts every third line
		int counter = 0;
		for(int i = 0; i < vals.length; i++)
		{
			if((i + 1) % 3 == 0)
			{
				str += vals[i];
				if((i + 1) % 9 == 0)
				{
					str += "\n";
					counter++;
					
					if(counter % 3 == 0)
					{
						str += "\n";
					}
				}
				else
				{
					str += "   ";
				}
			}
			else
			{
				str += vals[i];
				str += ", ";
			}
		}
		System.out.println(str);
		*/
		
		// inserts given values
		int valIndex = 0;
		
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board[0].length; j++)
			{
				board[i][j] = vals[valIndex];
				//System.out.print(board[i][j]);
				valIndex++;
			}
			//System.out.println("\n");
			
		}
		
		// prints board (make sure values r there)
		
	}
	
	private Integer[] readCSV()
	{
		String pathname = "SudokuBoard.csv";
		File file = new File(pathname);	
		Scanner input = null;
		try
		{
			input = new Scanner(file);
		}
		catch (FileNotFoundException ex)
		{
			System.out.println("Cannot open " + pathname );
			System.exit(1);
		}
		
		// returns everything given in the board
		Integer[] vals = new Integer[81];
		int counter = 0;
		
		while( input.hasNextLine() )
		{
			String inp = input.nextLine();
			for(String s : inp.split(","))
			{
				// suitable value for board
				if(Integer.parseInt(s) == 1 || Integer.parseInt(s) == 2 || Integer.parseInt(s) == 3 || Integer.parseInt(s) == 4 || Integer.parseInt(s) == 5 || Integer.parseInt(s) == 6 || Integer.parseInt(s) == 7 || Integer.parseInt(s) == 8 || Integer.parseInt(s) == 9)
					vals[counter] = Integer.parseInt(s);
				
				// not suitable value --> empty spot
				else
					vals[counter] = 0;
				
				counter++;
			}
		}
		
		return vals;
	}
	
	
	/**
	 * place numeral n at position (r, c)
	 * 
	 * @param r
	 * @param c
	 * @param n
	 */
	public void place(int r, int c, int n)
	{
		if(canPlace(r, c, n))
		{
			board[r][c] = n;
		}
		
		// what TODO if cannot place?
	}
	
	
	/**
	 * returns a string representation of the board
	 * 
	 * @return string representation of the gameboard
	 */
	public String toString()
	{	
		// test- print array
		String s = "";
		
		// i = row index
		for(int i = 0; i < board.length; i++)
		{
			// j = column index
			for(int j = 0; j < board[0].length; j++)
			{				
				// adds value
				s += board[i][j];
				s += " ";
				
				if((j + 1) % 3 == 0)
				{
					if((j + 1) % 9 == 0)
					{
						// additional new line
						if((i + 1) % 3 == 0)
						{
							s += "\n";
						}
						
						// new line
						s += "\n";
					}
					
					else
					{
						s += "  ";
					}
				}
				
			}
		}
		
		return s;
	}
	
	
	/**
	 * return the numeral at position (r, c)
	 * 
	 * @param row
	 * @param column
	 * @return value at the given row and column
	 */
	public int get(int r, int c)
	{
		return board[r][c];
	}
	
	/**
	 * tests to see if the board has an empty spot at r, c
	 * then confirms if n can be placed there
	 * 
	 * @param r
	 * @param c
	 * @param n
	 * @return true if the board can allow placing n at (r, c)
	 */
	public boolean canPlace(int r, int c, int n)
	{
		// is empty spot
		if(board[r][c] == 0)
		{
			if(canPlaceRow(r, n) && canPlaceCol(c, n) && canPlaceSquare(r, c, n))
				return true;
		}
		
		return false;
	}
	
	
	/**
	 * checks to see if n can be placed in a row r
	 * 
	 * @param r
	 * @param n
	 * @return
	 */
	private boolean canPlaceRow(int r, int n)
	{
		for(int i = 0; i < board[r].length; i++)
		{
			// item exists already
			if(board[r][i] == n)
			{
				return false;
			}
		}
		
		return true;
	}
	
	
	/**
	 * checks to see if n can be placed in a column c
	 * 
	 * @param c
	 * @param n
	 * @return
	 */
	private boolean canPlaceCol(int c, int n)
	{
		for(int i = 0; i < board.length; i++)
		{
			// item exists already
			if(board[i][c] == n)
			{
				return false;
			}
		}
		
		return true;
	}
	
	
	/**
	 * checks to see if n can be placed in square containing r and c
	 * 
	 * @param r
	 * @param c
	 * @param n
	 * @return
	 */
	private boolean canPlaceSquare(int r, int c, int n)
	{
		// starting row and column of a square
		int tempRow;
		int tempCol;
		
		// find starting row
		if(r < 3 && r >= 0)
		{
			tempRow = 0;
		}
		
		else if(r < 6 && r >= 3)
		{
			tempRow = 3;
		}
		
		else
		{
			tempRow = 6;
		}
		
		// find starting column
		if(c < 3 && c >= 0)
		{
			tempCol = 0;
		}
		
		else if(c < 6 && c >= 0)
		{
			tempCol = 3;
		}
		
		else
		{
			tempCol = 6;
		}
		
		// check
		for(int row = tempRow; row < tempRow + 3; row++)
		{
			for(int col = tempCol; col < tempCol + 3; col++)
			{
				// item exists already
				if(board[row][col] == n)
				{
					return false;
				}
			}
		}
		
		return true;
		
	}
	
	/**
	 * checks if there are blank spots on the board
	 * 
	 * @return true if there are no blank spots (0s) on the board
	 */
	public boolean solved()
	{
		for(int r = 0; r < board.length; r++)
		{
			for(int c = 0; c < board[0].length; c++)
			{
				// blank spot
				if(board[r][c] == 0)
					return false;
			}
		}
		
		return true;
	}
	
	
	/**
	 * finds most constrained spot
	 * 
	 * @return row, col of most constrained spot
	 * @return -1, -1 if there is no constrained spot (impossible to solve puzzle)
	 */
	public int[] getMostConstrained()
	{
		
		int rowMC = -1;
		int colMC = -1;
		int mostConstrained = -1;
		int temp;
		
		for(int r = 0; r < board.length; r++)
		{
			for(int c = 0; c < board[0].length; c++)
			{
				temp = findConstraint(r, c);
				if(temp > mostConstrained)
				{
					mostConstrained = temp;
					rowMC = r;
					colMC = c;
				}
			}
		}
		
		// returns row, col of most constrained spot
		int[] mCCoord = new int[2];
		mCCoord[0] = rowMC;
		mCCoord[1] = colMC;
		return mCCoord;
	}
	
	
	/**
	 * helped method to find the constraint for a given spot on the board
	 * 
	 * @param row
	 * @param col
	 * @return how constrained given row, col is
	 */
	private int findConstraint(int row, int col)
	{
		// how many numbers can be placed at given index
		int counter = 0; //TODO can u initialize it here?
		
		for(int i = 1; i <= 9; i++)
		{
			if(canPlace(row, col, i))
			{
				counter++;
			}
		}
		
		return counter;
	}
}
