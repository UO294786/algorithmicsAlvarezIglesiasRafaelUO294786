package algstudent.test;

public class PatternMatchingT {

	private boolean [][] matches;
	private String word;
	
	public PatternMatchingT(String text) {
		this.word = text;
	}

	public void printsTable(String pattern) {
		System.out.print("   ");
		
		for (char c : pattern.toCharArray()) 
			System.out.print(" " + c);
		System.out.println();
		
		for (int i = 0; i < matches.length; i++) {
			if (i > 0)
				System.out.print(word.charAt(i - 1) + " ");
			else
				System.out.print("  ");
			for (int j = 0; j < matches[i].length; j++)
				System.out.print((matches[i][j] ? "T":"F") + " ");
			System.out.println();
		}
		System.out.println();
	}
	
	public boolean checkPattern(String pattern) {
        matches = new boolean[word.length() + 1][pattern.length() + 1];
        matches[0][0] = true;

        for (int i = 1; i <= word.length(); i++) {
            for (int j = 1; j <= pattern.length(); j++) {
                if (word.charAt(i - 1) == pattern.charAt(j - 1)) {
                    matches[i][j] = matches[i - 1][j - 1];
                } else if (pattern.charAt(j - 1) == '?') {
                    matches[i][j] = matches[i][j - 1] || matches[i - 1][j - 1];
                } else if (pattern.charAt(j - 1) == '*') {
                    matches[i][j] = matches[i - 1][j] || matches[i][j - 1] || matches[i - 1][j - 1];
                } else if (pattern.charAt(j - 1) == '&') {
                    matches[i][j] = (matches[i - 1][j - 1] || matches[i - 1][j]);
                    
                    if (i - 2 > -1)
                    	if (matches[i - 2][j])
                    		matches[i][j] = matches[i][j] && matches[i - 2][j - 1];
                }else {
                    matches[i][j] = false;
                }
            }
            
        }

        return matches[word.length()][pattern.length()];
    }

}
