package banish;

import java.util.Collections;

public class UppercasedWord implements BanishingStrategy{
    @Override
    public String replaceBanishedWord(String bWord) {

        StringBuilder sb = new StringBuilder(bWord.toUpperCase());
        return sb.reverse().toString();
    }
}
