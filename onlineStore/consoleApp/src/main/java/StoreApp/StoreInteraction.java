package StoreApp;

import categories.Category;
import org.apache.commons.lang3.math.NumberUtils;
import products.Product;
import store.Store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StoreInteraction {
    public static void runStoreInteraction(Store store) {
        try {
            boolean flag = true;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("The store is created and filled with random products.");
            while (flag) {
                System.out.println("To interact with the store use next commands: sort, top, order, quit:");
                String command = bufferedReader.readLine();

                switch (command) {
                    case "sort":
                        store.sort();
                        break;
                    case "top":
                        store.top();
                        break;
                    case "order":
                        boolean finish = false;
                        Store.getInstance().showInfo();
                        System.out.printf("To order the product please enter category number from 1 to %d. To stop ordering use: stop%n",
                                store.getCategoryList().size());
                        String command2 = bufferedReader.readLine();
                        while (!finish) {
                            if (NumberUtils.isDigits(command2)) {
                                if (Integer.parseInt(command2)>=1 && Integer.parseInt(command2)<=store.getCategoryList().size()) {
                                    int categoryIndex = Integer.parseInt(command2);
                                    Category selectedCategory = store.getCategoryList().get(categoryIndex - 1);
                                    System.out.printf("Please enter product number from 1 to %d: ",
                                            selectedCategory.getProductList().size());
                                    String command3 = bufferedReader.readLine();
                                    if (NumberUtils.isDigits(command3)) {
                                        if (Integer.parseInt(command3)>=1 && Integer.parseInt(command3)<=selectedCategory.getProductList().size()) {
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
                        break;
                    case "quit":
                        flag = false;
                        break;
                    default:
                        System.out.println("Command is not supported");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
