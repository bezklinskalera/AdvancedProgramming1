package bookStore;

public class FlexSalesBookStore extends BookStore{

    private FlexDiscount flexDiscount;

    public FlexSalesBookStore( FlexDiscount flexDiscount) {
        super();
        this.flexDiscount = flexDiscount;
    }

    public void setDiscount(FlexDiscount flexDiscount){
        this.flexDiscount = flexDiscount;
    }

    public FlexDiscount getDiscount() {
        return flexDiscount;
    }

    public void addBook(String author, String title, double basePrice){

        Book book = new Book(author,title,basePrice);
        double discount = flexDiscount.getDiscount(book);

        if(discount != 0){
            BookOnSale bookOnSale = new BookOnSale(author,title,basePrice, discount);
            addBook(bookOnSale);
        } else {
            super.addBook(book);
        }


    }

    @Override
    public String toString() {
        return flexDiscount.toString() + "\n" + super.toString();
    }
}
