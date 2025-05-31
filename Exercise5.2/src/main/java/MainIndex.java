import wordIndex.*;

public class MainIndex {
	public static void main(String args[]) {
		// String delimiters = "[^a-zA-Z0-9Ã¡Ã©Ã­Ã³ÃºÃ¼Ã�Ã‰Ã�Ã“ÃšÃœ]+";
		String delimiters = "[ .,:;\\-\\!\\?]+";
		//Index cp = new CounterIndex();
		//Index cp = new LineIndex();
		Index cp = new PositionInLineIndex();
		cp.addSentence("How much wood would a woodchuck chuck ");
		cp.addSentence("if a woodchuck could chuck wood?");
		cp.addSentence("He would chuck, he would, as much as he could, ");
		cp.addSentence("and chuck as much wood as a woodchuck would ");
		cp.addSentence("if a woodchuck could chuck wood.");
//		cp.addSentence("How much wood would a woodchuck chuck " +
//				"if a woodchuck could chuck wood?");
//		cp.addSentence("He would chuck, he would, as much as he could, "
//				+ "and chuck as much wood as a woodchuck would "
//				+ "if a woodchuck could chuck wood.");
		cp.solve(delimiters);
		//cp.presentIndexOnConsole();
	}
}
