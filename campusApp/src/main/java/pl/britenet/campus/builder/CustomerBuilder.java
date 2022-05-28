package pl.britenet.campus.builder;

import pl.britenet.campus.obj.model.*;

public class CustomerBuilder {
    private final Customer customer;

    public CustomerBuilder(int id) {
        this.customer = new Customer(id);
    }

    public CustomerBuilder setFirstName(String name) {
        this.customer.setFirst_name(name);
        return this;
    }

    public CustomerBuilder setLastName(String name) {
        this.customer.setLast_name(name);
        return this;
    }

    public CustomerBuilder setEmail(String email) {
        this.customer.setEmail(email);
        return this;
    }

    public CustomerBuilder setAddress(String address) {
        this.customer.setAddress(address);
        return this;
    }

//    public CustomerBuilder setCategory (Category category) {
//        this.customer.setCategory(category);
//        return this;
//    }
//
//    public CustomerBuilder setProduct (Product product) {
//        this.customer.setProduct(product);
//        return this;
//    }
//
//    public CustomerBuilder setCart (Cart cart) {
//        this.customer.setCart(cart);
//        return this;
//    }
//
//    public CustomerBuilder setCartProduct (CartProduct cartProduct) {
//        this.customer.setCartProduct(cartProduct);
//        return this;
//    }


    public Customer getCustomer() {
        return this.customer;
    }
}
