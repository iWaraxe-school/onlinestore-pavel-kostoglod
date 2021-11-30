package store;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private final String name;
    private ArrayList<Product> productList = new ArrayList<Product>();

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public void setProductItem(Product product) {
        this.productList.add(product);
    }
}
