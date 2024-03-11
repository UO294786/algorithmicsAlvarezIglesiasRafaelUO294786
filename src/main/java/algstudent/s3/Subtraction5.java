package algstudent.s3;

/* 
 * The time complexity is quadratic O() 
 * and the waste of stack is O(3^n/2)
 * In this case => the stack does not overflow because 
 * long before the execution time is untreatable 
 */
public class Subtraction5 {
	
	static long cont;
	
	public static void rec5(int n) {
		if (n <= 0)
			cont++;
		else {
			cont++; // O(1)
			rec5(n - 2);
			rec5(n - 2);
			rec5(n - 2);
		}
	}

	public static void main(String arg[]) {
		long t1, t2 = 0;
		int nTimes = Integer.parseInt (arg [0]);
		
		
		for (int n =30; n <= 100; n +=2 ) {
			t1 = System.currentTimeMillis();

			for (int reps=1; reps<=nTimes;reps++)
			{ 
				cont = 0;
				rec5(n);
			} 

			t2 = System.currentTimeMillis();

			System.out.println("n=" + n + "**TIME=" + (t2 - t1) + "**cont=" + cont);
		} // for
	} // main
} // class