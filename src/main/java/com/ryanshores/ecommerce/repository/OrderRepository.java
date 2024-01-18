package com.ryanshores.ecommerce.repository;

import com.ryanshores.ecommerce.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Sale, Long> {
}
