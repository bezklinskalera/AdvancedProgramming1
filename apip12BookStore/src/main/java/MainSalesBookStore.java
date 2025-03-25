import bookStore.SalesBookStore;

public class MainSalesBookStore {

    public static void main(String[] args) {

        String[] authorsOnSale = {"George Orwell", "Isaac Asimov"};
        SalesBookStore salesBookStore = new SalesBookStore(20.0, authorsOnSale);

        salesBookStore.addBook("george orwell", "1984", 8.20);
        salesBookStore.addBook("Philip K. Dick", "Do Androids Dream of Electric Sheep?", 3.50);
        salesBookStore.addBook("Isaac Asimov", "Foundation and Empire", 9.40);
        salesBookStore.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
        salesBookStore.addBook("Aldous Huxley", "A Brave New World", 6.50);
        salesBookStore.addBook("Isaac Asimov", "Foundation", 7.30);
        salesBookStore.addBook("William Gibson", "Neuromancer", 8.30);
        salesBookStore.addBook("Isaac Asimov", "Second Foundation", 8.10);
        salesBookStore.addBook("Isaac Newton", "arithmetica universalis", 7.50);
        salesBookStore.addBook("George Orwell", "1984", 6.20);
        salesBookStore.addBook("Isaac Newton", "Arithmetica Universalis", 10.50);

        System.out.println(salesBookStore+ "\n");

        salesBookStore.deleteBook("George Orwell", "1984");
        salesBookStore.deleteBook("Aldous Huxley", "A Brave New World");
        salesBookStore.deleteBook("Isaac Newton", "Arithmetica Universalis");

        System.out.println(salesBookStore+ "\n");

        salesBookStore.getFinalPrice("Philip K. Dick", "Do Androids Dream of Electric Sheep?");
        salesBookStore.getFinalPrice("isaac asimov", "foundation and empire");
        salesBookStore.getFinalPrice("Ray Bradbury", "Fahrenheit 451");
        salesBookStore.getFinalPrice("Isaac Asimov", "Foundation");
        salesBookStore.getFinalPrice("william gibson", "neuromancer");
        salesBookStore.getFinalPrice("Isaac Asimov", "Second Foundation");

        salesBookStore.getFinalPrice("Isaac Newton", "Arithmetica Universalis");


    }
}
