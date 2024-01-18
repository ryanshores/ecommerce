package com.ryanshores.ecommerce.service;

import com.ryanshores.ecommerce.config.Constants;
import com.ryanshores.ecommerce.model.Account;
import com.ryanshores.ecommerce.model.exception.AlreadyRegisteredException;
import com.ryanshores.ecommerce.model.exception.NotFoundException;
import com.ryanshores.ecommerce.repository.AccountRepository;
import com.ryanshores.ecommerce.repository.AuthorityRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Account> findByAuthority(String name) throws NotFoundException {
        var accountAuthority = authorityRepository.findById(name);

        if (accountAuthority.isEmpty()) throw new NotFoundException("Unable to find authority with name: " + name);

        return accountRepository.findAccountsByAuthorities(accountAuthority.get().getName());
    }

    public Account createNewUser(String email, String password) throws NotFoundException, AlreadyRegisteredException {
        var existingUser = accountRepository.findOneByEmail(email);

        if (existingUser.isPresent()) throw new AlreadyRegisteredException("email is already registered");

        var userRole = authorityRepository.findById(Constants.UserRole);

        if (userRole.isEmpty()) throw new NotFoundException("user role not found");

        var account = new Account(email, password, userRole.get());

        return save(account);
    }
}
