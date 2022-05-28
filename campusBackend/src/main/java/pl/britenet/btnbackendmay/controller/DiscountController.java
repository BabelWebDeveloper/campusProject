package pl.britenet.btnbackendmay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus.obj.model.Discount;
import pl.britenet.campus.service.DiscountService;

import java.util.Optional;

@RestController
@RequestMapping("/api/discount")
public class DiscountController {

    private final DiscountService discountService;

    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping("/{discountId}")
    public Optional<Discount> getDiscount(@PathVariable int discountId) {
        return this.discountService.retrieve(discountId);
    }

    @PostMapping
    public void createDiscount(@RequestBody Discount discount) {
        this.discountService.create(discount);
    }

    @PutMapping
    public void updateDiscount(@RequestBody Discount discount) {
        this.discountService.update(discount);
    }

    @DeleteMapping("/{discountId}")
    public void deleteDiscount(@PathVariable int discountId) {
        this.discountService.remove(discountId);
    }

}
