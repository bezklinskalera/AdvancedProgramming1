package wordIndex;

import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

public class CounterIndex extends AbstractIndex{

    private SortedMap<String, Integer> index;

    public CounterIndex() {
        this.index  = new TreeMap<String, Integer>();
    }


    @Override
    public void solve(String delimiters) {

        index.clear();
        for (String line : sentences) {
                String[] words = line.split(delimiters);
                for (String word : words) {
                    if (!index.containsKey(word)) {
                        this.index.put(word.toLowerCase(), 1);
                    }
                    else {
                        index.replace(word.toLowerCase(), index.get(word.toLowerCase()) + 1);
                    }
                }
            }

        presentIndex(new PrintWriter(System.out, true));
    }

    @Override
    public void presentIndex(PrintWriter pw) {

        for (Map.Entry<String, Integer> entry : index.entrySet()) {
            pw.println(entry.getKey() + " " + entry.getValue());
        }


    }
}
