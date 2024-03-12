package algstudent.s3;

import algstudent.s2.Vector;

public class Mergesort {
	static int[] v;
	
	public static void mergesort(int[] elements) {
		mergesort(0, elements.length - 1, elements);
	}
	
    private static void mergesort(int left, int right, int[] elements) {
        if (right > left) {
            int center = (right + left) / 2;
            
            mergesort(left, center, elements);
            
            mergesort(center + 1, right, elements);
            
            combine(left, center, center + 1, right, elements);
        }
    }

    public static void combine(int x1, int x2, int y1, int y2, int[] elements) {
        int sizeX = x2 - x1 + 1;
        int sizeY = y2 - y1 + 1;
        int[] x = new int[sizeX];
        int[] y = new int[sizeY];
    
        for (int i = 0; i < sizeX; i++) {
            x[i] = elements[x1 + i];
        }
    
        for (int i = 0; i < sizeY; i++) {
            y[i] = elements[y1 + i];
        }
    
        int i = 0, j = 0, k = x1;
    
        while (i < sizeX && j < sizeY) {
            if (x[i] <= y[j]) {
                elements[k++] = x[i++];
            } else {
                elements[k++] = y[j++];
            }
        }
    
        while (i < sizeX) {
            elements[k++] = x[i++];
        }
    
        while (j < sizeY) {
            elements[k++] = y[j++];
        }
    }

	public static void main(String arg[]) {
		int n = Integer.parseInt(arg[0]); //size of the problem
		v = new int[n];

		Vector.sorted(v);
		System.out.println("VECTOR TO BE SORTED");
		Vector.print(v);
		mergesort(v);
		System.out.println("SORTED VECTOR");
		Vector.print(v);

		Vector.reverseSorted(v);
		System.out.println("VECTOR TO BE SORTED");
		Vector.print(v);
		mergesort(v);
		System.out.println("SORTED VECTOR");
		Vector.print(v);

		Vector.randomSorted(v);
		System.out.println("VECTOR TO BE SORTED");
		Vector.print(v);
		mergesort(v);
		System.out.println("SORTED VECTOR");
		Vector.print(v);
	} 

}
