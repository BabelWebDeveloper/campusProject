package pl.britenet.btnbackendmay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus.obj.model.Cart;
import pl.britenet.campus.service.CartService;

import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{cartId}")
    public Optional<Cart> getCart(@PathVariable int cartId) {
        return this.cartService.retrieve(cartId);
    }

    @PostMapping
    public void createCart(@RequestBody Cart cart) {
        this.cartService.create(cart);
    }

    @PutMapping
    public void updateCart(@RequestBody Cart cart) {
        this.cartService.update(cart);
    }

    @DeleteMapping("/{cartId}")
    public void deleteCart(@PathVariable int cartId) {
        this.cartService.remove(cartId);
    }

}
