package banish;

import java.util.Objects;

public class BanishWord {

    private String word;
    private int year;

    public BanishWord(String word, int year) {
        this.word = word;
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BanishWord that = (BanishWord) o;
        return year == that.year && word.equalsIgnoreCase(that.word);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + word.toUpperCase().hashCode();
        result = 31 * result + year;
        return result;
    }

    public String getWord() {
        return word;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "(" +
               word + ", " +
                year +
                ")";
    }
}
