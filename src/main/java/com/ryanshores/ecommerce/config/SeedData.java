package com.ryanshores.ecommerce.config;

import com.ryanshores.ecommerce.data.entities.Account;
import com.ryanshores.ecommerce.data.repositories.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SeedData implements CommandLineRunner {

    private final AccountRepository accountRepository;
    private final Logger logger;

    @Autowired
    public SeedData(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.logger = LoggerFactory.getLogger(SeedData.class);
    }

    @Override
    public void run(String... args) throws Exception {
        seedAccounts();
    }

    private void seedAccounts() {
        var account = accountRepository.findAll();

        if (!account.isEmpty()) return;

        logger.info("Preloading accounts");

        var admin = new Account("admin@test.com");

        logger.info(accountRepository.save(admin).toString());
    }
}
