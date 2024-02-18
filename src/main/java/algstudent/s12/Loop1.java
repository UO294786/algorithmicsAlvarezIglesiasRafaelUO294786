package algstudent.s12;

public class Loop1 {

	public static long loop1(long n) {
		long cont = 0;
		long n1 = 1;
		while (n1 <= n * n) { //O(log(n))
			for (long i = 1; i <= 2 * n; i += 3) //O(n)
				cont++;
			n1 = 3 * n1;
		}
		return cont;
	}

	public static void main(String arg[]) {
		long t1, t2;
		int nTimes = Integer.parseInt(arg[0]);

		System.out.println("n\ttime\trepetions\tcounter");

		for (long n = 100; n <= 819200; n *= 2) {
			long c = 0;
			t1 = System.currentTimeMillis();

			for (int repetitions = 1; repetitions <= nTimes; repetitions++)
				c = loop1(n); //Loop1 has a complexity of O(n*log(n))

			t2 = System.currentTimeMillis();

			System.out.println(n + "\t" + (t2 - t1) + "\t" + nTimes + "\t\t" + c);
		} // for
	} // main
	
} 