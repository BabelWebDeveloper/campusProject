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
//to jest tak jakby cartservice
    public Map<String, String> login(String email, String password){//customerId, productId
        Optional<Customer> opCustomer = this.customerService.retrieve(email,password);//jeśli cartService otrzyma cartId to tworzy się przy niej cartproduct
        Customer customer = opCustomer.orElseThrow();//tutaj orElseThrow dla Cart cart

        String token = UUID.randomUUID().toString();
        this.loggedInCustomers.put(token, customer);

        Map<String, String> result = new HashMap<>();
        result.put("token", token);
        result.put("id", String.valueOf(customer.getId()));

        return result;
    }
}
