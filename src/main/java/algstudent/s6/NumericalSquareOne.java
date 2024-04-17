package algstudent.s6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumericalSquareOne {
	
	private static final String Q_MARK = "?";
	private static final String EQ_SIGN = "=";

	private static final int D = 1;
	private static final int M = 2;
	private static final int S = 3;
	private static final int R = 4;
	private static final int NO_OP = -1;
	
	private static final int MAX_NUM = 9;
	private static final int MIN_NUM = 0;

	
	private int size;
	
	private int [][] 
			board,
			rowOps,
			colOps;
	
	private int []
			rowRes,
			colRes;
	
	private boolean [][] blocked;
	
	private boolean found;
	
	
	public static void main(String[] args) {
		NumericalSquareOne sq = new NumericalSquareOne();
		
		sq.solve("src/main/java/algstudent/s6/tests/test03.txt");
	}
	
	public void solve(String path) {
		readFromFile(path);

		backtracking(0, 0);
        
        if (found) {
            System.out.println("SOLUTION FOUND");
			printMatrix(board);
        }
        else 
            System.out.println("No solution found");
        
    }
	
	//SOLUTION WITH BACKTRACKING
	private void backtracking(int row, int col) {
		if (row == size) {
			this.found = isSolution();
			return;
		}
		
		if (blocked[row][col]) {
			int nRow = row, nCol = col;
		
			if (col == size - 1) {
				nCol = 0;
				
				nRow = row + 1;
			} else nCol = col + 1;
			
			backtracking(nRow, nCol);
		}
		
		else {
			for (int i = MIN_NUM; i <= MAX_NUM && !found; i++) {	
				
				board[row][col] = i;
				
				int nRow = row, nCol = col;
				
				if (col == size - 1) {
					nCol = 0;
					
					nRow = row + 1;
				} else nCol = col + 1;

				
				if (isRowSolution(row) && isColSolution(col))
					backtracking(nRow, nCol);
				
			}
		}
	}
	
	
	
	private boolean isSolution() {
		boolean res = true;
		
		for (int i = 0; i < size; i++) {
			res = isRowSolution(i) && isColSolution(i);
			
			if (!res) return false;
		}
		
		return true;
	}
	
	private boolean isRowSolution(int row) {
		int opC = 0, res = board[row][0];
		for (int j = 1; j < board.length; j++) 
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
		
		return res == rowRes[row];
	}
	
	private boolean isColSolution(int col) {
		int opC = 0, res = board[0][col];
		for (int j = 1; j < board.length; j++) 
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
		
		return res == colRes[col];
	}
	
	// --- Utils ---
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
					this.size = Integer.parseInt(line);
					
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

}
