package pl.britenet.campus.service;

import pl.britenet.campus.builder.CartBuilder;
import pl.britenet.campus.builder.CartProductBuilder;
import pl.britenet.campus.builder.CustomerBuilder;
import pl.britenet.campus.builder.ProductBuilder;
import pl.britenet.campus.obj.model.Cart;
import pl.britenet.campus.obj.model.CartProduct;
import pl.britenet.campus.obj.model.Customer;
import pl.britenet.campus.obj.model.Product;
import pl.britenet.campus.service.database.DatabaseService;

import java.util.*;

public class CartService {

    private final DatabaseService databaseService;

    public CartService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public List<Integer> retrieveIds() {
        String sqlQuery = "SELECT id FROM cart";

        try {
            return this.databaseService.performQuery(sqlQuery, resultSet -> {

                List<Integer> idList = new ArrayList<>();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");

                    Cart cart = new CartBuilder(id)
                            .getCard();

                    idList.add(cart.getId());
                }

                return idList;

            });
        } catch (RuntimeException exception) {
            System.out.println("ERROR!");
            System.out.println(exception.getMessage());

            return new ArrayList<>();
        }
    }

    public List<Cart> retrieveOrderedOrders(int customerId) {
        String sqlQuery = String.format("SELECT ct.id AS cartId, ct.isOrdered AS status,\n" +
                "cp.id AS cartproductId, cp.productId AS productId, SUM(cp.quantity) AS quantity,\n" +
                "p.price, p.name,\n" +
                "cr.first_name AS firstName, cr.last_name AS lastName, cr.email AS email, cr.address as address, cr.id\n" +
                "FROM cart ct\n" +
                "INNER JOIN cartProduct cp ON ct.id = cp.cartId\n" +
                "INNER JOIN product p ON p.id = cp.productId\n" +
                "INNER JOIN customer cr ON cr.id = ct.customerId\n" +
                "WHERE ct.isOrdered = 1 AND cr.id = %d\n" +
                "GROUP BY cp.productId", customerId);

        try {

            return this.databaseService.performQuery(sqlQuery, resultSet -> {
                List<Cart> orderedOrders = new ArrayList<>();

                while (resultSet.next()) {

                    int cartProductId = resultSet.getInt("cartProductId");
                    int productId = resultSet.getInt("productId");
                    int quantity = resultSet.getInt("quantity");

                    double productPrice = resultSet.getDouble("p.price");
                    String productName = resultSet.getString("p.name");

                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                    String email = resultSet.getString("email");
                    String address = resultSet.getString("address");

                    int cartId = resultSet.getInt("cartId");
                    boolean status = resultSet.getBoolean("status");

                    CartProduct cartProduct = new CartProductBuilder(cartProductId)
                            .setProductId(productId)
                            .setQuantity(quantity)
                            .getCardProduct();

                    Product product = new ProductBuilder(productId)
                            .setPrice(productPrice)
                            .setName(productName)
                            .getProduct();

                    Customer customer = new CustomerBuilder(customerId)
                            .setFirstName(firstName)
                            .setLastName(lastName)
                            .setEmail(email)
                            .setAddress(address)
                            .getCustomer();

                    Cart cart = new CartBuilder(cartId)
                            .setOrdered(status)
                            .setCartProduct(cartProduct)
                            .setProduct(product)
                            .setCustomer(customer)
                            .getCard();

                    orderedOrders.add(cart);
                }

                return orderedOrders;
            });

        } catch (RuntimeException exception) {
            System.out.println("ERROR!");
            System.out.println(exception.getMessage());

            return new ArrayList<>();
        }
    }

    public Optional<Cart> retrieveCartCustomer(int id) {
        String sqlQuery = String.format("SELECT c.id\n" +
                "FROM cart c\n" +
                "WHERE c.customerId = %d", id);

        try {
            Cart cart = this.databaseService.performQuery(sqlQuery, resultSet -> {

                if (resultSet.next()) {
                    int cartId = resultSet.getInt("c.id");

                    return new CartBuilder(cartId)
                            .setCustomerId(id)
                            .getCard();
                }
                return null;

            });

            return Optional.of(cart);

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<Cart> retrieve(int id) {
        String sqlQuery = String.format("SELECT * FROM cart WHERE id=%d", id);

        try {
            Cart cart = this.databaseService.performQuery(sqlQuery, resultSet -> {

                if (resultSet.next()) {
                    int customer_id = resultSet.getInt("customerId");
                    double total_cost = resultSet.getDouble("total_cost");
                    boolean isOrdered = resultSet.getBoolean("isOrdered");

                    return new CartBuilder(id)
                            .setCustomerId(customer_id)
                            .setTotal_Cost(total_cost)
                            .setOrdered(isOrdered)
                            .getCard();
                }
                return null;

            });

            return Optional.of(cart);

        } catch (RuntimeException e) {
            System.out.println("ERROR!");
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }



    public Cart create(int customerId, int productId) {
        String sqlQuery = String.format("SELECT ct.id, cr.id AS customerID, ct.isOrdered\n" +
                "FROM cart ct\n" +
                "INNER JOIN customer cr ON ct.customerId = cr.id\n" +
                "WHERE cr.id = %d", customerId);

        try {
            Cart cart = this.databaseService.performQuery(sqlQuery, resultSet -> {

                if (resultSet.next()) {
                    int cartId = resultSet.getInt("ct.id");
                    System.out.println(cartId + " cart istnieje");
                    String sqlQuery2 = String.format("INSERT INTO cartproduct (cartId, productId, quantity) VALUES (%d, %d, %d)",cartId,productId,1);

                    try {
                        this.databaseService.performDML(sqlQuery2);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }
                else {
                    System.out.println("Cart NIE istnieje");
                    int id = 0;
                    Cart createdCart = new CartBuilder(id)
                            .setCustomerId(customerId)
                            .setTotal_Cost(0)
                            .getCard();
                    this.create(createdCart);

                    String sqlQuery3 = String.format("SELECT ct.id, ct.customerId\n" +
                            "FROM cart ct\n" +
                            "WHERE ct.customerId = %d", customerId);
                    try {
                        Cart checkCart = this.databaseService.performQuery(sqlQuery3,resultSet1 -> {
                            if (resultSet1.next()) {
                                int cartId = resultSet1.getInt("ct.id");

                                return new CartBuilder(cartId)
                                        .getCard();
                            }
                            return null;
                        });
                        System.out.println(checkCart.getId());
                        String dml = String.format("INSERT INTO cartproduct (cartId, productId, quantity) VALUES (%d, %d, %d)",
                                checkCart.getId(),
                                productId,
                                1);

                        try {
                            this.databaseService.performDML(dml);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        return null;

                    } catch (RuntimeException e) {
                        System.out.println("ERROR!");
                        System.out.println(e.getMessage());
                    }
                }
                return null;

            });

            return cart;

        }
        catch (RuntimeException e) {
            System.out.println("ERROR! to tutaj");
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Cart create(Cart cart) {
        String dml = String.format("INSERT INTO cart (customerId, total_cost, isOrdered) VALUES (%d, 0, 0)",
                cart.getCustomerId());
        try {
            this.databaseService.performDML(dml);
        } catch (RuntimeException e) {
            System.out.println("ERROR!");
            System.out.println(e.getMessage());
        }
        return cart;
    }

    public void remove(int id) {
        String dml = String.format("DELETE FROM cart WHERE id=%d", id);
        try {
            this.databaseService.performDML(dml);
        } catch (RuntimeException e) {
            System.out.println("ERROR!");
            System.out.println(e.getMessage());
        }
    }

    public Cart update(Cart cart) {
        String dml = String.format("UPDATE cart SET customerId=%d, total_cost=%s, isOrdered=%b WHERE id=%d",
                cart.getCustomerId(),
                String.valueOf(cart.getTotal_cost()).replace(",","."),
                cart.isOrdered(),
                cart.getId());

        try {
            this.databaseService.performDML(dml);
        } catch (RuntimeException e) {
            System.out.println("ERROR!");
            System.out.println(e.getMessage());
        }
        return cart;
    }

    public void display(int id){
        try {
            Cart cart = this.retrieve(id).orElseThrow();
            System.out.println(cart);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

    }

}
