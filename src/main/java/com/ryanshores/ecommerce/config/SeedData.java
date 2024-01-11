package com.ryanshores.ecommerce.config;

import com.ryanshores.ecommerce.data.entities.Account;
import com.ryanshores.ecommerce.data.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SeedData implements CommandLineRunner {

    private final AccountRepository accountRepository;

    @Autowired
    public SeedData(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedAccounts();
    }

    private void seedAccounts() {
        var account = accountRepository.findAll();

        if (!account.isEmpty()) return;

        var admin = new Account("admin@email.com");

        accountRepository.save(admin);
    }
}
