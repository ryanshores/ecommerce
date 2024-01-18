package com.ryanshores.ecommerce.dto;

import com.ryanshores.ecommerce.model.Account;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class AccountDto {

    private final String email;

    public AccountDto(@NotNull Account account) {
        this.email = account.getEmail();
    }
}
