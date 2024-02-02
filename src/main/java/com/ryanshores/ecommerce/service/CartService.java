package com.ryanshores.ecommerce.service;

import com.ryanshores.ecommerce.model.Cart;
import com.ryanshores.ecommerce.model.Product;
import com.ryanshores.ecommerce.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService extends BaseService<Cart> {

    private final CartRepository cartRepo;

    public CartService(CartRepository cartRepository) {
        super(cartRepository);
        this.cartRepo = cartRepository;
    }

    public Optional<Cart> createCart(Product product) {
        var cart = addProduct(new Cart(), product);
        return cart.getId() != null ? Optional.of(cart) : Optional.empty();
    }

    public Cart addProduct(Cart cart, Product product) {
        if (product.getQuantity() == 0) return cart; // product has no remaining quantity
        cart.addProductByQuantity(product, 1L);
        return cartRepo.save(cart);
    }

    public Optional<Cart> removeProduct(Cart cart, Product product) {
        cart.removeProductByQuantity(product, 1L);
        return checkCartLineItemsBeforeSave(cart);
    }

    public Optional<Cart> clearProduct(Cart cart, Product product) {
        cart.clearProduct(product);
        return checkCartLineItemsBeforeSave(cart);
    }

    /**
     * Checks if line items is empty. Deletes cart if empty or saves cart.
     *
     * @param cart with line items to be checked
     * @return optional cart null if no line items remain
     */
    private Optional<Cart> checkCartLineItemsBeforeSave(Cart cart) {
        if (cart.getLineItems().isEmpty()) {
            cartRepo.delete(cart);
            return Optional.empty();
        } // delete cart if line items is empty
        return Optional.of(cartRepo.save(cart));
    }
}
