package store;

import categories.Category;
import categories.CategoryNames;
import comparators.ProductComparator;
import db.DBService;
import populator.RandomStorePopulator;
import products.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Store {
    private final List<Category> categoryList = new ArrayList<>();
    private static volatile Store storeInstance;

    private Store() {
    }

    public static Store getInstance() {
        if (storeInstance != null) {
            return storeInstance;
        }
        synchronized (Store.class) {
            if (storeInstance == null) {
                storeInstance = new Store();
            }
            return storeInstance;
        }
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void sort() {
        for (Category category : categoryList) {
            category.sort();
        }
    }

    public void top() {
        List<Product> productList = new ArrayList<>();
        for (Category category : categoryList) {
            productList.addAll(category.getProductList());
        }
        ProductComparator.sortProductListbyPrice(productList);
        System.out.println("Top 5 products by price:");
        for (int i = 0; i < 5; i++) {
            System.out.println(productList.get(i));
        }
    }

    public void fillStore() {

        RandomStorePopulator populator = new RandomStorePopulator();

        for (CategoryNames category : CategoryNames.values()) {
            Category c = new Category(category);
            DBService DBService = new DBService();
            DBService.addCategory(c);

            Random random = new Random();
            int r = random.nextInt(9) + 1;

            for (int i = 0; i < r; i++) {
                Product product = new Product(
                        populator.getProductName(category),
                        populator.getProductPrice(),
                        populator.getProductRate()
                );
                c.setProductItem(product);

                DBService.addProduct(product, c);
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
