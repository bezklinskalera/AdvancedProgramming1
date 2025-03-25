package bookStore;

import java.util.Arrays;

public class DiscountAuthor implements FlexDiscount{

    private double discountPer;
    private String[] authors;

    public DiscountAuthor(double discountPer, String[] authors) {
        this.discountPer = discountPer;
        this.authors = authors;
    }

    public double getDiscount(Book book) {

        for (String author : authors) {
            if (author.equalsIgnoreCase(book.getAuthor())) {
                return discountPer;
            }
        }

        return 0;
    }

    @Override
    public String toString() {

        String result = discountPer + "%[";

        for (int i = 0; i < authors.length; i++) {
            result += authors[i];
            if (i < authors.length - 1) {
                result += ", ";
            }
        }

        result += "]";

        return result;}


}
