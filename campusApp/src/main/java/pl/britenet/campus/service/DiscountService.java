package pl.britenet.campus.service;

import pl.britenet.campus.builder.CategoryBuilder;
import pl.britenet.campus.builder.CustomerBuilder;
import pl.britenet.campus.builder.DiscountBuilder;
import pl.britenet.campus.builder.ProductBuilder;
import pl.britenet.campus.obj.model.Category;
import pl.britenet.campus.obj.model.Customer;
import pl.britenet.campus.obj.model.Discount;
import pl.britenet.campus.obj.model.Product;
import pl.britenet.campus.service.database.DatabaseService;

import java.util.*;

public class DiscountService {

    private final DatabaseService databaseService;

    public DiscountService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Optional<Discount> retrieve(int id) {
        String sqlQuery = String.format("SELECT p.name AS \"produkt\", d.description AS \"promocja\", c.name AS \"nazwaKategorii\", c.id, p.id, d.percent\n" +
                "FROM product p\n" +
                "RIGHT JOIN discount d\n" +
                "ON p.discountId = d.id\n" +
                "INNER JOIN category c\n" +
                "ON c.id = p.categoryId\n" +
                "WHERE d.id =%d", id);

        try {
            Discount discount = this.databaseService.performQuery(sqlQuery, resultSet -> {

                if (resultSet.next()) {
                    int discount_percent = resultSet.getInt("percent");
                    String discount_description	 = resultSet.getString("promocja");

                    String categoryName = resultSet.getString("nazwaKategorii");
                    String productName = resultSet.getString("produkt");
                    int category_id = resultSet.getInt("c.id");
                    int product_id = resultSet.getInt("p.id");

                    Category category = new CategoryBuilder(category_id)
                            .setName(categoryName)
                            .getCategory();

                    Product product = new ProductBuilder(product_id)
                            .setName(productName)
                            .setCategoryId(category_id)
                            .getProduct();

                    return new DiscountBuilder(id)
                            .setDiscountPercent(discount_percent)
                            .setDescription(discount_description)
                            .setProduct(product)
                            .setCategory(category)
                            .getDiscount();
                }
                return null;

            });

            return Optional.of(discount);
        } catch (RuntimeException e) {
            System.out.println("ERROR!");
            System.out.println(e.getMessage());
            return Optional.empty();
        }

    }

    public Discount create(Discount discount) {
        String dml = String.format("INSERT INTO discount (percent, description) VALUES (%d, '%s')",
                discount.getPercent(),
                discount.getDescription());

        try {
            this.databaseService.performDML(dml);
        } catch (RuntimeException e) {
            System.out.println("ERROR!");
            System.out.println(e.getMessage());
        }
        return discount;
    }

    public void remove(int id) {
        String dml = String.format("DELETE FROM discount WHERE id=%d", id);
        try {
            this.databaseService.performDML(dml);
        } catch (RuntimeException e) {
            System.out.println("ERROR!");
            System.out.println(e.getMessage());
        }
    }

    public Discount update(Discount discount) {
        String dml = String.format("UPDATE discount SET percent=%d, description='%s' WHERE id=%d",
                discount.getPercent(),
                discount.getDescription(),
                discount.getId());

        try {
            this.databaseService.performDML(dml);
        } catch (RuntimeException e) {
            System.out.println("ERROR!");
            System.out.println(e.getMessage());
        }
        return discount;
    }

    public void display(int id){
        try {
            Discount discount = this.retrieve(id).orElseThrow();
            System.out.println(discount);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

    }

}
