import java.io.*;

import java.util.NoSuchElementException;

import simpleFilesWordCounting.*;

public class Main {
    public static void main(String[] args) {
        String[] data = {
                "How much wood would a woodchuck chuck",
                "if a woodchuck could chuck wood?",
                "He would chuck, he would, as much as he could,",
                "and chuck as much wood as a woodchuck would",
                "if a woodchuck could chuck wood."
        };
        String delimiters = "[ .,\\?]+"; // " .,?" one or many times
        String[] nonSig = { "If", "A", "as", "AND"};
        System.out.println("Word Counter...");
        WordCounter counter = new WordCounter();
        WordCounterSig counterSig = new WordCounterSig();
        counterSig.readNonSigArray(nonSig);
        // We include all the words
        // taking into account the delimiters
        counter.includeAll(data, delimiters);
        counterSig.includeAll(data, delimiters);
        System.out.println(counter + "\n");
        System.out.println(counterSig + "\n");
        try {
            System.out.println(counter.find("wood"));
            System.out.println(counter.find("Wooden"));
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        // We repeat the execution
        // taking reading/writing from/to file
        System.out.println("We repeat the execution "
                + "taking reading/writing from/to file");
        WordCounterSig counterSigFile = null;
        counter = new WordCounter();
        try {
            counterSigFile = new WordCounterSig();
            counterSigFile.readNonSigFile("fileNotSig.txt", delimiters);
        } catch (IOException e1) {
            System.out.println("ERROR:" + e1.getMessage());
        }
        // We include all the words that are in data.txt
        // taking into account the separators
        try {
            counter.includeAllFromFile("data.txt", delimiters);
            counterSigFile.includeAllFromFile("data.txt", delimiters);
            System.out.println(counter + "\n");
            System.out.println(counterSigFile + "\n");
            // display methods
            PrintWriter pw = new PrintWriter(System.out, true);
            counter.presentWords(pw);
            // output to file
            counter.presentWords("output-data.txt");
            // Methods to present on screen for Non-Significant
            System.out.println();
            counterSigFile.presentWords(pw);
            // output to file
        } catch (IOException e) {
            System.out.println("ERROR:" + e.getMessage());
        }
    }
}