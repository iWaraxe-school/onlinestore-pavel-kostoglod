package db;

import categories.Category;
import products.Product;

import java.sql.*;

public class DBService extends DBUtil {

    public void prepareDB() {
        Connection connection = get_connection();
        PreparedStatement preparedStatement = null;
        String create_categories_table_query = "CREATE TABLE IF NOT EXISTS CATEGORIES (" +
                "id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
                "name VARCHAR NOT NULL)";
        String create_products_table_query = "CREATE TABLE IF NOT EXISTS PRODUCTS (" +
                "id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
                "category_id INTEGER NOT NULL," +
                "name VARCHAR NOT NULL," +
                "price DOUBLE NOT NULL," +
                "rate DOUBLE NOT NULL," +
                "FOREIGN KEY (category_id)" +
                "REFERENCES Categories (id))";

        try {
            preparedStatement = connection.prepareStatement(create_categories_table_query);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(create_products_table_query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addCategory(Category category) {
        Connection connection = get_connection();
        PreparedStatement preparedStatement = null;
        String query = String.format("INSERT INTO CATEGORIES (name) VALUES ('%s')", category.getName());
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int getCategoryIndexByName(Category category) throws SQLException {
        Connection connection = get_connection();
        Statement statement = null;
        int index = 0;
        String query = String.format("SELECT id FROM CATEGORIES WHERE name = '%s'", category.getName());
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                index = resultSet.getInt("ID");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return index;
    }

    public void addProduct(Product product, Category category) {
        Connection connection = get_connection();
        PreparedStatement preparedStatement = null;

        try {
            String query = String.format("INSERT INTO PRODUCTS (category_id, name, price, rate) VALUES (%d, '%s', %.2f, %.2f)"
                    ,getCategoryIndexByName(category)
                    ,product.getName()
                    ,product.getPrice()
                    ,product.getRate());

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
