package algstudent.s6;

public class NumericalSquareOneTimes {
	static int[] v;

	public static void main(String arg[]) {
		long t1, t2;
		NumericalSquareOne sq = new NumericalSquareOne();
		
		for (int n = 1; n <= 7; n++) {
			String path = "src/main/java/algstudent/s6/tests/test0" + String.valueOf(n) + ".txt";
			t1 = System.currentTimeMillis();
			
			sq.solve(path);

			t2 = System.currentTimeMillis();

			System.out.println("Test0"+ String.valueOf(n) + ":" + "\t" + (t2 - t1));
		}
	}
}
