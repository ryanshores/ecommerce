package com.ryanshores.ecommerce.data.repositories;

import com.ryanshores.ecommerce.data.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
