package pl.britenet.btnbackendmay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus.obj.model.Customer;
import pl.britenet.campus.service.CustomerService;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/{customerId}")
    public Optional<Customer> getCustomer(@PathVariable int customerId) {
        return this.customerService.retrieve(customerId);
    }

    @PostMapping
    public void createCustomer(@RequestBody Customer customer) {
        this.customerService.create(customer);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/createCustomer")
    public Map<String, String> createCustomer(@RequestBody Map<String, String> json) {
        String first_name = json.get("first_name");
        String last_name = json.get("last_name");
        String email = json.get("email");
        String address = json.get("address");
        String password = json.get("password");
        return this.customerService.create(first_name,last_name,email,address,password);
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
