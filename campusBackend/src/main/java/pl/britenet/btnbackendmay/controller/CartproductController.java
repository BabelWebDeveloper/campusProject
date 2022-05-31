package pl.britenet.btnbackendmay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus.obj.model.Cart;
import pl.britenet.campus.obj.model.CartProduct;
import pl.britenet.campus.obj.model.Payment;
import pl.britenet.campus.obj.model.Product;
import pl.britenet.campus.service.CartProductService;
import pl.britenet.campus.service.CartService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cartproduct")
public class CartproductController {

    private final CartProductService cartProductService;

    @Autowired
    public CartproductController(CartProductService cartProduct) {
        this.cartProductService = cartProduct;
    }


    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping
    public List<CartProduct> getCartproducts() {
        return this.cartProductService.retrieveCartproducts(3);
    }


    @GetMapping("/{cartId}")
    public Optional<CartProduct> getCartProduct(@PathVariable int cartId) {
        return this.cartProductService.retrieve(cartId);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/cart")
    public List<CartProduct> getCartProduct(@RequestParam(name = "email") @PathVariable String email) {
        System.out.println("Order2");
        return this.cartProductService.retrieveCartProducts(email);
    }

    @PostMapping
    public void createCartProduct(@RequestBody CartProduct cartProduct) {
        this.cartProductService.create(cartProduct);
    }

    @PutMapping
    public void updateCartProduct(@RequestBody CartProduct cartProduct) {
        this.cartProductService.update(cartProduct);
    }

    @DeleteMapping("/{cartId}")
    public void deleteCartProduct(@PathVariable int cartId) {
        this.cartProductService.remove(cartId);
    }

}
