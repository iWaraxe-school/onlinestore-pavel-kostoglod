package store;

import java.util.ArrayList;
import java.util.Random;


public class Store {
    private final ArrayList<Category> categoryList = new ArrayList<Category>();

    public void fillStore() {
        RandomStorePopulator populator = new RandomStorePopulator();

        for (CategoryNames category : CategoryNames.values()) {
            Category c = new Category(category);
            Random random = new Random();
            int r = random.nextInt(10);

            for (int i = 0; i < r; i++) {
                Product product = new Product(
                        populator.getProductName(category),
                        populator.getProductPrice(),
                        populator.getProductRate());
                c.setProductItem(product);
            }
            categoryList.add(c);
        }
    }

    public void showInfo() {
        System.out.println("The list of the categories and products in the store:");
        for (Category category: categoryList) {
            System.out.println(category.toString());
        }
    }
}
