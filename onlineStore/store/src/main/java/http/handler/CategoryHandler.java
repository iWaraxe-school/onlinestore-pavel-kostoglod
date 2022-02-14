package http.handler;

import categories.Category;
import categories.CategoryNames;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import products.Product;
import store.Store;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CategoryHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        if ("POST".equals(httpExchange.getRequestMethod())) {
            Store store = Store.getInstance();
            InputStreamReader input = new InputStreamReader(httpExchange.getRequestBody());
            StringBuilder sb = new StringBuilder();
            while (input.ready()) {
                sb.append((char) input.read());
            }
            // Parse the request body to Category and Product list and put them to the Store
            try {
                System.out.println("StringBuilder content: \n" + sb);
                JSONObject categoryJSON = (JSONObject) new JSONParser().parse(sb.toString());
                String categoryName = categoryJSON.keySet().iterator().next().toString();

                Category category = new Category(
                        CategoryNames.valueOf(categoryName));
                JSONArray productListJson = (JSONArray) categoryJSON.get(categoryName);
                for (Object o : productListJson) {
                    JSONObject productJson = (JSONObject) o;
                    Product product = new Product(
                            (String) productJson.get("name"),
                            (Double) productJson.get("price"),
                            (Double) productJson.get("rate")
                    );
                    category.setProductItem(product);
                }
                store.getCategoryList().add(category);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            httpExchange.sendResponseHeaders(201, 0);
            httpExchange
                    .getResponseBody()
                    .write(sb.toString().getBytes(StandardCharsets.UTF_8));
        } else {
            httpExchange.sendResponseHeaders(405, 0);
            httpExchange
                    .getResponseBody()
                    .write("Not Implemented".getBytes(StandardCharsets.UTF_8));
        }
        httpExchange.close();
    }
}
