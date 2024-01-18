package com.ryanshores.ecommerce.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.OrderColumn;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Cart extends Base {

    @Size(max = 32)
    private String couponCode;

    @ElementCollection
    @OrderColumn
    private List<LineItem> lineItems;

    @Override
    public String toString() {
        return "Cart{" +
                "couponCode='" + couponCode + '\'' +
                ", lineItems=" + lineItems +
                '}';
    }
}

