package pl.britenet.campus.service;

import pl.britenet.campus.builder.*;
import pl.britenet.campus.obj.model.*;
import pl.britenet.campus.service.database.DatabaseService;

import java.util.*;

public class PaymentService {

    private final DatabaseService databaseService;

    public PaymentService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

//    public List<Payment> retrieveOrders() {
//        String sqlQuery = "SELECT p.id, p.name AS productName, p.price AS price, cp.quantity AS quantity, py.date AS paymentDate, c.isOrdered AS status, ct.first_name AS firstName, cp.id, ct.last_name AS lastName, ct.email AS email, ct.address AS address, ct.id, c.id AS cartId, py.id\n" +
//                "FROM product p\n" +
//                "INNER JOIN cartproduct cp ON cp.productId = p.id\n" +
//                "INNER JOIN cart c ON c.id = cp.cartId\n" +
//                "INNER JOIN payment py ON py.cartId = c.id\n" +
//                "INNER JOIN customer ct ON c.customerId = ct.id";
//
//
//        try {
//            return this.databaseService.performQuery(sqlQuery, resultSet -> {
//
//                List<Payment> paymentList = new ArrayList<>();
//                while (resultSet.next()) {
//                    int id = resultSet.getInt("id");
//                    int cartId = resultSet.getInt("cartId");
//                    int productId = resultSet.getInt("p.id");
//                    int cartproductId = resultSet.getInt("cp.id");
//                    int customerId = resultSet.getInt("ct.id");
//
//                    double productPrice = resultSet.getDouble("price");
//                    int productQuantity = resultSet.getInt("quantity");
//                    String paymentDate = resultSet.getString("paymentDate");
//                    String productName = resultSet.getString("productName");
//                    boolean status = resultSet.getBoolean("status");
//
//                    String firstName = resultSet.getString("firstName");
//                    String lastName = resultSet.getString("lastName");
//                    String address = resultSet.getString("address");
//                    String email = resultSet.getString("email");
//
//                    Cart cart = new CartBuilder(cartId)
//                            .setOrdered(status)
//                            .getCard();
//
//                    Product product = new ProductBuilder(productId)
//                            .setPrice(productPrice)
//                            .setName(productName)
//                            .getProduct();
//
//                    CartProduct cartProduct = new CartProductBuilder(cartproductId)
//                            .setQuantity(productQuantity)
//                            .getCardProduct();
//
//                    Customer customer = new CustomerBuilder(customerId)
//                            .setFirstName(firstName)
//                            .setLastName(lastName)
//                            .setAddress(address)
//                            .setEmail(email)
//                            .getCustomer();
//
//                    Payment payment = new PaymentBuilder(id)
//                            .setDate(paymentDate)
//                            .setCart(cart)
//                            .setProduct(product)
//                            .setCartProduct(cartProduct)
//                            .setCustomer(customer)
//                            .getPayment();
//
//                    paymentList.add(payment);
//                }
//
//                return paymentList;
//
//            });
//        } catch (RuntimeException exception) {
//            System.out.println("ERROR!");
//            System.out.println(exception.getMessage());
//
//            return new ArrayList<>();
//        }
//    }

    public List<Payment> retrieveOrdersByCustomerId(int id) {
        String sqlQuery = String.format("SELECT p.id, p.name AS productName, p.price AS price, cp.quantity AS quantity, py.date AS paymentDate, c.isOrdered AS status,ct.email, ct.first_name AS firstName, cp.id, ct.last_name AS lastName, ct.email AS email, ct.address AS address, ct.id, c.id AS cartId, py.id\n" +
                "FROM product p\n" +
                "INNER JOIN cartproduct cp ON cp.productId = p.id\n" +
                "INNER JOIN cart c ON c.id = cp.cartId\n" +
                "INNER JOIN payment py ON py.cartId = c.id\n" +
                "INNER JOIN customer ct ON c.customerId = ct.id\n" +
                "WHERE ct.id = %d", id);


        try {
            return this.databaseService.performQuery(sqlQuery, resultSet -> {

                List<Payment> paymentList = new ArrayList<>();
                while (resultSet.next()) {
                    int cartId = resultSet.getInt("cartId");
                    int productId = resultSet.getInt("p.id");
                    int cartproductId = resultSet.getInt("cp.id");
                    int customerId = resultSet.getInt("ct.id");
                    String email = resultSet.getString("ct.email");

                    double productPrice = resultSet.getDouble("price");
                    int productQuantity = resultSet.getInt("quantity");
                    String paymentDate = resultSet.getString("paymentDate");
                    String productName = resultSet.getString("productName");
                    boolean status = resultSet.getBoolean("status");

                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                    String address = resultSet.getString("address");

                    Cart cart = new CartBuilder(cartId)
                            .setOrdered(status)
                            .getCard();

                    Product product = new ProductBuilder(productId)
                            .setPrice(productPrice)
                            .setName(productName)
                            .getProduct();

                    CartProduct cartProduct = new CartProductBuilder(cartproductId)
                            .setQuantity(productQuantity)
                            .getCardProduct();

                    Customer customer = new CustomerBuilder(customerId)
                            .setFirstName(firstName)
                            .setLastName(lastName)
                            .setAddress(address)
                            .setEmail(email)
                            .getCustomer();

                    Payment payment = new PaymentBuilder(id)
                            .setDate(paymentDate)
                            .setCart(cart)
                            .setProduct(product)
                            .setCartProduct(cartProduct)
                            .setCustomer(customer)
                            .getPayment();

                    paymentList.add(payment);
                }
                return paymentList;

            });
        } catch (RuntimeException exception) {
            System.out.println("ERROR!");
            System.out.println(exception.getMessage());

            return new ArrayList<>();
        }
    }
    public Optional<Payment> retrieve(int id) {
        String sqlQuery = String.format("SELECT * FROM payment WHERE id=%d", id);

        try {
            Payment payment = this.databaseService.performQuery(sqlQuery, resultSet -> {

                if (resultSet.next()) {
                    int customer_id = resultSet.getInt("cartId");
                    String date = resultSet.getString("date");

                    return new PaymentBuilder(id)
                            .setCartId(customer_id)
                            .setDate(date)
                            .getPayment();
                }
                return null;

            });

            return Optional.of(payment);
        } catch (RuntimeException e) {
            System.out.println("ERROR!");
            System.out.println(e.getMessage());
            return Optional.empty();
        }

    }

    public Payment create(Payment payment) {
        String dml = String.format("INSERT INTO payment (cartId, date) VALUES (%d, '%s')",
                payment.getCardId(),
                payment.getDate());

        try {
            this.databaseService.performDML(dml);
        } catch (RuntimeException e) {
            System.out.println("ERROR!");
            System.out.println(e.getMessage());
        }
        return payment;
    }

    public void remove(int id) {
        String dml = String.format("DELETE FROM payment WHERE id=%d", id);
        try {
            this.databaseService.performDML(dml);
        } catch (RuntimeException e) {
            System.out.println("ERROR!");
            System.out.println(e.getMessage());
        }
    }

    public Payment update(Payment payment) {
        String dml = String.format("UPDATE payment SET cartId=%d, date='%s' WHERE id=%d",
                payment.getCardId(),
                payment.getDate(),
                payment.getId());

        try {
            this.databaseService.performDML(dml);
        } catch (RuntimeException e) {
            System.out.println("ERROR!");
            System.out.println(e.getMessage());
        }
        return payment;
    }

    public void display(int id){
        try {
            Payment payment = this.retrieve(id).orElseThrow();
            System.out.println(payment);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

    }

//    public void display(){
//        for (Payment payment : this.payments){
//            System.out.println(payment);
//        }
//    }
}
