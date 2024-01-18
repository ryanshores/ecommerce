package com.ryanshores.ecommerce.controller;

import com.ryanshores.ecommerce.dto.AccountDto;
import com.ryanshores.ecommerce.dto.CreateAccountDto;
import com.ryanshores.ecommerce.model.exception.NotFoundException;
import com.ryanshores.ecommerce.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

    private final AccountService accountService;

    @Autowired
    public RegisterController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public CreateAccountDto get() {
        return new CreateAccountDto("your@email.com", "password");
    }

    @PostMapping
    public AccountDto post(@RequestBody CreateAccountDto createAccountDto) throws NotFoundException {
        return new AccountDto(accountService.createNewUser(createAccountDto.email(), createAccountDto.password()));
    }
}
