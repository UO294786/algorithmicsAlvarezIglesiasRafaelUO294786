package algstudent.examples.RoadsInCity;

/**
 * To solve the RoadsInCity problem
 */
public class RoadsInCity {
	private int columns; //Size of the city - horizontal
	private int rows; //Size of the city - vertical
	private long[][] map; //Map of the city to calculate the possibilities from the starting point to the target point
	private int startX; //Initial coordinate X
	private int startY; //Initial coordinate Y
	private int endX; //Final coordinate X
	private int endY; //Final coordinate Y
	
	/**
	 * At the beginning, everything is empty
	 * We just create the map
	 * @param rows Size of the city (vertical)
	 * @param columns Size of the city (horizontal)
	 */
	public RoadsInCity(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		map = new long[rows][columns];
	}
	
	/**
	 * Places a barrier in the map, if possible
	 * @param x Coordinate x of the barrier
	 * @param y Coordinate y of the barrier
	 */
	public void addBarrier(int x, int y) {
		if ((x >= 0)&&(x < rows))
			if ((y >= 0)&&(y < columns))
				map[x][y] = -1; //We mark the barrier as -1
	}
	
	/**
	 * It is the main method. It manages the 4 steps to 
	 * solve the problem (including printing the results)
	 * The final complexity is O(n^2)
	 * @param startX Initial coordinate X
	 * @param startY //Initial coordinate Y
	 * @param endX //Final coordinate X
	 * @param endY //Final coordinate Y
	 * @return
	 */
	public long calculate(int startX, int startY, int endX, int endY) {
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		
		System.out.println("\n************************************");
		System.out.println("************************************");
		
		System.out.println("Starting position: (" + startX + ", " + startY + ")");
		System.out.println("Target position: (" + endX + ", " + endY + ")");
		System.out.println("Representation of the map at the beginning:");
		printMap();
		
		//1-Check whether indicated positions are OK
		if (checkPositions()) { //O(1)
			
			//2-Mark the adjacent positions as possible visited places
			markAdjacentPositions(); //O(1)
				
			//3-Calculate number of paths
			calculateNumberOfPaths(); //O(n^2)
			
			//4-Print solution //O(n^2)
			System.out.println("Representation of the map at the end:");
			printMap();
			System.out.println("Number of ways to get to the target position: " + map[endX][endY]);
			return map[endX][endY];
		} 
		else {
			System.out.println("Error. Wrong positions. Please check");
			return -1;
		}
	}
	
	/**
	 * Checks the positions:
	 *  1) startX, startY, endX, endY should be inside the city
	 *  2) startX should be > endX
	 *  3) startY should be < endY
	 * @return True if the positions indicated are OK. False otherwise
	 */
	private boolean checkPositions() {
	    if (startX < 0 || startY < 0 || endX < 0 || endY < 0 ||
	        startX >= rows || startY >= columns ||
	        endX >= rows || endY >= columns)
	        return false;

	    if (startX <= endX || startY >= endY)
	        return false;

	    return true;
	}
	
	/**
	 * Marks the adjacent positions as visited (setting the values as 1)
	 * The adjacent are next to the starting position
	 * We should take into account that we cannot put 1 is it is a barrier (if it is -1)
	 */
	private void markAdjacentPositions() {
		if (map[startX - 1][startY] != -1)
			map[startX - 1][startY] = 1;
	
		if (map[startX][startY + 1] != -1)
			map[startX][startY + 1] = 1;

		
	}
	
	/**
	 * After the mark of the adjacent positions, it is necessary to continue the algorithm to 
	 * count the number of possibilities from the starting point to the end point
	 */
	private void calculateNumberOfPaths(){
		for (int i = startX; i >= endX; i--)
			for (int j = startY; j <= endY; j++) 
				if (map[i][j] != -1) {
					if (i + 1 < rows)
						if (map[i + 1][j] != -1)
							map[i][j] += map[i + 1][j];
	
					if (j - 1 >= 0)
						if (map[i][j - 1] != -1)
							map[i][j] += map[i][j-1];
				}
	}
	
	/**
	 * Prints the state of the map of the city
	 */
	private void printMap() {
		for (int i=0; i<rows; i++) {
			for (int j=0; j<columns; j++) {
				if ((startX == i)&&(startY == j))
					System.out.printf("%7s", "START");
				else if ((endX == i)&&(endY == j))
					System.out.printf("%7s", "END");
				else
					System.out.printf("%7d", map[i][j]);
			}
			System.out.println("");
		}
	}

}