package algstudent.s7;

import static org.junit.Assert.assertEquals;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Test;

/**
 * JUnit Test for NumericalSquare
 */
public class NumericalSquareTest {
	
	@Test
	public void test00() {
		boolean result = executeFromFile("src/main/java/algstudent/s6/tests/test00.txt", "Test00");
		assertEquals(true, result);
	}
	
	@Test
	public void test01() {
	    boolean result = executeFromFile("src/main/java/algstudent/s6/tests/test01.txt", "Test01");
	    assertEquals(true, result);
	}

	@Test
	public void test02() {
	    boolean result = executeFromFile("src/main/java/algstudent/s6/tests/test02.txt", "Test02");
	    assertEquals(true, result);
	}

	@Test
	public void test03() {
	    boolean result = executeFromFile("src/main/java/algstudent/s6/tests/test03.txt", "Test03");
	    assertEquals(true, result);
	}

	@Test
	public void test04() {
	    boolean result = executeFromFile("src/main/java/algstudent/s6/tests/test04.txt", "Test04");
	    assertEquals(true, result);
	}

	@Test
	public void test05() {
	    boolean result = executeFromFile("src/main/java/algstudent/s6/tests/test05.txt", "Test05");
	    assertEquals(true, result);
	}

	@Test
	public void test06() {
	    boolean result = executeFromFile("src/main/java/algstudent/s6/tests/test06.txt", "Test06");
	    assertEquals(true, result);
	}

	@Test
	public void test07() {
	    boolean result = executeFromFile("src/main/java/algstudent/s6/tests/test07.txt", "Test07");
	    assertEquals(true, result);
	}
	
	/**
	 * Reads the initial pyramid from a text file and creates an object to deal with the problem
	 * @param file File from which 
	 * @return True if we find a solution for the problem, false otherwise
	 */
	private boolean executeFromFile(String file, String tName) {
		long t1, t2;

		System.out.println("\n" + tName + ": ");

		boolean result = false;
		
		NumericalSquaresBoard board = new NumericalSquaresBoard(file);
		NumericalSquaresBaB puzzle = new NumericalSquaresBaB(board);
		
		t1 = System.currentTimeMillis();

		puzzle.branchAndBound(puzzle.getRootNode()); 		
		
		t2 = System.currentTimeMillis();
		
		board.printBoard();
		
		result = puzzle.getBestNode() != null ? true : false; 

		System.out.println("Time:" + "\t" + (t2 - t1));
		
		puzzle.printSolutionTrace();
		
		return result;
	}
	
}
