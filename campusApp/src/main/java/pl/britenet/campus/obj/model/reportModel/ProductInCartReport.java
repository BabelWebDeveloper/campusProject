package pl.britenet.campus.obj.model.reportModel;

public class ProductInCartReport {

    private final int cartId;
    private final String productName;
    private final int quantity;

    public ProductInCartReport(int cartId, String productName, int quantity) {
        this.cartId = cartId;
        this.productName = productName;
        this.quantity = quantity;
    }

    public int getCartId() {
        return cartId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "CartId: " + cartId + " " +
                "Nazwa produktu: " + productName + " " +
                "Ilość: " + quantity;
    }
}
