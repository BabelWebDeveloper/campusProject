package pl.britenet.campus.builder;

import pl.britenet.campus.obj.model.Cart;
import pl.britenet.campus.obj.model.Customer;

public class CartBuilder {
    private final Cart cart;

    public CartBuilder(int id) {
        this.cart = new Cart(id);
    }

    public CartBuilder setCustomerId(int customer_id) {
        this.cart.setCustomerId(customer_id);
        return this;
    }

    public CartBuilder setTotal_Cost(double total_cost) {
        this.cart.setTotal_cost(total_cost);
        return this;
    }

    public CartBuilder setOrdered(boolean ordered) {
        this.cart.setOrdered(ordered);
        return this;
    }

    public CartBuilder setCustomer(Customer customer) {
        this.cart.setCustomer(customer);
        return this;
    }

    public Cart getCard() {
        return this.cart;
    }
}
