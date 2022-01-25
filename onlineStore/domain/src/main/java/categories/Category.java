package categories;

import comparators.ProductComparator;
import products.Product;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private final CategoryNames name;

    public String getName() {
        return name.toString();
    }

    public List<Product> getProductList() {
        return productList;
    }

    private List<Product> productList = new ArrayList<>();

    public Category(CategoryNames name) {
        this.name = name;
    }

    public void setProductItem(Product product) {
        this.productList.add(product);
    }

    public void sort() {
        StringBuilder info = new StringBuilder();
        info.append(String.format("Sorted list of %s category:%n", name));
        List<Product> pl = ProductComparator.sortProductList(productList);
        for (Product product : pl) {
            info.append(product.toString());
        }
        System.out.println(info);
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
