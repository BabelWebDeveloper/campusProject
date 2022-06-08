package pl.britenet.campus.builder;

import pl.britenet.campus.obj.model.*;

import java.util.Date;

public class PaymentBuilder {
    private final Payment payment;

    public PaymentBuilder(int id) {
        this.payment = new Payment(id);
    }

    public PaymentBuilder setCartId(int cardId) {
        this.payment.setCardId(cardId);
        return this;
    }

    public PaymentBuilder setDate(Date date) {
        this.payment.setDate(date);
        return this;
    }

    public PaymentBuilder setDate() {
        this.payment.setDate();
        return this;
    }

    public PaymentBuilder setProduct(Product product) {
        this.payment.setProduct(product);
        return this;
    }

    public PaymentBuilder setCart(Cart cart) {
        this.payment.setCart(cart);
        return this;
    }

    public PaymentBuilder setCartProduct(CartProduct cartProduct) {
        this.payment.setCartProduct(cartProduct);
        return this;
    }

    public PaymentBuilder setCustomer(Customer customer) {
        this.payment.setCustomer(customer);
        return this;
    }

    public Payment getPayment() {
        return this.payment;
    }

}
