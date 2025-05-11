import simpleFilesWordCounting.WordInText;

public class MainWordInText {

	public static void main(String[] args) {

		WordInText w1 = new WordInText("wood");
		WordInText w2 = new WordInText("Wood");

		w1.increment();

		if (w1.equals(w2)) {
			System.out.println("Word 1 = " + w1);
			System.out.println("Word 2 = " + w2);
			System.out.println("The words are equal\n");
		}else{
			System.out.println("The words are NOT equal ");
		}


	}

}
