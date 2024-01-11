package com.ryanshores.ecommerce.data.repositories;

import com.ryanshores.ecommerce.data.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Sale, Long> {
}
