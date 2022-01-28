package StoreApp;

import store.Store;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class StoreApp {
    public static void main(String[] args) {
        Store store = Store.getInstance();
        store.fillStore();
        StoreInteraction.runStoreInteraction(store);
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
