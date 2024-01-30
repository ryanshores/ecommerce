package com.ryanshores.ecommerce.service;

import com.ryanshores.ecommerce.model.Cart;
import com.ryanshores.ecommerce.model.TestProduct;
import com.ryanshores.ecommerce.repository.CartRepository;
import com.ryanshores.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CartTest {

    private final CartRepository cartRepository = Mockito.mock(CartRepository.class);
    private final ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    private CartService cartService;
    private ProductService productService;

    @BeforeEach
    public void initServices() {
        initProducts();
        initCarts();
    }

    private void initProducts() {
        productService = new ProductService( productRepository );
        when( productRepository.findById( anyLong() ) ).thenAnswer( invocation -> Optional.of(
                new TestProduct( ( Long ) invocation.getArguments()[0] ) ) );
    }

    private void initCarts() {
        cartService = new CartService(cartRepository, this.productService);
        when(cartRepository.save(any(Cart.class))).thenAnswer( invocation -> {
            Cart arg = (Cart)invocation.getArguments()[0];
            if (arg.getId() == null) {
                arg.setId(1L);
            }
            return arg;
        } );
        when(cartRepository.findById(anyLong())).thenAnswer( invocation -> {
            Long id = (Long) invocation.getArguments()[0];
            Cart cart = new Cart();
            cart.setId(id);
            return Optional.of(cart);
        } );
    }

    private Cart createTestCart() { return cartService.createCart(); }

    @Test
    public void createCart_success() {
        var cart = createTestCart();
        assertNotNull(cart.getId());
    }

    @Test
    public void addProduct_success() throws Exception {

        var cartId = createTestCart().getId();

        var productId = 10L;

        var updatedCart = cartService.addProduct(cartId, productId);
        assertTrue(updatedCart.getOrderProductIds().contains(productId));
    }

    @Test
    public void addMultipleProduct_success() throws Exception {

        var cartId = createTestCart().getId();

        var productId = 10L;

        var updatedCart = cartService.addProduct(cartId, productId);
        updatedCart = cartService.addProduct(cartId, productId);

        assertTrue(updatedCart.getOrderProductIds().contains(productId));
    }

}
