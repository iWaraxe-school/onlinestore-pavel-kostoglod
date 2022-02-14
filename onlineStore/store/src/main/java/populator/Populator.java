package populator;

import store.Store;

public interface Populator {
    RandomStorePopulator populator = new RandomStorePopulator();
    Store store = Store.getInstance();

    void fillStore();
}
