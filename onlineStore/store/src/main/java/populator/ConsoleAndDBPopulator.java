package populator;

import categories.Category;
import categories.CategoryNames;
import db.DBService;
import products.Product;

import java.util.Random;

public class ConsoleAndDBPopulator implements Populator{

    public void fillStore() {

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
            store.getCategoryList().add(c);
        }
    }
}
