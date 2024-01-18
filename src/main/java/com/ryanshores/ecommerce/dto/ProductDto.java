package com.ryanshores.ecommerce.dto;

import com.ryanshores.ecommerce.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDto {
    private final String name;
    private final String description;
    private final String sku;
    private final Double price;
    private final Long quantity;

    public ProductDto(Product product) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.sku = product.getSku();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
    }
}
