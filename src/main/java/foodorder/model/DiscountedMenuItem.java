package foodorder.model;

public class DiscountedMenuItem extends MenuItem {
    private final double discountPercentage;

    public DiscountedMenuItem(String name, double price, double discountPercentage) {
        super(name, price);
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double getPrice() {
        return super.getPrice() * (1 - discountPercentage / 100);
    }
}
