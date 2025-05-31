package wordIndex;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractIndex  implements Index {

   protected List<String> sentences;

    public AbstractIndex() {
        this.sentences = new  ArrayList<>();
    }

    @Override
    public void addSentence(String sentence) {
        sentences.add(sentence);
    }
}
