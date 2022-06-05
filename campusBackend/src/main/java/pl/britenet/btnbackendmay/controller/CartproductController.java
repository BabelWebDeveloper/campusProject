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

    @GetMapping("/{cartId}")
    public Optional<CartProduct> getCartProduct(@PathVariable int cartId) {
        return this.cartProductService.retrieve(cartId);
    }

//    @CrossOrigin(origins = "http://127.0.0.1:5500")
//    @GetMapping("/cart")
//    public List<CartProduct> getCustomerCartProduct(@RequestParam(name = "id") @PathVariable int id) {
//        return this.cartProductService.retrieveCartProducts2(id);
//    }

//    @CrossOrigin(origins = "http://127.0.0.1:5500")
//    @GetMapping("/retrieve")
//    public Optional<CartProduct> getCartproductByCart(@RequestParam(name = "id") @PathVariable int cartId) {
//        return this.cartProductService.retrieveByCartId(cartId);
//    }

    @PostMapping
    public void createCartProduct(@RequestBody CartProduct cartProduct) {
        this.cartProductService.create(cartProduct);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PutMapping("/increment/{cartProduct}")
    public void cartProductIncrement(@PathVariable int cartProduct) {
        this.cartProductService.cartProductIncrement(cartProduct);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PutMapping("/decrement/{cartProduct}")
    public void cartProductDecrement(@PathVariable int cartProduct) {
        this.cartProductService.cartProductDecrement(cartProduct);
    }


    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @DeleteMapping("/{cartId}")
    public void deleteCartProduct(@PathVariable int cartId) {
        this.cartProductService.remove(cartId);
    }

}
