package pl.britenet.btnbackendmay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus.obj.model.Customer;
import pl.britenet.campus.service.CustomerService;

import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public Optional<Customer> getCustomer(@PathVariable int customerId) {
        return this.customerService.retrieve(customerId);
    }

    @PostMapping
    public void createCustomer(@RequestBody Customer customer) {
        this.customerService.create(customer);
    }

    @PutMapping
    public void updateCustomer(@RequestBody Customer customer) {
        this.customerService.update(customer);
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable int customerId) {
        this.customerService.remove(customerId);
    }

}
