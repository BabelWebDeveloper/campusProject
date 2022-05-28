package pl.britenet.btnbackendmay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus.obj.model.Category;
import pl.britenet.campus.obj.model.Customer;
import pl.britenet.campus.service.CategoryService;
import pl.britenet.campus.service.CustomerService;

import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{categoryId}")
    public Optional<Category> getCategory(@PathVariable int categoryId) {
        return this.categoryService.retrieve(categoryId);
    }

    @PostMapping
    public void createCustomer(@RequestBody Category category) {
        this.categoryService.create(category);
    }

    @PutMapping
    public void updateCustomer(@RequestBody Category category) {
        this.categoryService.update(category);
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCustomer(@PathVariable int categoryId) {
        this.categoryService.remove(categoryId);
    }

}
