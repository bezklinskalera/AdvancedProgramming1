package banish;

public class StarredWord implements BanishingStrategy{
    @Override
    public String replaceBanishedWord(String bWord) {

        if (bWord.length() <= 2) {
            return bWord;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(bWord.charAt(0));
        for (int i = 0; i < bWord.length() - 2; i++) {
            sb.append('*');
        }
        sb.append(bWord.charAt(bWord.length() - 1));

        return sb.toString();
    }
}
