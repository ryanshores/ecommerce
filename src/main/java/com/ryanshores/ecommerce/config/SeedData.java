package com.ryanshores.ecommerce.config;

import com.ryanshores.ecommerce.model.Account;
import com.ryanshores.ecommerce.model.Authority;
import com.ryanshores.ecommerce.model.Product;
import com.ryanshores.ecommerce.repository.AuthorityRepository;
import com.ryanshores.ecommerce.repository.ProductRepository;
import com.ryanshores.ecommerce.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SeedData implements CommandLineRunner {

    private final AccountService accountService;
    private final AuthorityRepository authorityRepository;
    private final Logger logger;
    private final ProductRepository productRepository;

    @Autowired
    public SeedData(AccountService accountService, AuthorityRepository authorityRepository, ProductRepository productRepository) {
        this.accountService = accountService;
        this.authorityRepository = authorityRepository;
        this.logger = LoggerFactory.getLogger(SeedData.class);
        this.productRepository = productRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        seedAuthorities();
        seedAccounts();
        seedTestProducts();
    }

    private void seedAuthorities() {
        var authorities = authorityRepository.findAll();

        if (!authorities.isEmpty()) return;

        logger.info("Seeding authorities");

        var adminRole = new Authority(Constants.AdminRole);
        logger.info(authorityRepository.save(adminRole).toString());

        var userRole = new Authority(Constants.UserRole);
        logger.info(authorityRepository.save(userRole).toString());
    }

    private void seedAccounts() throws Exception {
        var accounts = accountService.findByAuthority(Constants.AdminRole);

        if (!accounts.isEmpty()) return;

        logger.info("Seeding admin account");

        var adminAuthority = authorityRepository.findById(Constants.AdminRole);
        if (adminAuthority.isEmpty()) {
            logger.error("Unable to find admin role");
            return;
        }

        var admin = new Account("admin@email.com", "password", adminAuthority.get());
        logger.info(accountService.save(admin).toString());
    }

    private void seedTestProducts() {
        var products = productRepository.findAll();

        if (!products.isEmpty()) return;

        logger.info("Seeding test products");

        var product1 = new Product("Product 1", "This product is 1 because of the way it is.", "ECP000001", 9.99, 100L);
        logger.info("Seeding product: ${}", productRepository.save(product1));

        var product2 = new Product("Product 2", "This product is 2 because of the way it is.", "ECP000002", 19.99, 50L);
        logger.info(productRepository.save(product2).toString());
    }
}
