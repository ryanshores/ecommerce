package com.ryanshores.ecommerce.data.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Cart extends Base {

    @Nullable
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

