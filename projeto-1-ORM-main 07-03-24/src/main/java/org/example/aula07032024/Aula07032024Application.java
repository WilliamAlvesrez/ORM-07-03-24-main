package org.example.aula07032024;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Aula07032024Application {

    private static final Logger log = LoggerFactory.getLogger(Aula07032024Application.class);

    // Define constantes para as mensagens de log
    private static final String CUSTOMERS_FOUND_MESSAGE = "Customers found with findAll():";
    private static final String CUSTOMER_FOUND_BY_ID_MESSAGE = "Customer found with findById(1L):";
    private static final String CUSTOMERS_FOUND_BY_LAST_NAME_MESSAGE = "Customer found with findByLastName('Bauer'):";
    private static final String SEPARATOR = "--------------------------------";
    private static final String EMPTY_LINE = "";

    public static void main(String[] args) {
        SpringApplication.run(Aula07032024Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return (args) -> {
            // save a few customers
            repository.save(new Customer("Jack", "Bauer"));
            repository.save(new Customer("Chloe", "O'Brian"));
            repository.save(new Customer("Kim", "Bauer"));
            repository.save(new Customer("David", "Palmer"));
            repository.save(new Customer("Michelle", "Dessler"));

            // fetch all customers
            log.info(CUSTOMERS_FOUND_MESSAGE);
            log.info(SEPARATOR);
            repository.findAll().forEach(customer -> {
                log.info(customer.toString());
            });
            log.info(EMPTY_LINE);

            // fetch an individual customer by ID
            Customer customer = repository.findById(1L);
            log.info(CUSTOMER_FOUND_BY_ID_MESSAGE);
            log.info(SEPARATOR);
            log.info(customer.toString());
            log.info(EMPTY_LINE);

            // fetch customers by last name
            log.info(CUSTOMERS_FOUND_BY_LAST_NAME_MESSAGE);
            log.info(SEPARATOR);
            repository.findByLastName("Bauer").forEach(bauer -> {
                log.info(bauer.toString());
            });
            log.info(EMPTY_LINE);
        };
    }
}
