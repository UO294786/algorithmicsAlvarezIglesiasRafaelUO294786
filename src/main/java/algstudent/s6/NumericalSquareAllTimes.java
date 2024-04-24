package algstudent.s6;

public class NumericalSquareAllTimes {
	static int[] v;

	public static void main(String arg[]) {
		long t1, t2;
		NumericalSquareAll sq = new NumericalSquareAll();
		
		for (int n = 0; n <= 7; n++) {
			String path = "src/main/java/algstudent/s6/tests/test0" + String.valueOf(n) + ".txt";
			System.out.println("Test0" + n + ": ");
			t1 = System.currentTimeMillis();
			
			int res = sq.solve(path);

			t2 = System.currentTimeMillis();
			
			System.out.println("Number of results: " + res);
			System.out.println("Time:" + "\t" + (t2 - t1));
		}
	} 
}
