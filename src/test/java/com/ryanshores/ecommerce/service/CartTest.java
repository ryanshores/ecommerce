package com.ryanshores.ecommerce.service;

import com.ryanshores.ecommerce.model.TestCarts;
import com.ryanshores.ecommerce.model.TestProducts;
import com.ryanshores.ecommerce.model.order.Cart;
import com.ryanshores.ecommerce.repository.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CartTest {

    private final CartRepository cartRepository = Mockito.mock(CartRepository.class);
    private CartService cartService;

    @BeforeEach
    public void initService() {
        cartService = new CartService(cartRepository);
        when(cartRepository.save(any(Cart.class))).thenAnswer( invocation -> {
            var cart = (Cart) invocation.getArguments()[0];
            cart.setId(1L);
            return cart;
        });
    }

    private void assertLineItem(Cart cart, Long productId, Long quantity) {
        var lineItem = cart.getLineItems().stream().filter(object ->
                        object.getProduct().getId().equals(productId))
                .findFirst().orElse(null);

        if (quantity == 0) assertNull(lineItem);
        assertNotNull(lineItem);
        assertEquals(productId, lineItem.getProduct().getId());
        assertEquals(quantity, lineItem.getQuantity());
    }

    @Test
    public void createCartWithProduct_success() {
        var product = TestProducts.TestProduct();
        var cart = cartService.createCart(product);
        assertTrue(cart.isPresent());
        assertLineItem(cart.get(), product.getId(), 1L);
    }

    @Test
    public void createCartWithProduct_fail() {
        var productWithNoQuantity = TestProducts.TestProduct();
        productWithNoQuantity.setQuantity(0L);
        var cart = cartService.createCart(productWithNoQuantity);
        assertTrue(cart.isEmpty());
    }

    @Test
    public void addProductToCartWithProduct_success() {
        var product = TestProducts.TestProduct();
        var cart = TestCarts.WithProduct(product, 1L);

        var updatedCart = cartService.addProduct(cart, product);

        assertLineItem(updatedCart, product.getId(), 2L);
    }

    @Test
    public void removeProductFromCartNoneLeft_success() {
        var product = TestProducts.TestProduct();
        var cart = TestCarts.WithProduct(product, 1L);
        var updatedCart = cartService.removeProduct(cart, product);

        assertTrue(updatedCart.isEmpty());
    }

    @Test
    public void removeProductFromCartOneLeft_success() {
        var product = TestProducts.TestProduct();
        var cart = TestCarts.WithProduct(product, 2L);
        var updatedCart = cartService.removeProduct(cart, product);

        assertTrue(updatedCart.isPresent());
        assertLineItem(updatedCart.get(), product.getId(), 1L);
    }

    @Test
    public void clearProductFromCart() {
        var product = TestProducts.TestProduct();
        var cart = TestCarts.WithProduct(product, 2L);
        var updatedCart = cartService.clearProduct(cart, product);

        assertTrue(updatedCart.isEmpty());
    }

    @Test
    public void clearProductFromCartWithAnotherLineItem() {
        var product = TestProducts.TestProduct(1L);
        var product2 = TestProducts.TestProduct(2L);

        var cart = TestCarts.WithProducts(Map.of(
                product, 2L,
                product2, 1L
        ));

        var updatedCart = cartService.clearProduct(cart, product);

        assertTrue(updatedCart.isPresent());
        assertLineItem(updatedCart.get(), product2.getId(), 1L);
    }

}
