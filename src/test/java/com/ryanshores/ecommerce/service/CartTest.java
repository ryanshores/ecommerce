package com.ryanshores.ecommerce.service;

import com.ryanshores.ecommerce.model.Cart;
import com.ryanshores.ecommerce.repository.CartRepository;
import com.ryanshores.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static jdk.jfr.internal.jfc.model.Constraint.any;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.when;

public class CartTest {

    @Mock
    private CartRepository cartRepository;
    @Mock
    private ProductRepository productRepository;
    private CartService cartService;

    @BeforeEach
    public void initServices() {
        this.cartService = new CartService(cartRepository, new ProductService(productRepository));
    }

    @Test
    public void createCart_success() {
        var cart = cartService.createCart();
        when(cartRepository.save(any(Cart.class))).then(returnsFirstArg());
        assertNotNull(cart.getId());
    }

}
