package com.ryanshores.ecommerce.repository;

import com.ryanshores.ecommerce.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
