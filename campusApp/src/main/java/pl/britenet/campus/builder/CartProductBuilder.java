package pl.britenet.campus.builder;

import pl.britenet.campus.obj.model.Cart;
import pl.britenet.campus.obj.model.CartProduct;
import pl.britenet.campus.obj.model.Product;

public class CartProductBuilder {
    private final CartProduct cartProduct;

    public CartProductBuilder(int id) {
        this.cartProduct = new CartProduct(id);
    }

    public CartProductBuilder setCardId(int card_id) {
        this.cartProduct.setCard_id(card_id);
        return this;
    }

    public CartProductBuilder setProductId(int product_id) {
        this.cartProduct.setProductId(product_id);
        return this;
    }

    public CartProductBuilder setQuantity(int quantity) {
        this.cartProduct.setQuantity(quantity);
        return this;
    }

//    public CartProductBuilder setCategory (Category category) {
//        this.cartProduct.setCategory(category);
//        return this;
//    }
//
//    public CartProductBuilder setProduct (Product product) {
//        this.cartProduct.setProduct(product);
//        return this;
//    }
    public CartProductBuilder setCart (Cart cart) {
        this.cartProduct.setCart(cart);
        return this;
    }

    public CartProductBuilder setProduct (Product product) {
        this.cartProduct.setProduct(product);
        return this;
    }

    public CartProduct getCardProduct() {
        return this.cartProduct;
    }
}
