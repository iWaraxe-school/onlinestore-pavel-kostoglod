package categories;

import products.Product;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private final CategoryNames name;
    private List<Product> productList = new ArrayList<Product>();

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
        for (Product product : productList) {
            info.append(product.toString());
        }
        return info.toString();
    }
}
