package StoreApp;


import store.Store;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class StoreApp {
    public static void main(String[] args) {
//        Store store = new Store();
//        store.fillStore();
//        store.showInfo();

        getStore.fillStore();
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}
