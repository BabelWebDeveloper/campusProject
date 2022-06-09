package pl.britenet.campus.service;

import pl.britenet.campus.builder.CartBuilder;
import pl.britenet.campus.builder.CategoryBuilder;
import pl.britenet.campus.builder.ProductBuilder;
import pl.britenet.campus.obj.model.Cart;
import pl.britenet.campus.obj.model.Category;
import pl.britenet.campus.obj.model.Product;
import pl.britenet.campus.service.database.DatabaseService;

import java.util.*;

public class CategoryService {

    private final DatabaseService databaseService;

    public CategoryService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public List<Category> retrieveAll() {
        String sqlQuery = "SELECT * FROM category";

        try {
            return this.databaseService.performQuery(sqlQuery, resultSet -> {

                List<Category> categories = new ArrayList<>();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");

                    Category category = new CategoryBuilder(id)
                            .setName(name)
                            .getCategory();

                    categories.add(category);
                }

                return categories;

            });
        } catch (RuntimeException exception) {
            System.out.println("ERROR!");
            System.out.println(exception.getMessage());

            return new ArrayList<>();
        }
    }

    public List<Category> retrieveAllByName(String name) {
        String sqlQuery = String.format("SELECT p.name, p.description, p.price, p.id\n" +
                "FROM product p\n" +
                "INNER JOIN category c ON p.categoryId = c.id\n" +
                "WHERE c.name LIKE '%s'", name);

        try {
            return this.databaseService.performQuery(sqlQuery, resultSet -> {

                List<Category> categories = new ArrayList<>();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int productId = resultSet.getInt("p.id");
                    String productDesc = resultSet.getString("p.description");
                    String productName = resultSet.getString("p.name");
                    double productPrice = resultSet.getDouble("p.price");

                    Product product = new ProductBuilder(productId)
                            .setName(productName)
                            .setDescription(productDesc)
                            .setPrice(productPrice)
                            .getProduct();

                    Category category = new CategoryBuilder(id)
                            .setName(name)
                            .setProduct(product)
                            .getCategory();

                    categories.add(category);
                }

                return categories;

            });
        } catch (RuntimeException exception) {
            System.out.println("ERROR!");
            System.out.println(exception.getMessage());

            return new ArrayList<>();
        }
    }
    public Optional<Category> retrieve(int id) {
        String sqlQuery = String.format("SELECT * FROM category WHERE id=%d", id);

        try {
            Category category = this.databaseService.performQuery(sqlQuery, resultSet -> {

                if (resultSet.next()) {
                    String name = resultSet.getString("name");

                    return new CategoryBuilder(id)
                            .setName(name)
                            .getCategory();

                }
                return null;

            });

            return Optional.of(category);
        } catch (RuntimeException e) {
            System.out.println("ERROR!");
            System.out.println(e.getMessage());
            return Optional.empty();
        }

    }

    public Category create(Category category) {
        String dml = String.format("INSERT INTO category (name) VALUES ('%s')",
                category.getName());

        try {
            this.databaseService.performDML(dml);
        } catch (RuntimeException e) {
            System.out.println("ERROR!");
            System.out.println(e.getMessage());
        }
        return category;
    }

    public void remove(int id) {
        String dml = String.format("DELETE FROM category WHERE id=%d", id);
        try {
            this.databaseService.performDML(dml);
        } catch (RuntimeException e) {
            System.out.println("ERROR!");
            System.out.println(e.getMessage());
        }
    }

    public Category update(Category category) {
        String dml = String.format("UPDATE category SET name='%s' WHERE id=%d",
                category.getName(),
                category.getId());

        try {
            this.databaseService.performDML(dml);
        } catch (RuntimeException e) {
            System.out.println("ERROR!");
            System.out.println(e.getMessage());
        }
        return category;
    }

    public void display(int id){
        try {
            Category category = this.retrieve(id).orElseThrow();
            System.out.println(category);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

    }

}
