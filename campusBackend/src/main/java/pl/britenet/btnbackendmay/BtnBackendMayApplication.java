package pl.britenet.btnbackendmay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.britenet.btnbackendmay.controller.ProductController;
import pl.britenet.campus.service.ProductService;
import pl.britenet.campus.service.database.DatabaseService;

@SpringBootApplication
public class BtnBackendMayApplication {

    public static void main(String[] args) {
        SpringApplication.run(BtnBackendMayApplication.class, args);
    }
}
