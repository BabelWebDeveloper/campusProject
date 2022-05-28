package pl.britenet.btnbackendmay.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.britenet.campus.service.*;
import pl.britenet.campus.service.database.DatabaseService;

@Configuration
public class APIConfig {

    private final DatabaseService databaseService;

    @Autowired
    public APIConfig(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @Bean
    public ProductService getProductService() {
        return new ProductService(this.databaseService);
    }
    @Bean
    public PaymentService getPaymentService(){
        return new PaymentService(this.databaseService);
    }
    @Bean
    public CategoryService getCategoryService() {
        return new CategoryService(this.databaseService);
    }
    @Bean
    public CartService getCartService() {
        return new CartService(this.databaseService);
    }
    @Bean
    public CartProductService getCartProductService() {
        return new CartProductService(this.databaseService);
    }
    @Bean
    public CustomerService getCustomerService() {
        return new CustomerService(this.databaseService);
    }
    @Bean
    public DiscountService getDiscountService() {
        return new DiscountService(this.databaseService);
    }

}
