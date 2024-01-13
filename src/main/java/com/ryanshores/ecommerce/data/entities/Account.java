package com.ryanshores.ecommerce.data.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Account extends Base {

    public Account(String email) {
        this.email = email;
    }

    @Pattern(regexp = "^(.+)@(\\S+)$", message = "email must be valid")
    private String email;

    private String password;

    @OneToOne
    private Cart cart;

    @Override
    public String toString() {
        return "Account{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", cart=" + cart +
                '}';
    }
}
