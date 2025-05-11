import simpleFilesWordCounting.*;

public class MainWordCounter {

    public static void main(String[] args) {
        WordCounter wordCounter = new WordCounter();

        String[] datos = {
                "This is the first sentence of the example",
                "and this is the second one"
        };

        wordCounter.includeAll(datos, "[ ]");
        System.out.println(wordCounter);

    }


}
