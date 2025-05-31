package wordIndex;

import java.io.PrintWriter;
import java.util.*;

public class PositionInLineIndex extends AbstractIndex{

    private SortedMap<String, SortedMap<Integer, SortedSet<Integer>>> index;

    public PositionInLineIndex() {
        this.index = new TreeMap<String, SortedMap<Integer, SortedSet<Integer>>>();
    }


    @Override
    public void solve(String delimiters) {

        index.clear();
        int lineCounter = 1;

        for (String line : sentences) {
            String[] words = line.split(delimiters);
            int position = 1;

            for (String word : words) {
                if (word.isEmpty()) continue;
                index.putIfAbsent(word.toLowerCase().trim(), new TreeMap<>());
                index.get(word.toLowerCase().trim()).putIfAbsent(lineCounter, new TreeSet<>());
                index.get(word.toLowerCase().trim()).get(lineCounter).add(position);
                position++;
            }
            lineCounter++;
        }

        presentIndex(new PrintWriter(System.out, true));

    }

    @Override
    public void presentIndex(PrintWriter pw) {
        for (Map.Entry<String, SortedMap<Integer, SortedSet<Integer>>> entry : index.entrySet()) {
            pw.print(entry.getKey());

            SortedMap<Integer, SortedSet<Integer>> lineMap = entry.getValue();

            for (Map.Entry<Integer, SortedSet<Integer>> lineEntry : lineMap.entrySet()) {
                pw.print(" " + lineEntry.getKey() + " <");

                Iterator<Integer> positionIter = lineEntry.getValue().iterator();
                while (positionIter.hasNext()) {
                    pw.print(positionIter.next());
                    if (positionIter.hasNext()) {
                        pw.print(" , ");
                    }
                }

                pw.print(">");
            }

            pw.println();
        }
    }

}