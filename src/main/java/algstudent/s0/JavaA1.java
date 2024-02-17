package algstudent.s0;

import java.util.ArrayList;
import java.util.List;

public class JavaA1 {
	final static int NUM_OF_CASES = 7;
	
	
	public static void main(String[] args) {
		long t1,t2;
		int n = 10000;
		List<Integer> primes = new ArrayList<>();
		
		for (int i = 0; i < NUM_OF_CASES; i++) {
			t1 = System.currentTimeMillis();
			
			primes = primesList( n );
			
			t2 = System.currentTimeMillis();
			
			System.out.println("n = " + n + " *** " + "time = " + (t2 - t1) + " milliseconds");		
			
			n *= 2;
		}

	}


	private static List<Integer> primesList(int n) {
		List<Integer> primes = new ArrayList<>();
		
		for (int i = 2; i <= n; i++)
			if (isPrime( i ))
				primes.add( i );
		
		return primes;
	}


	private static boolean isPrime(int n) {
		boolean res = true;
		
		for (int i = 2; i < n; i++)
			if (n % i == 0)
				res = false;
		
		return res;
	}

}
