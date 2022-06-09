package pl.britenet.btnbackendmay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.btnbackendmay.service.AuthService;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping
    public Map<String, String> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");//pobiera z JS //customerId
        String password = credentials.get("passw");//pobiera z JS //productId
        return this.authService.login(email, password);
    }

}
