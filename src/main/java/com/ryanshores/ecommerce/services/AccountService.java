package com.ryanshores.ecommerce.services;

import com.ryanshores.ecommerce.data.entities.Account;
import com.ryanshores.ecommerce.data.repositories.AccountRepository;
import com.ryanshores.ecommerce.data.repositories.AuthorityRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder encoder;

    public AccountService(AccountRepository repository, PasswordEncoder passwordEncoder,
                          AuthorityRepository authorityRepository) {
        this.accountRepository = repository;
        this.encoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }

    public Account save(Account account) {
        account.setPassword(encoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    public Optional<Account> findByEmail(String email) {
        return accountRepository.findOneByEmail(email);
    }

    public List<Account> findByAuthority(String name) throws Exception {
        var accountAuthority = authorityRepository.findById(name);

        if (accountAuthority.isEmpty()) throw new Exception("Unable to find authority with name: " + name);

        return accountRepository.findAccountsByAuthorities(accountAuthority.get().getName());
    }
}
