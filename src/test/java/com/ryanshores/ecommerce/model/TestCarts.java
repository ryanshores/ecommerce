package com.ryanshores.ecommerce.model;

import com.ryanshores.ecommerce.model.order.Cart;

import java.util.Map;

public class TestCarts {
    public static Cart WithProduct(Product product, Long quantity) {
        var cart = new Cart();
        cart.addProductByQuantity(product, quantity);
        return cart;
    }

    public static Cart WithProducts(Map<Product, Long> productQuantityMap) {
        var cart = new Cart();
        productQuantityMap.forEach(cart::addProductByQuantity);
        return cart;
    }
}
