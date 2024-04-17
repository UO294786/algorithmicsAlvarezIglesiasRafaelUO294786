package algstudent.s6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumericalSquare {
	
	private static final String Q_MARK = "?";
	private static final String EQ_SIGN = "=";

	private static final int D = 1;
	private static final int M = 2;
	private static final int S = 3;
	private static final int R = 4;
	private static final int NO_OP = -1;

	
	private int size;
	
	private int [][] 
			board,
			rowOps,
			colOps;
	
	private int []
			rowRes,
			colRes;
	
	private boolean [][] blocked;
	
	
	public NumericalSquare() {
				
	}
	
	public static void main(String[] args) {
		NumericalSquare sq = new NumericalSquare();
		
		sq.readFromFile("src/main/java/algstudent/s6/tests/test01.txt");
	}
	
	private static void printMatrix(int [][] m) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println();
	}
	
//	public void solve() {
//		found = false;
//		for (int i=0; i<numberOfPasswords; i++) {
//			backtracking(0);
//			found = false;
//		}
//	}
//	
//	public List<String> getPasswords() {
//		return passwords;
//	}
//	
//	//SOLUTION WITH BACKTRACKING
//	private void backtracking(int level) {
//		if (level == numberOfTotalCharacters) {
//			found = true;
//			passwords.add(String.valueOf(password));
//		}
//		else {
//			Character [] shuffle;
//			if (level < numberOfTotalCharacters - numberOfNonLettersEnds) {
//				Collections.shuffle(lettersList);
//				shuffle = lettersList.toArray(new Character[lettersList.size()]);
//			}
//			else {
//				Collections.shuffle(nonLettersList);;
//				shuffle = nonLettersList.toArray(new Character[nonLettersList.size()]);
//			}
//			for (int i = 0; i < shuffle.length; i++) {
//				if (!found) {
//					char character = shuffle[i];
//					if (valid(level, character)) {
//						password[level] = character;
//						backtracking(level + 1);
//						password[level] = '?';
//					}
//				}
//			}
//		}
//	}
//	
//	//TO KNOW IF A CHAR IS VALID IN A PASSWORD
//	private boolean valid(int level, char letter) {
//		//Only two consecutive vowels if they are different
//		if (level > 0 && isVowel(letter) && letter == password[level - 1])
//			return false;
//		
//		//Only some consonant pairs are allowed
//		if (level > 0 && isConsonant(letter) && isConsonant(password[level - 1]))
//			if (!consonantPairs.contains(String.valueOf(password[level - 1])+ String.valueOf(letter)))
//				return false;
//		//Not 3 consecutive vowels or consonants
//		if (level > 1) {
//			if ((isVowel(letter) && isVowel(password[level - 1]) && isVowel(password[level - 2])))
//				return false;
//		
//			if ((isConsonant(letter) && isConsonant(password[level - 1]) && isConsonant(password[level - 2])))
//				return false;
//		}
//		//Otherwise
//		return true;
//	}

	
	private void readFromFile(String fileName) {
		BufferedReader reader = null; 	
		try {
			reader = new BufferedReader(new FileReader(fileName)); 	
			boolean isSizeLine = true,
					isNumberLine = true;
			int row = 0;
			while (reader.ready()) {
				String line = reader.readLine();
				
				//Get the size
				if (isSizeLine) {
					this.size = Integer.parseInt(line);
					
					this.board = new int[size][size];
                    this.colOps= new int[size][size];
                    this.rowOps = new int[size][size];
                    this.colRes = new int[size];
                    this.rowRes = new int[size];
                    
                    isSizeLine = false;	
                    
					continue;
				}
				
				if (row == size - 1) {
					parseColRes(reader.readLine());
					
					break;
				}

				if (isNumberLine) {
					parseRow(line, row++);
					
					isNumberLine = false;
				}
				else {
					parseCol(line, row);
					
					isNumberLine = true;
				}
			}
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} 
		
		System.out.println("Board");
		printMatrix(board);
		System.out.println("Row ops");
		printMatrix(rowOps);
		System.out.println("Col ops");
		printMatrix(colOps);
	}
	
	private boolean isEven(int n) {
		return n % 2 == 0;
	}
	
	private void parseColRes(String line) {
		String[] arr = line.split(" ");
		for (int i = 0; i < arr.length; i++)
			this.colRes[i]= Integer.parseInt(arr[i]);
	}
	
	private void parseCol(String line, int row) {
		String[] arr = line.split(" ");
		for (int j = 0; j < arr.length; j++)
			this.colOps[j][row] = getOp(arr[j]);
	}

	private void parseRow(String line, int i) {
		String[] arr = line.split(" ");
		
		int j = 0;
		for (int c = 0; c < arr.length; c++) {
			String character = arr[c];
			
			if (Character.isDigit(character.charAt(0)))
				board[i][j++] = Integer.parseInt(character);
			else {
				switch(character) {
					case Q_MARK:{
						board[i][j++] = 0;
						
						break;
					}
					
					case EQ_SIGN:{
						rowRes[i] = Integer.parseInt(String.valueOf(arr[c + 1]));
						
						return;
					}
					
					default:{
						rowOps[i][j] = getOp(character);
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

}
