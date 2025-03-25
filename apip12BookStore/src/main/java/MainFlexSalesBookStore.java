import bookStore.DiscountAuthor;
import bookStore.FlexDiscount;
import bookStore.FlexSalesBookStore;
import bookStore.SalesBookStore;

public class MainFlexSalesBookStore {

    public static void main(String[] args) {

        String[] authorsOnSale = {"George Orwell", "Isaac Asimov"};
        DiscountAuthor discountAuthor = new DiscountAuthor(20,authorsOnSale);
        FlexSalesBookStore flexSalesBookStore = new FlexSalesBookStore(discountAuthor);

        flexSalesBookStore.addBook("george orwell", "1984", 8.20);
        flexSalesBookStore.addBook("Philip K. Dick", "Do Androids Dream of Electric Sheep?", 3.50);
        flexSalesBookStore.addBook("Isaac Asimov", "Foundation and Empire", 9.40);
        flexSalesBookStore.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
        flexSalesBookStore.addBook("Aldous Huxley", "A Brave New World", 6.50);
        flexSalesBookStore.addBook("Isaac Asimov", "Foundation", 7.30);
        flexSalesBookStore.addBook("William Gibson", "Neuromancer", 8.30);
        flexSalesBookStore.addBook("Isaac Asimov", "Second Foundation", 8.10);
        flexSalesBookStore.addBook("Isaac Newton", "arithmetica universalis", 7.50);
        flexSalesBookStore.addBook("George Orwell", "1984", 6.20);
        flexSalesBookStore.addBook("Isaac Newton", "Arithmetica Universalis", 10.50);

        System.out.println(flexSalesBookStore+ "\n");

        flexSalesBookStore.deleteBook("George Orwell", "1984");
        flexSalesBookStore.deleteBook("Aldous Huxley", "A Brave New World");
        flexSalesBookStore.deleteBook("Isaac Newton", "Arithmetica Universalis");

        System.out.println(flexSalesBookStore+ "\n");

        flexSalesBookStore.getFinalPrice("Philip K. Dick", "Do Androids Dream of Electric Sheep?");
        flexSalesBookStore.getFinalPrice("isaac asimov", "foundation and empire");
        flexSalesBookStore.getFinalPrice("Ray Bradbury", "Fahrenheit 451");
        flexSalesBookStore.getFinalPrice("Isaac Asimov", "Foundation");
        flexSalesBookStore.getFinalPrice("william gibson", "neuromancer");
        flexSalesBookStore.getFinalPrice("Isaac Asimov", "Second Foundation");

        flexSalesBookStore.getFinalPrice("Isaac Newton", "Arithmetica Universalis");
        
    }
}
