package store;

import categories.Category;
import categories.CategoryNames;
import populator.RandomStorePopulator;
import products.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Store {
    private final List<Category> categoryList = new ArrayList<Category>();

    private void fillStore() {

        RandomStorePopulator populator = new RandomStorePopulator();

        for (CategoryNames category : CategoryNames.values()) {
            Category c = new Category(category);
            Random random = new Random();
            int r = random.nextInt(10);

            for (int i = 0; i < r; i++) {
                Product product = new Product(
                        populator.getProductName(category),
                        populator.getProductPrice(),
                        populator.getProductRate()
                );
                c.setProductItem(product);
            }
            categoryList.add(c);
        }
    }

    private void showInfo() {
        System.out.println("The list of the categories and products in the store:");
        for (Category category : categoryList) {
            System.out.println(category.toString());
        }
    }
}
