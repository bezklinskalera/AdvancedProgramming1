package simpleFilesWordCounting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class WordCounter {

    private ArrayList<WordInText> words;

    public WordCounter() {
        this.words = new ArrayList<WordInText>();
    }

    private int position(String word){
        WordInText that = new WordInText(word);
        for (int i = 0; i<words.size();i++){
            if(words.get(i).equals(that)){
                return i;
            }
        }
        return -1;
    }

    protected void include(String word){
            int pos = position(word);
            if(pos >= 0){
                words.get(pos).increment();
            }
            else{
                words.add(new WordInText(word));
            }

    }

    private void includeAll(String line, String del){

        String[] result = line.split(del);
        for (int i = 0; i< result.length; i++){
            if (result[i].trim().isEmpty()) {
                continue;
            }
            include(result[i]);
        }
    }

    public void includeAll(String[] text, String del){
            for (String line : text) {
                includeAll(line, del);
            }
        }


    public void includeAllFromFile(String filename, String del) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(filename));
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            includeAll(line,del);
        }
        sc.close();

    }

    public WordInText find(String pal){
        WordInText that = new WordInText(pal);
        for (int i = 0; i<words.size();i++){
            if(words.get(i).equals(that)){
                return words.get(i);
            }
        }
        throw new NoSuchElementException();
    }

    public void presentWords(String fileName) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(fileName);
        presentWords(pw);
        pw.close();
    }

    public void presentWords(PrintWriter pw){
        for (int i = 0; i<words.size();i++){
            pw.println(words.get(i));
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(words.get(0));
        for (int i = 1; i<words.size();i++){
            sb.append(" - ");
            sb.append(words.get(i));
        }
        sb.append("]");

        return sb.toString();
    }
}
