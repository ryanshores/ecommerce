package com.ryanshores.ecommerce.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class LineItem {
    
    @ManyToOne
    private Product product;
    
    @Positive(message = "quantity must be positive")
    private Long quantity;

    public Double getTotal() {
        return quantity * product.getPrice();
    }

    @Override
    public String toString() {
        return "LineItem{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
