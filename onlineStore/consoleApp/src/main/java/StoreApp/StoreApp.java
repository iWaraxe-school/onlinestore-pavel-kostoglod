package StoreApp;


import categories.Category;
import categories.CategoryNames;
import comparators.ProductComparator;
import products.Product;
import store.Store;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StoreApp {
    public static void main(String[] args) {
//        Store store = new Store();
//        store.fillStore();
//        store.showInfo();

        Category c1 = new Category(CategoryNames.SUSHI);
        List<Product> lp = new ArrayList<>();
        c1.setProductItem(new Product("B", 2.0, 2.0));
        c1.setProductItem(new Product("B", 1.0, 1.0));
        c1.setProductItem(new Product("B", 1.0, 1.0));
        c1.setProductItem(new Product("B", 1.0, 2.0));
        c1.setProductItem(new Product("A", 1.0, 1.0));
        c1.setProductItem(new Product("A", 2.0, 1.0));
        c1.setProductItem(new Product("A", 2.0, 2.0));
        c1.setProductItem(new Product("A", 2.0, 3.0));
        c1.setProductItem(new Product("A", 3.0, 3.0));
        c1.setProductItem(new Product("A", 3.0, 2.0));
        c1.setProductItem(new Product("A", 3.0, 1.0));

        c1.sort();

//        for (Category category: store.getCategoryList()) {
//            category.sort();
//        }

    }
}


class getStore {
    public static void fillStore() {
        try {
            Class<?> storeClass = Class.forName(Store.class.getName());
            Store store = (Store) storeClass.getConstructor().newInstance();
            Method fillStore = storeClass.getDeclaredMethod("fillStore");
            Method showInfo = storeClass.getDeclaredMethod("showInfo");
            fillStore.setAccessible(true);
            showInfo.setAccessible(true);
            fillStore.invoke(store);
            showInfo.invoke(store);

        } catch (ClassNotFoundException |
                InstantiationException |
                IllegalAccessException |
                InvocationTargetException |
                NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
