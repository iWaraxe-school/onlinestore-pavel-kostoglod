package StoreApp;

import orders.OrderListCleanupTask;
import store.Store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;

public class StoreInteraction {

    public static void runStoreInteraction(Store store) {
        store.fillStore("HTTP");

        try {
            Timer timer = new Timer();
            timer.schedule(new OrderListCleanupTask(), 0, 120000);
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
                        store.order();
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
