package com.ryanshores.ecommerce.model;

public class TestCarts {
    public static Cart WithLineItem(Long productId) {
        var cart = new Cart();
        var lineItems = cart.getLineItems();
        lineItems.add(new LineItem(new TestProduct(productId), 1L));
        cart.setLineItems(lineItems);
        return cart;
    }
}
