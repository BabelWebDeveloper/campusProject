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


    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/createCart/createProduct")
    public Map<String, String> createCartCartProduct(@RequestBody Map<String, String> json) {
        String customerId = json.get("customerId");//to są row
        String productId = json.get("productId");//to są row
        return this.cartService.create(customerId,productId);//i tutaj tyle ile potrzeba zaciągane ze stringów
    }


    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PutMapping("/pay")
    public void updateCart(@RequestParam(name = "id") @PathVariable int customerId) {
        this.cartService.update(customerId);
    }

    @DeleteMapping("/{cartId}")
    public void deleteCart(@PathVariable int cartId) {
        this.cartService.remove(cartId);
    }

}
