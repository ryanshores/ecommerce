package com.ryanshores.ecommerce.data.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Account extends Base {

    public Account(String email, String password, Authority authority) {
        this.email = email;
        this.password = password;
        this.authorities.add(authority);
    }

    @NotNull
    @Pattern(regexp = "^(.+)@(\\S+)$", message = "email must be valid")
    private String email;

    @NotNull
    private String password;

    @OneToOne
    private Cart cart;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "account_authority",
            joinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
    private Set<Authority> authorities = new HashSet<>();

    @Override
    public String toString() {
        return "Account{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", cart=" + cart +
                ", authorities=" + authorities +
                '}';
    }
}
