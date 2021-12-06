package store;

import java.util.ArrayList;

public class Category {
    private final CategoryNames name;
    private final ArrayList<Product> productList = new ArrayList<Product>();

    public Category(CategoryNames name) {
        this.name = name;
    }

    public void setProductItem(Product product) {
        this.productList.add(product);
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append(String.format("Category name: %s. The list of products: %n", name));
        for (Product product: productList) {
            info.append(product.toString());
        }
        return info.toString();
    }
}
