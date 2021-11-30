package utils;

import com.github.javafaker.Faker;
import store.Category;
import store.Product;
import store.Store;

public class RandomStorePopulator {

    static Store populateTheStore(int categoryNum, int productNum) {
        Faker faker = new Faker();
        Store store = new Store();

        for (int i=0; i < categoryNum; i++) {
            Category category = new Category(faker.commerce().productName());

            for (int n=0; n < productNum; n++) {
                Product product = new Product(faker.commerce().productName(),
                        Double.parseDouble(faker.commerce().price()),
                        Double.parseDouble(faker.commerce().price()));
                category.setProductItem(product);
            }

            store.setCategoryItem(category);
        }

        return store;
    }
}
