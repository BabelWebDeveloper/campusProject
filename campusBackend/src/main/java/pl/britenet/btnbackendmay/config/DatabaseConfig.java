package pl.britenet.btnbackendmay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.britenet.campus.service.database.DatabaseService;

@Configuration
public class DatabaseConfig {

    @Bean
    public DatabaseService getDatabaseService() {
        return new DatabaseService();
    }

}
