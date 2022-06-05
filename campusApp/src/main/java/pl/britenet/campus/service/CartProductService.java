package pl.britenet.campus.service;

import pl.britenet.campus.builder.*;
import pl.britenet.campus.obj.model.*;
import pl.britenet.campus.service.database.DatabaseService;

import java.util.*;

public class CartProductService {

    private final DatabaseService databaseService;

    public CartProductService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Optional<CartProduct> retrieve(int id) {
        String sqlQuery = String.format("SELECT * FROM cartproduct WHERE id =%d", id);

        try {
            CartProduct cartProduct = this.databaseService.performQuery(sqlQuery, resultSet -> {

                if (resultSet.next()) {
                    int card_id = resultSet.getInt("cartId");
                    int product_id = resultSet.getInt("productId");
                    int quantity = resultSet.getInt("quantity");

                    return new CartProductBuilder(id)
                            .setCardId(card_id)
                            .setProductId(product_id)
                            .setQuantity(quantity)
                            .getCardProduct();
                }
                return null;

            });

            return Optional.of(cartProduct);
        } catch (RuntimeException e) {
            System.out.println("ERROR!");
            System.out.println(e.getMessage());
            return Optional.empty();
        }

    }



    public CartProduct create(CartProduct cartProduct) {
        String dml = String.format("INSERT INTO cartproduct (cartId, productId) VALUES (%d, %d)",
                cartProduct.getCard_id(),
                cartProduct.getProductId());

        try {
            this.databaseService.performDML(dml);
        } catch (RuntimeException e) {
            System.out.println("ERROR!");
            System.out.println(e.getMessage());
        }
        return cartProduct;
    }

    public void remove(int id) {
        String dml = String.format("DELETE FROM cartproduct WHERE id=%d", id);
        try {
            this.databaseService.performDML(dml);
        } catch (RuntimeException e) {
            System.out.println("ERROR!");
            System.out.println(e.getMessage());
        }
    }

    public CartProduct update(CartProduct cartProduct) {
        String dml = String.format("UPDATE cartproduct SET cartId=%d, productId=%d, quantity=%d WHERE id=%d",
                cartProduct.getCard_id(),
                cartProduct.getProductId(),
                cartProduct.getQuantity(),
                cartProduct.getId());

        try {
            this.databaseService.performDML(dml);
        } catch (RuntimeException e) {
            System.out.println("ERROR!");
            System.out.println(e.getMessage());
        }
        return cartProduct;
    }

    public void cartProductIncrement(int cartProduct) {
        String dml = String.format("UPDATE cartproduct SET quantity=cartproduct.quantity + 1 WHERE id=%d",cartProduct);

        try {
            this.databaseService.performDML(dml);
        } catch (RuntimeException e) {
            System.out.println("ERROR!");
            System.out.println(e.getMessage());
        }
    }

    public void cartProductDecrement(int cartProduct) {
        String dml = String.format("UPDATE cartproduct SET quantity=cartproduct.quantity - 1 WHERE id=%d",cartProduct);

        try {
            this.databaseService.performDML(dml);
        } catch (RuntimeException e) {
            System.out.println("ERROR!");
            System.out.println(e.getMessage());
        }
    }

    public void display(int id){
        CartProduct cartProduct = this.retrieve(id).orElseThrow();
        System.out.println(cartProduct);
    }

}
