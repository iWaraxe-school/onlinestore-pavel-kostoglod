package json;

import categories.CategoryNames;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import populator.RandomStorePopulator;

import java.util.Random;

public class JSONComposer {
    public static JSONObject categoryToJSON(CategoryNames category) {
        RandomStorePopulator populator = new RandomStorePopulator();
        JSONObject categoryJSON = new JSONObject();

        JSONArray productList = new JSONArray();
        Random random = new Random();
        int r = random.nextInt(9) + 1;

        for (int i = 0; i < r; i++) {
            JSONObject productJSON = new JSONObject();
            productJSON.put("name", populator.getProductName(category));
            productJSON.put("price", populator.getProductPrice());
            productJSON.put("rate", populator.getProductRate());
            productList.add(productJSON);
        }
        categoryJSON.put(category.toString(), productList);
        return categoryJSON;
    }
}
