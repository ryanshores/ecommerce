package com.ryanshores.ecommerce.model;

public class TestProducts {
    public static Product TestProduct() {
        return TestProduct(1L);
    }

    public static Product TestProduct(Long id) {
        var product = new Product(
                "Test",
                "Description",
                "SKU000001",
                1.99,
                10L
        );
        product.setId(id);
        return product;
    }
}
