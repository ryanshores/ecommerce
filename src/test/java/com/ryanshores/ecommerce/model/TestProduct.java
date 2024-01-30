package com.ryanshores.ecommerce.model;

public class TestProduct extends Product {
    public TestProduct() {
        this(1L);
    }

    public TestProduct(Long id) {
        super(
                "Test",
                "Description",
                "SKU000001",
                1.99,
                10L
        );
        this.setId(id);
    }
}
