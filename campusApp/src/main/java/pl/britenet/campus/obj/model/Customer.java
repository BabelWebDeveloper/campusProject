package pl.britenet.campus.obj.model;

public class Customer {

    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String address;

//    private Cart cart;
//    private CartProduct cartProduct;
//    private Product product;
//    private Category category;

    public Customer(int id) {
        this.id = id;
    }
    public Customer() {
        this.id = 0;
    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

//    public Cart getCart() {
//        return cart;
//    }
//
//    public void setCart(Cart cart) {
//        this.cart = cart;
//    }
//
//    public CartProduct getCartProduct() {
//        return cartProduct;
//    }
//
//    public void setCartProduct(CartProduct cartProduct) {
//        this.cartProduct = cartProduct;
//    }
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
//
//    public Category getCategory() {
//        return category;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }

    @Override
    public String toString() {
        try {
            return String.format("first name: %s, last name: %s", first_name, last_name);
        } catch (NullPointerException e){
            return null;
        }

    }
}
