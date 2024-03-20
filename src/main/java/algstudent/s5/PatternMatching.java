package algstudent.s5;

public class PatternMatching {

	private boolean [][] matches;
	private String word;
	
	public PatternMatching(String text) {
		this.word = text;
	}

	public void printsTable() {
		for (int i = 0; i < matches.length; i++) {
			for (int j = 0; j < matches[i].length; j++)
				System.out.print(matches[i][j] ? "T":"F" + " ");
			System.out.println();
		}
	}

	public Object checkPattern(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
