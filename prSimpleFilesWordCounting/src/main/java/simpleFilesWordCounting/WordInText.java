package simpleFilesWordCounting;

import java.util.Objects;

public class WordInText {

    private String word;
    private int times;

    public WordInText(String word) {
        this.word = word.toUpperCase();
        this.times = 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordInText that = (WordInText) o;
        return word.equalsIgnoreCase(that.word);
    }

    @Override
    public int hashCode() {
        return word.toUpperCase().hashCode();
    }

    public String getWord() {
        return word;
    }

    public void increment(){
        this.times++;
    }

    @Override
    public String toString() {
        return word + ": " + times;
    }
}
