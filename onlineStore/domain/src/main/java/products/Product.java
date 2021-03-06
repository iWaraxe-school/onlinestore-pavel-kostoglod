package products;

public class Product {
    private final String name;
    private final double price;
    private final double rate;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getRate() {
        return rate;
    }

    public Product(String name, double price, double rate) {
        this.name = name;
        this.price = price;
        this.rate = rate;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, price: %.2f, rate: %.2f; %n", name, price, rate);
    }
}
