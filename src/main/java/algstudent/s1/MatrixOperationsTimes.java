package algstudent.s1;

public class MatrixOperationsTimes {

	
	
	public static void main(String arg []){
		  
		  int iteration = 0;
		  int repetitions = Integer.parseInt(arg[0]);
		  
		  for(int i=10;i!=0;i=i*3) {
			  
			  MatrixOperations mo = new MatrixOperations(i,1,2);
			  
			  long t1 = System.currentTimeMillis();
			  
			  for (int repetition=1; repetition<=repetitions;repetition++) {
				  mo.sumDiagonal11();
			  } 
			  
			  long t2 = System.currentTimeMillis();
			  
			  System.out.println("(One)Time passed for iteration "+ iteration+": "+(t2-t1));
			  
              long t12 = System.currentTimeMillis();
			  
			  for (int repetition=1; repetition<=repetitions;repetition++) {
				  mo.sumDiagonal12();
			  } 
			  
			  long t22 = System.currentTimeMillis();
			  
			  System.out.println("(Two)Time passed for iteration "+ iteration+": "+(t22-t12));
			  iteration++;
		  }
		} 
	
}
