package StoreApp;

import db.DBService;
import store.Store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class StoreApp {
    public static void main(String[] args) {
        try {
            DBService dbService = new DBService();
            dbService.prepareDB();
            Store store = Store.getInstance();
            store.fillStore();


            boolean flag = true;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("The store is created and filled with random products.");
            while (flag) {
                System.out.println("To interact with the store use next commands: sort, top, quit:");
                String command = bufferedReader.readLine();

                switch (command) {
                    case "sort":
                        store.sort();
                        break;
                    case "top":
                        store.top();
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
