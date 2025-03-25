package bookStore;

public class DiscountPrice implements FlexDiscount{

    private double discountPer;
    private double priceThreshold;


    public DiscountPrice(double discountPer, double priceThreshold) {
        this.discountPer = discountPer;
        this.priceThreshold = priceThreshold;

    }

    public double getDiscount(Book book) {
        if (book.getBasePrice() >= priceThreshold) {
            return discountPer;
        }
        return 0;
    }

    @Override
    public String toString() {
        return discountPer + "% (" + priceThreshold + ")";
    }
}

