package bookStore;

import java.util.ArrayList;

public class BookStore {

    ArrayList<Book> books;

    public BookStore() {
        books = new ArrayList();
    }

    public void addBook(String author, String title, double basePrice){
        addBook(new Book(author, title, basePrice));
    }

    protected void addBook(Book book){

        int bookNumber = seekBook(book.getAuthor(), book.getTitle());

        if(bookNumber != -1){
            books.set(bookNumber,book);
        } else {
            books.add(book);
        }
    }

    public void deleteBook(String author, String title){

        int bookNumber = seekBook(author,title);

        if(bookNumber != -1){
            books.remove(bookNumber);
        }
        else { throw new RuntimeException("Book not found (" + author + ", " + title +
                ")");}

    }


    protected int seekBook(String author, String title) {
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getAuthor().equalsIgnoreCase(author) && book.getTitle().equalsIgnoreCase(title)) {
                return i;
            }
        }
        return -1;
    }

    public double getBasePrice(String author, String title){
        int bookNumber = seekBook(author,title);

        if(bookNumber != -1){
            Book book = books.get(bookNumber);
            return book.getBasePrice();
        }
        else { throw new RuntimeException("Book not found (" + author + ", " + title +
                ")");}
    }

    public double getFinalPrice(String author, String title){
        int bookNumber = seekBook(author,title);

        if(bookNumber != -1){
            double price = books.get(bookNumber).getFinalPrice();
            System.out.println("FinalPrice(" + author + ", " + title + "): " + price);
            return price;
        }
        else{ throw new RuntimeException("Book not found (" + author + ", " + title +
                ")");}
    }

    @Override
    public String toString() {
        String result = "[";
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
