package algstudent.s0;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class MatrixOperations {

	public static final int MOVE_UP = 1;
	public static final int MOVE_RIGHT = 2;
	public static final int MOVE_DOWN = 3;
	public static final int MOVE_LEFT = 4;
	public static final int TRAVERSED = -1;
	public static final String EXAMPLE_FILE = "ExampleFile";

	int[][] matrix;
	int size;

	public MatrixOperations(int n,int min,int max){
	    matrix = new int[n][n];
	    this.size = n;
	    Random rand = new Random();
	    for(int i=0; i<n;i++){
	        for(int j=0; j<n;j++){
	             matrix[i][j] = rand.nextInt(max-min)
	             + min + 1 ;
	        }
	    }
	}

	public MatrixOperations(String filename) throws NumberFormatException, IOException{
	    String line;
	    int lineNum = 0;
	    String[] rowstr;
	    int[] row = null;
	    BufferedReader bfr = new BufferedReader(new FileReader(filename));
	    while((line=bfr.readLine()) != null){
	        if(lineNum == 0){
	            int newSize = Integer.parseInt(line);
	            matrix = new int[newSize][newSize];
	            row = new int[newSize];
	            rowstr = new String[newSize];
	            lineNum++;
	        } else 
	        rowstr = line.split("	");
	        for(int i=0; i<row.length;i++){
	        	row[i] = Integer.parseInt(rowstr[i]);
	            matrix[lineNum-1][i] = row[i];
	        }
	        lineNum++;
	    }
	    bfr.close();
	}

	public int getSize(){
	    return this.size;
	}

	public void write() {
	    for(int i=0; i<size;i++){
	        for(int j=0; j<size;j++){
	             System.out.print(matrix[i][j] + "(Tab here)");
	        }
	        System.out.println();
	    }
	}

	public int sumDiagonal11(){
	    int sum = 0;
	    for(int i=0; i<size;i++){
	        for(int j=0; j<size;j++){
	            if(i==j){
	                sum += matrix[i][j];
	            }
	        }
	    }
	    return sum;
	}

	public int sumDiagonal12(){
	    int sum = 0;
	    for(int i=0; i<size;i++){
	        sum += matrix[i][i];
	    }
	    return sum;
	}

	public void travelPath(int i, int j){

	    boolean doesContinue = true;
	    while(doesContinue){
	        if (matrix[i][j] == TRAVERSED){
	            doesContinue = false;
	        } else
	        if(matrix[i][j]==MOVE_UP){
	            matrix[i][j] = TRAVERSED;
	            j--;
	            if(j<0){
	                doesContinue = false;
	            }
	        } else
	        if(matrix[i][j]==MOVE_RIGHT){
	            matrix[i][j] = TRAVERSED;
	            i++;
	            if(i>this.size-1){
	                doesContinue = false;
	            }
	        } else
	        if(matrix[i][j]==MOVE_DOWN){
	            matrix[i][j] = TRAVERSED;
	            j++;
	            if(j>this.size-1){
	                doesContinue = false;
	            }
	        } else
	        if(matrix[i][j]==MOVE_LEFT){
	            matrix[i][j] = TRAVERSED;
	            i--;
	            if(i<0){
	                doesContinue = false;
	            }
	        }
	    }
	    
	    write();
	}

}
