package simpleFilesWordCounting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordCounterSig extends WordCounter{

    private ArrayList<String> nonSignificant;

    public WordCounterSig() {
        super();
        this.nonSignificant = new ArrayList<>();
    }

    public void readNonSigArray(String[] nsWords){

        for(String word : nsWords){
            word = word.toUpperCase();
            nonSignificant.add(word);
        }

    }

    public void readNonSigFile(String filNoSig, String del) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filNoSig));
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] words = line.split(del);
            for (String word : words) {
                includeNonSig(word);
            }
        }
        sc.close();
    }


    private int findNonSig(String w){

        for(int i = 0; i<nonSignificant.size(); i++){
            if(w.equals(nonSignificant.get(i))){
                return i;
            }
        }

        return -1;
    }

    private List<String> copyCapitalized(String[] a){
        List<String> ret = new ArrayList<>();
        for(String s : a){
            ret.add(s.toUpperCase());
        }

        return ret;
    }

    @Override
    protected void include(String word) {
        String upper = word.toUpperCase();
        if (findNonSig(upper) == -1) {
            super.include(word);
        }
    }


    private void includeNonSig(String w){
        String upper = w.toUpperCase();
        if (findNonSig(upper) == -1) {
            nonSignificant.add(upper);
        }
    }


}
