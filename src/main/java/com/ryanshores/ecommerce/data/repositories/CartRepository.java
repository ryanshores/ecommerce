package com.ryanshores.ecommerce.data.repositories;

import com.ryanshores.ecommerce.data.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
