package store;

import categories.Category;
import comparators.ProductComparator;
import http.Server;
import orders.Order;
import org.apache.commons.lang3.math.NumberUtils;
import populator.ConsoleAndDBPopulator;
import populator.HTTPPopulator;
import products.Product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Store {
    private final List<Category> categoryList = new ArrayList<>();
    private static Store storeInstance;

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

    public static void orderProduct(Product product) {
        Order order = Order.getInstance();
        order.addProductToOrder(product);
    }

    public void order() {
        try {
            boolean finish = false;
            showInfo();
            System.out.printf("To order the product please enter category number from 1 to %d. To stop ordering use: stop%n",
                    categoryList.size());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String command2 = bufferedReader.readLine();
            while (!finish) {
                if (NumberUtils.isDigits(command2)) {
                    if (Integer.parseInt(command2) >= 1 && Integer.parseInt(command2) <= categoryList.size()) {
                        int categoryIndex = Integer.parseInt(command2);
                        Category selectedCategory = categoryList.get(categoryIndex - 1);
                        System.out.printf("Please enter product number from 1 to %d: ",
                                selectedCategory.getProductList().size());
                        String command3 = bufferedReader.readLine();
                        if (NumberUtils.isDigits(command3)) {
                            if (Integer.parseInt(command3) >= 1 && Integer.parseInt(command3) <= selectedCategory.getProductList().size()) {
                                int productIndex = Integer.parseInt(command3);
                                Product selectedProduct = selectedCategory.getProductList().get(productIndex - 1);
                                Store.orderProduct(selectedProduct);
                                break;
                            } else {
                                System.out.println("No such product");
                                break;
                            }
                        } else {
                            System.out.println("The command is not supported");
                            break;
                        }
                    } else {
                        System.out.println("No such category");
                        break;
                    }
                } else if (command2.equals("stop")) {
                    finish = true;
                } else {
                    System.out.println("The command is not supported");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fillStore(String mode) {

        if (mode.equals("DB")) {
            ConsoleAndDBPopulator consoleAndDBPopulator = new ConsoleAndDBPopulator();
            consoleAndDBPopulator.fillStore();
        } else if (mode.equals("HTTP")) {
            Server httpServer = new Server();
            httpServer.startServer();

            HTTPPopulator httpPopulator = new HTTPPopulator();
            httpPopulator.fillStore();
        } else {
            throw new IllegalArgumentException("Illegal argument");
        }
    }

    public void showInfo() {
        System.out.println("The list of the categories and products in the store:");
        for (Category category : categoryList) {
            System.out.println(category.toString());
        }
    }
}
