package com.ryanshores.ecommerce.model;

import com.ryanshores.ecommerce.dto.ProductDto;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product extends Base {

    public Product(ProductDto productDto) {
        name = productDto.getName();
        description = productDto.getDescription();
        sku = productDto.getSku();
        price = productDto.getPrice();
        quantity = productDto.getQuantity();
    }

    @NotNull(message = "name is required")
    @Pattern(regexp="^[ A-Za-z0-9_@./#&+-]+$", message = "name must be a string")
    private String name;

    @NotNull(message = "description is required")
    @Pattern(regexp="^[ A-Za-z0-9_@./#&+-]+$", message = "description must be a string")
    private String description;

    @NotNull(message = "sku is required")
    @Pattern(regexp="^[A-Z0-9]+$", message = "sku must be uppercase letters and numbers")
    private String sku;

    @Positive(message = "price must be positive")
    private Double price;

    @Positive(message = "quantity must be positive")
    private Long quantity;
}
