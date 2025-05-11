package banish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class TextBanisher {

    private List<BanishWord> list;
    private String text;

    public TextBanisher() {
        this.list = new ArrayList<>();
    }

    public void readBanishedWords(String filePath) {

        try(Scanner frFile = new Scanner(Path.of(filePath))){
        while(frFile.hasNextLine()) {
                String line = frFile.nextLine();
                String[] part =line.split(", ");
                BanishWord word = new BanishWord(part[0],Integer.parseInt(part[1]));
                list.add(word);
        }}catch (Exception e) {
            throw new BanishException("Error reading banished words file");
        }
    }

    public void findBanishedWords(String textFilePath) {

        List<String> foundWords = new ArrayList<>();

        try(BufferedReader br = Files.newBufferedReader(Path.of(textFilePath))){

            String line;
            while((line = br.readLine()) != null) {

                String[] parts = line.split("\\W+");
                for (String part : parts) {
                    for (BanishWord bw : list) {
                        if (bw.getWord().equals(part)) {
                            foundWords.add(bw.getWord().toLowerCase());
                        }
                    }

                }
            }
            StringJoiner joiner = new StringJoiner(",","(",")");
            for (String word : foundWords) {
                joiner.add(word);
            }
            System.out.println(joiner);

        }catch (Exception e) {
            throw new BanishException("Error reading banished words file");
        }

    }

    public void findAndReplaceBanishedWords(String textFilePath, BanishingStrategy bs){

        try(BufferedReader br = Files.newBufferedReader(Path.of(textFilePath))){

            StringBuilder result = new StringBuilder();
            String line;
            while((line = br.readLine()) != null) {

                String[] parts = line.split("[\\s.,]+");
                for (String part : parts) {
                    boolean found = false;
                    for (BanishWord bw : list) {
                        if (bw.getWord().equals(part)) {
                            result.append(bs.replaceBanishedWord(bw.getWord()));
                            result.append(" ");
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        result.append(part).append(" ");
                    }
                }
                result.append(System.lineSeparator());
            }

            PrintWriter pw = new PrintWriter(System.out);
            pw.println(result.toString());
            pw.flush();

        }catch (Exception e) {
            throw new BanishException("Error reading banished words file");
        }
    }

    @Override
    public String toString() {
        return  list.toString();
    }
}
