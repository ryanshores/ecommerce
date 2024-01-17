package com.ryanshores.ecommerce.data.repositories;

import com.ryanshores.ecommerce.data.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findOneByEmail(String email);

    @Query("SELECT distinct ac from Account ac join ac.authorities au where au.name = ?1")
    List<Account> findAccountsByAuthorities(String name);
}
