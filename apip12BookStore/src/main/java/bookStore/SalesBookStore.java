package bookStore;

import java.util.ArrayList;
import java.util.Arrays;

public class SalesBookStore extends BookStore {

    private double discountPerc;
    private String[] authorsOnSale;
    private ArrayList<BookOnSale> booksSales;

    public SalesBookStore(double discountPerc, String[] authorsOnSale) {
        super();
        this.discountPerc = discountPerc;
        this.authorsOnSale = authorsOnSale;
        this.booksSales = new ArrayList(); // Ініціалізація списку книг
    }

    public void setSale(double discountPerc, String[] authorsOnSale) {
        this.discountPerc = discountPerc;
        this.authorsOnSale = authorsOnSale;
    }

    public void setSale(String[] authorsOnSale) {
        this.discountPerc = 0.0;
        this.authorsOnSale = authorsOnSale;
    }

    public String[] getSale() {
        return authorsOnSale;
    }

    public double getDiscount() {
        return discountPerc;
    }

    @Override
    public void addBook(String author, String title, double basePrice) {
        boolean authorInTheSaleList = AuthorInTheSaleList(author);

        if (authorInTheSaleList) {
            addBook(new BookOnSale(author, title, basePrice, discountPerc));
        } else {
            addBook(new Book(author, title, basePrice));
        }
    }
    private boolean AuthorInTheSaleList(String author) {
        for (String saleAuthor : authorsOnSale) {
            if (saleAuthor.equalsIgnoreCase(author)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String result = getDiscount() + "%[";

        for (int i = 0; i < authorsOnSale.length; i++) {
            result += authorsOnSale[i];
            if (i < authorsOnSale.length - 1) {
                result += ", ";
            }
        }

        result += "]\n[";

        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            result += book.toString();

            if (i < books.size() - 1) {
                result += ",\n";
            }
        }

        result += "]";
        return result;
    }
}
