package wordIndex;

import java.io.PrintWriter;
import java.util.*;

public class LineIndex extends AbstractIndex{

    private SortedMap<String, SortedSet<Integer>> index;

    public LineIndex() {
        this.index = new TreeMap<String, SortedSet<Integer>>();
    }

    @Override
    public void solve(String delimiters) {

        index.clear();
        int lineCounter = 1;

        for (String line : sentences) {
            String[] words = line.split(delimiters);
            for (String word : words) {
                if (word.isEmpty()) continue;
                index.putIfAbsent(word.toLowerCase().trim(), new TreeSet<>());
                index.get(word.toLowerCase().trim()).add(lineCounter);
            }
                lineCounter++;
        }

        presentIndex(new PrintWriter(System.out, true));

    }

    @Override
    public void presentIndex(PrintWriter pw) {
        for (Map.Entry<String, SortedSet<Integer>> entry : index.entrySet()) {
            pw.print(entry.getKey() + " <");

            Iterator<Integer> it = entry.getValue().iterator();
            while (it.hasNext()) {
                pw.print(it.next());
                if (it.hasNext()) {
                    pw.print(" , ");
                }
            }

            pw.println(">");
        }
    }

}
