package com.ryanshores.ecommerce.services;

import com.ryanshores.ecommerce.data.entities.Account;
import com.ryanshores.ecommerce.data.repositories.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository repo;
    private final PasswordEncoder encoder;

    public AccountService(AccountRepository repository, PasswordEncoder passwordEncoder) {
        this.repo = repository;
        this.encoder = passwordEncoder;
    }

    public Account save(Account account) {
        account.setPassword(encoder.encode(account.getPassword()));
        return repo.save(account);
    }

    public Optional<Account> findByEmail(String email) {
        return repo.findOneByEmail(email);
    }

    public List<Account> findAll() { return repo.findAll(); }
}
