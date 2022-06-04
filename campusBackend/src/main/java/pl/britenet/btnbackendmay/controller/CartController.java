package pl.britenet.btnbackendmay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus.obj.model.Cart;
import pl.britenet.campus.obj.model.CartProduct;
import pl.britenet.campus.service.CartProductService;
import pl.britenet.campus.service.CartService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/getCart/{cartId}")
    public Optional<Cart> getCart(@PathVariable int cartId) {
        return this.cartService.retrieve(cartId);
    }

////    Sprawdź cart po customerze:
//    @CrossOrigin(origins = "http://127.0.0.1:5500")
//    @GetMapping("/checkCart")
//    public Optional<Cart> getCartCustomer(@RequestParam(name = "id") @PathVariable int id) {
//        return this.cartService.retrieveCartCustomer(id);
//    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/ordered-orders")
    public List<Cart> getOrderedOrders(@RequestParam(name = "id") @PathVariable int customerId) {
        return this.cartService.retrieveOrderedOrders(customerId);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/not-ordered-orders")
    public List<Cart> getNotOrdered(@RequestParam(name = "id") @PathVariable int customerId) {
        return this.cartService.retrieveProductsInCart(customerId);
    }

    @PostMapping("/createCart")
    public void createCart(@RequestBody Cart cart) {
        this.cartService.create(cart);
    }
//    @CrossOrigin(origins = "http://127.0.0.1:5500")
//    @PostMapping("/createCart/createProduct")
//    public Map<String, String> createCartCartProduct(@RequestBody Map<String, String> json) {
//        int customerId = Integer.parseInt(json.get("customerId"));
//        int productId = Integer.parseInt(json.get("productId"));
//        return (Map<String, String>) this.cartService.create(customerId,productId);
//    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/createCart/createProduct")
    public Map<Integer, Integer> createCartCartProduct(@RequestBody Map<String, String> json) {
        int customerId = Integer.parseInt(json.get("customerId"));
        int productId = Integer.parseInt(json.get("productId"));
        return this.cartService.create(customerId,productId);
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
