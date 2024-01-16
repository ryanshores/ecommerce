package com.ryanshores.ecommerce.data.repositories;

import com.ryanshores.ecommerce.data.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
