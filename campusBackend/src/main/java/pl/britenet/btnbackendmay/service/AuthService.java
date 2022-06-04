package pl.britenet.btnbackendmay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.britenet.campus.obj.model.Customer;
import pl.britenet.campus.service.CustomerService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {
    private final Map<String, Customer> loggedInCustomers;
    private final CustomerService customerService;

    @Autowired
    public AuthService(CustomerService customerService) {
        this.loggedInCustomers = new HashMap<>();
        this.customerService = customerService;
    }

    public Map<String, String> login(String email, String password){
        Optional<Customer> opCustomer = this.customerService.retrieve(email,password);
        Customer customer = opCustomer.orElseThrow();

        String token = UUID.randomUUID().toString();
        this.loggedInCustomers.put(token, customer);

        Map<String, String> result = new HashMap<>();
        result.put("token", token);
        result.put("id", String.valueOf(customer.getId()));
        result.put("first_name", customer.getFirst_name());
        result.put("last_name", customer.getLast_name());
        result.put("address", customer.getAddress());

        return result;
    }
}
