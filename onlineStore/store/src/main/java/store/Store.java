package store;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private ArrayList<Category> categoryList = new ArrayList<Category>();

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ArrayList<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public void setCategoryItem(Category category) {
        this.categoryList.add(category);
    }

}
