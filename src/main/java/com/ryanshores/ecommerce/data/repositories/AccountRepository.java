package com.ryanshores.ecommerce.data.repositories;

import com.ryanshores.ecommerce.data.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
