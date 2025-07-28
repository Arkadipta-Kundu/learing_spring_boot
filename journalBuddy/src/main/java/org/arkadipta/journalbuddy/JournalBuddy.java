package org.arkadipta.journalbuddy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JournalBuddy {
    static {
        new EnvLoader(); // Load .env before Spring starts
    }

    public static void main(String[] args) {

        // Set SSL properties
//        System.setProperty("javax.net.ssl.trustStore", "/path/to/truststore.jks");
//        System.setProperty("javax.net.ssl.trustStorePassword", "your-truststore-password");
//
        SpringApplication.run(JournalBuddy.class, args);
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(MongoDatabaseFactory mongoDatabaseFactory){
        return new MongoTransactionManager(mongoDatabaseFactory);
    }
}