package algstudent.s2;

/* This class measures times for the Bubble method
for the 3 assumptions: (already ordered, reverse ordered and random ordered) */
public class QuicksortInsertionTimes {
	static int[] v;

	public static void main(String arg[]) {
		long t1, t2;
		v = new int[16000000];
		
		Vector.randomSorted(v);

		t1 = System.currentTimeMillis();

		Quicksort.mquicksort(v, Integer.parseInt(arg[0]));

		t2 = System.currentTimeMillis();

		System.out.println(arg[0] + "\t" + (t2 - t1));
		
	}
}
