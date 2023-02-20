/*
JAVA is sensitive to upper and lower case.
A class should begin with a capital letter.
Methods and all kinds of variables should begin with lower case.
 
Java classes are stored in files with the same as the class, 
(to which we add the .java extension). Vector1.java in this case.

Packages must be in a directory path with the same name, that is,
package alg77777777.s1 should be in the directory alg77777777/s1.
*/
package algstudent.s1;


/**
 * This program is for working with vectors and see how Java programs work
 */
public class Vector4 {
	
	

	public static void main(String arg []){
	  
	  int[] v;
	  int iteration = 0;
	  int repetitions = Integer.parseInt(arg[0]);
	  
	  for(int i=10;i!=0;i=i*3) {
		  
		  v = new int[i];
		  
		  long t1 = System.currentTimeMillis();
		  
		  for (int repetition=1; repetition<=repetitions;repetition++) {
			  Vector1.fillIn(v);
		  } 
		  
		  long t2 = System.currentTimeMillis();
		  
		  System.out.println("(FillIn)Time passed for iteration "+ iteration+": "+(t2-t1));
		  
		  
          long t12 = System.currentTimeMillis();
          
		  for (int repetition=1; repetition<=repetitions;repetition++) {
			  Vector1.sum(v);
		  } 
		  
		  long t22 = System.currentTimeMillis();
		  
		  System.out.println("(Sum)Time passed for iteration "+ iteration+": "+(t22-t12));
		  
          long t13 = System.currentTimeMillis();
		  
		  for (int repetition=1; repetition<=repetitions;repetition++) {
			  Vector1.maximum(v, v);
		  } 
		  
		  long t23 = System.currentTimeMillis();
		  
		  System.out.println("(Maximum)Time passed for iteration "+ iteration+": "+(t23-t13));
		  iteration++;
	  }
	} 
	
	

}
