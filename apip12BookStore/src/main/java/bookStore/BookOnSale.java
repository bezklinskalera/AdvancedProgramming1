package bookStore;

import java.util.Locale;

public class BookOnSale extends Book {

    private double discountPerc;


    public BookOnSale(String author, String title, double basePrice, double discountPerc) {
        super(author, title, basePrice);
        this.discountPerc = discountPerc;
    }

    public double getDiscount() {
        return discountPerc;
    }

    public double getFinalPrice(){
        return finalPrice = getTaxBasePrice() + getTaxBasePrice()*percVAT/100;
    }


    @Override
    protected double getTaxBasePrice(){
        double taxBasePriceWithoutDiscount = super.getTaxBasePrice();
        double taxBasePrice = taxBasePriceWithoutDiscount - taxBasePriceWithoutDiscount* discountPerc /100;
        return  taxBasePrice;
    }



    @Override
    public String toString() {
        return "(" + getAuthor() + "; " +
                getTitle() + "; " +
                String.format(Locale.US,"%.2f", getBasePrice()) + "; " + // Виводимо ціну з двома знаками після коми
                String.format(Locale.US,"%.1f", getDiscount()) + "%; " + // Виводимо з одним знаком після коми
                String.format(Locale.US,"%.2f", getTaxBasePrice()) + "; " +
                String.format(Locale.US,"%.1f", getVAT())  + "%; " +
                String.format(Locale.US,"%.3f", getFinalPrice()) + ")";
    }

}
