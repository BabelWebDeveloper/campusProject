package pl.britenet.btnbackendmay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus.obj.model.Cart;
import pl.britenet.campus.obj.model.CartProduct;
import pl.britenet.campus.service.CartService;

import java.util.List;
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

//    metoda do sprawdzania czy istnieje cart przypisany do customera (działa):
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/checkCart")
    public Optional<Cart> getCartCustomer(@RequestParam(name = "id") @PathVariable int id) {
        System.out.println("Order2");
        return this.cartService.retrieveCartCustomer(id);
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
