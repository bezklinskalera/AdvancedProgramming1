package bookStore;

public interface BookFactory {

    public Book createBook(String author, String title, double price);

}