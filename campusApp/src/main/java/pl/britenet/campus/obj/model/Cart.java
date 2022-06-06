package pl.britenet.campus.obj.model;

import java.util.List;

public class Cart {
    private int id;
    private int customerId;

    private String sCustomerId;

    private double total_cost;

    private boolean isOrdered;
    private CartProduct cartProduct;
    private List<CartProduct> cartProductList;
    private Product product;

    private Customer customer;

    public Cart(int id) {
        this.id = id;
    }
    public Cart() {
        this.id = 0;
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCustomerId(String customerId) {
        this.sCustomerId = customerId;
    }

    public double getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(double total_cost) {
        this.total_cost = 0;
    }

    public boolean isOrdered() {
        return isOrdered;
    }

    public Customer getCustomer() {
        return customer;
    }

    public CartProduct getCartProduct() {
        return cartProduct;
    }

    public void setCartProduct(CartProduct cartProduct) {
        this.cartProduct = cartProduct;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setOrdered(boolean ordered) {
        isOrdered = ordered;
    }

    public List<CartProduct> getCartProductList() {
        return cartProductList;
    }

    public void setCartProductList(List<CartProduct> cartProductList) {
        this.cartProductList = cartProductList;
    }

    @Override
    public String toString() {
        return String.format("cartId: %d", id);
    }
}
