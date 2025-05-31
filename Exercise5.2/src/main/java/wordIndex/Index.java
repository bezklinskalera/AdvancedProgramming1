package wordIndex;

import java.io.PrintWriter;

public interface Index {

    void addSentence(String sentence);
    void solve(String delimiters);
    void presentIndex(PrintWriter pw);

    default void presentIndexOnConsole() {
        PrintWriter pw = new PrintWriter(System.out, true);
        presentIndex(pw);
    }


}
