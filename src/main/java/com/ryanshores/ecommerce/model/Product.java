package com.ryanshores.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product extends Base {

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

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", sku='" + sku + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
