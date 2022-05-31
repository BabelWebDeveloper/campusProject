package pl.britenet.btnbackendmay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus.obj.model.Product;
import pl.britenet.campus.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping
    public List<Product> getProducts() {
        return this.productService.retrieveAll();
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/{productId}")
    public Optional<Product> getProduct(@PathVariable int productId) {
        System.out.println("Here");
        return this.productService.retrieve(productId);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/search")
    public Optional<Product> getProduct(@RequestParam(name = "name") @PathVariable String name) {
        System.out.println("Here2");
        return this.productService.retrieveName(name);
    }

    @PostMapping
    public void createProduct(@RequestBody Product product) {
        this.productService.create(product);
    }

    @PutMapping
    public void updateProduct(@RequestBody Product product) {
        this.productService.update(product);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable int productId) {
        this.productService.remove(productId);
    }

}
