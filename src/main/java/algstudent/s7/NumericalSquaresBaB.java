package algstudent.s7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

import algstudent.s7.utils.BranchAndBound;
import algstudent.s7.utils.Node;


public class NumericalSquaresBaB extends BranchAndBound {	
	
    public NumericalSquaresBaB(NumericalSquaresBoard board) {
    	rootNode = board; //we create the puzzle to start playing
    }
}


@SuppressWarnings("unused")
class NumericalSquaresBoard extends Node {
	
	//---- Constants ----
	private static final String Q_MARK = "?";
	private static final String EQ_SIGN = "=";

	private static final int D = 1;
	private static final int M = 2;
	private static final int S = 3;
	private static final int R = 4;
	private static final int NO_OP = -1;
	
	private static final int MAX_NUM = 9;
	private static final int MIN_NUM = 0;
	
	//---- Attributes ----
	private static int size; //Size of the board
	
	private int [][] 
			board,
			rowOps,
			colOps; //Matrixes for the board and board operations
	
	private int []
			rowRes,
			colRes; //Array for the results
	
	private boolean [][] blocked; //Matrix to account for the given numbers
	
	private int row,
				column;
	
	/**
	 * Constructor for Square Board objects (root node)
	 * @param n Size of the board
	 */
	public NumericalSquaresBoard(String path) { 
		readFromFile(path);
		
		row = column = 0;
		
    	calculateHeuristicValue();
	}
	
	/**
	 * Constructor for a given set of parameters
	 * @param board
	 * @param rowOps
	 * @param colOps
	 * @param rowRes
	 * @param colRes
	 * @param blocked
	 * @param depth
	 * @param parentID
	 * @param row
	 * @param column
	 */
    public NumericalSquaresBoard(int[][] board, int[][] rowOps, int[][] colOps,
						    		int[] rowRes, int[] colRes, boolean[][] blocked,
						    			int depth, UUID parentID, int row, int column) {
    	this.board = board;
    	this.rowOps = rowOps;
    	this.colOps = colOps;
    	
    	this.rowRes = rowRes;
    	this.colRes = colRes;
    	
    	this.blocked = blocked;
    	
    	this.depth = depth;
    	this.parentID = parentID;
    	
    	this.row = row;
    	this.column = column;
    	
    	calculateHeuristicValue();
    }
	
	/**
	 * Inserts the values of a line from the pyramid 
	 * It is call once per every row of the pyramid to initialize all the values
	 * @param values Values of a row of the pyramid
	 * @param row Number of the current row
	 */
	public void insertValues(int[] values, int row) {
		for (int i=0; i < size; i++) {
			if (values[i] == NO_OP)
				board[row][i] = 0;
			else
				board[row][i] = values[i];
		}
	}
		

    /**
     * Counts the number of blanks that are not yet filled
     */
    @Override
    public void calculateHeuristicValue() {
    	int counter = 0;
    	if (prune())
    		heuristicValue = Integer.MAX_VALUE;
    	else {
    		for (int i = 0; i < size; i++) {
    			for (int j = 0; j < size; j++) {
    				if (board[i][j] == NO_OP)
    					counter++;
    			}
    		}
    		
    		heuristicValue = counter;
    	}
    }
    
	/**
	 * Checks if we should prune when the conditions are not longer met
	 * @return True if we should prune. False otherwise
	 */
	private boolean prune() {
		return !(isRowSolution(row) && isColSolution(column));
	}
	
	private boolean isRowSolution(int row) {
		int opC = 0, res = board[row][0];
		
		if (res == NO_OP) return true;
		
		for (int j = 1; j < board.length; j++) {
			if (board[row][j] == NO_OP) return true;
			
			switch (rowOps[row][opC++]) {
		        case M -> res *= board[row][j];
		        case S -> res += board[row][j];
		        case R -> res -= board[row][j];
		        default -> {
		            if (board[row][j] == 0)
		                return false;
		            if (res % board[row][j] != 0) return false;
		            
		            res /= board[row][j];
		        }
			}
		}
		
		return res == rowRes[row];
	}
	
	private boolean isColSolution(int col) {
		int opC = 0, res = board[0][col];
		
		if (res == NO_OP) return true;
		
		for (int j = 1; j < board.length; j++) {
			if (board[j][col] == NO_OP) return true;
		
			switch (colOps[col][opC++]) {
		        case M -> res *= board[j][col];
		        case S -> res += board[j][col];
		        case R -> res -= board[j][col];
		        default -> {
		            if (board[j][col] == 0)
		                return false;
		            
		            if (res % board[j][col] != 0) return false;
		            
		            res /= board[j][col];
		        }
			}
		}
		
		return res == colRes[col];
	}
	
	@Override
	public boolean isSolution() {
		return heuristicValue == 0;
	}
    
	/**
	 * To get the children of the current node. They 
     * point to their parent through the parentID
	 */
	@Override
	public ArrayList<Node> expand() {
	    ArrayList<Node> result = new ArrayList<Node>();
	    
		int[][] newBoard;
		NumericalSquaresBoard temp;
		
		int nRow = row, nCol = column;
		while (board[nRow][nCol] != NO_OP) {
			nCol++;
			
			if (nCol == size) {
				nCol = 0;
				
				nRow++;
			} 
		}
		
		for (int k = MIN_NUM; k <= MAX_NUM; k++) {
			newBoard = copyBoard(nRow, nCol, k);
			temp = new NumericalSquaresBoard(newBoard, rowOps, colOps, rowRes, colRes, blocked, depth + 1, this.getID(), nRow, nCol);
			result.add(temp);
		}
		
		
		return result;
	}
	
	private int[][] copyBoard(int row, int column, int k) {
		int[][] newBoard = new int[size][size];
		
		for (int i = 0; i < size; i++) 
			for (int j = 0; j < size; j++)
				newBoard[i][j] = board[i][j];				      
		
		newBoard[row][column] = k;
		
		return newBoard;
	}

	
	// ---- Utils ----
	private void readFromFile(String fileName) {
		BufferedReader reader = null; 	
		try {
			reader = new BufferedReader(new FileReader(fileName)); 	
			boolean isSizeLine = true,
					isNumberLine = true;
			int row = 0, opC = 0;
			while (reader.ready()) {
				String line = reader.readLine();
				
				//Get the size
				if (isSizeLine) {
					NumericalSquaresBoard.size = Integer.parseInt(line);
					
					this.board = new int[size][size];
					this.blocked = new boolean[size][size];
                    this.colOps= new int[size][size-1];
                    this.rowOps = new int[size][size-1];
                    this.colRes = new int[size];
                    this.rowRes = new int[size];
                    
                    isSizeLine = false;	
                    
					continue;
				}
				
				if (row == size) {
					parseColRes(reader.readLine());
					
					break;
				}

				if (isNumberLine) {
					parseRow(line, row++);
					
					isNumberLine = false;
				}
				else {
					parseCol(line, opC++);
					
					if (opC == size - 1) opC = 0;
					
					isNumberLine = true;
				}
			}
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} 
	}
	
	private void parseColRes(String line) {
		String[] arr = line.split(" ");
		for (int i = 0; i < arr.length; i++)
			this.colRes[i]= Integer.parseInt(arr[i]);
	}
	
	private void parseCol(String line, int col) {
		String[] arr = line.split(" ");
		for (int i = 0; i < arr.length; i++)
			this.colOps[i][col] = getOp(arr[i]);
	}

	private void parseRow(String line, int i) {
		String[] arr = line.split(" ");
		
		int j = 0,opC = 0;
		for (int c = 0; c < arr.length; c++) {
			String character = arr[c];
			
			if (Character.isDigit(character.charAt(0))) {
				blocked[i][j] = true;
				board[i][j++] = Integer.parseInt(character);
			}
			else {
				switch(character) {
					case Q_MARK:{
						board[i][j++] = NO_OP;
						
						break;
					}
					
					case EQ_SIGN:{
						rowRes[i] = Integer.parseInt(String.valueOf(arr[c + 1]));
						
						return;
					}
					
					default:{
						rowOps[i][opC++] = getOp(character);
						
						if (opC == size - 1) opC = 0;
						
						break;
					}
				}
			}
		}
	}
	
	private int getOp(String c) {
		int op = switch(c) {
			case "*" -> M;
			case "/" -> D;
			case "+" -> S;
			case "-" -> R;
			default -> NO_OP;
		};
		
		return op;
	}
	

	private static void printMatrix(int [][] m) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println();
	}
	
	public void printBoard() {
		printMatrix(this.board);
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				str.append(board[i][j] + " ");
			}
			str.append("\n");
		}

		str.append("\n");
		
		return str.toString();
	}
} 


