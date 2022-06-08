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

    public Optional<Payment> retrieve(int id) {
        String sqlQuery = String.format("SELECT * FROM payment WHERE id=%d", id);

        try {
            Payment payment = this.databaseService.performQuery(sqlQuery, resultSet -> {

                if (resultSet.next()) {
                    int customer_id = resultSet.getInt("cartId");
                    String date = resultSet.getString("paymentDate");

                    return new PaymentBuilder(id)
                            .setCartId(customer_id)
                            .setDate()
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

}
