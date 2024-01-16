package com.ryanshores.ecommerce.config;

import com.ryanshores.ecommerce.data.entities.Account;
import com.ryanshores.ecommerce.data.entities.Authority;
import com.ryanshores.ecommerce.data.repositories.AuthorityRepository;
import com.ryanshores.ecommerce.services.AccountService;
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

    @Autowired
    public SeedData(AccountService accountService, AuthorityRepository authorityRepository) {
        this.accountService = accountService;
        this.authorityRepository = authorityRepository;
        this.logger = LoggerFactory.getLogger(SeedData.class);
    }

    private final String adminRole = "ROLE_ADMIN";
    private final String userRole = "ROLE_USER";


    @Override
    public void run(String... args) {
        seedAuthorities();
        seedAccounts();
    }

    private void seedAuthorities() {
        var authorities = authorityRepository.findAll();

        if (!authorities.isEmpty()) return;

        logger.info("Preloading authorities");

        var adminRole = new Authority(this.adminRole);
        logger.info(authorityRepository.save(adminRole).toString());

        var userRole = new Authority(this.userRole);
        logger.info(authorityRepository.save(userRole).toString());
    }

    private void seedAccounts() {
        var accounts = accountService.findAll();

        if (!accounts.isEmpty()) return;

        logger.info("Preloading accounts");

        var adminAuthority = authorityRepository.findById(this.adminRole);
        if (adminAuthority.isEmpty()) {
            logger.error("Unable to find admin role");
            return;
        }

        var admin = new Account("admin@email.com", "password", adminAuthority.get());
        logger.info(accountService.save(admin).toString());

        var userAuthority = authorityRepository.findById(this.userRole);
        if (userAuthority.isEmpty()) {
            logger.error("Unable to find user role");
            return;
        }

        var user = new Account("user@email.com", "password", userAuthority.get());
        logger.info(accountService.save(user).toString());
    }
}
