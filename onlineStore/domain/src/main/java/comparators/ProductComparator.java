package comparators;

import Parser.Parser;
import products.Product;

import java.util.*;

public class ProductComparator implements Comparator<Product> {

    public int compare(Product o1, Product o2) {
        return o1.getName().compareTo(o2.getName());
    }

    private static Comparator<Product> getComparator(String sortKey) {
        if("name".equals(sortKey)) {
            return Comparator.comparing(Product::getName);
        } else if ("price".equals(sortKey)) {
            return Comparator.comparing(Product::getPrice);
        } else if ("rate".equals(sortKey)) {
            return Comparator.comparing(Product::getRate);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static List<Product> sortProductList(List<Product> productList) {
        List<Product> pl = new ArrayList(productList);
        HashMap<String, String> sortingMap = Parser.getSorting();

        for (String sortKey : sortingMap.keySet()) {
            if (sortingMap.get(sortKey).equals("asc")) {
                pl.sort(getComparator(sortKey));
            } else if (sortingMap.get(sortKey).equals("desc")) {
                pl.sort(getComparator(sortKey).reversed());
            }
        }
        return pl;

    }

    public static void sortProductListbyPrice(List<Product> productList) {
        productList.sort(getComparator("price").reversed());
    }
}
