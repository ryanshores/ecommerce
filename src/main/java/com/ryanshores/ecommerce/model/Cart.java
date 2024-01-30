package com.ryanshores.ecommerce.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.OrderColumn;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.antlr.v4.runtime.misc.OrderedHashSet;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
public class Cart extends Base {

    @Size(max = 32)
    private String couponCode;

    @ElementCollection
    @OrderColumn
    private List<LineItem> lineItems = new ArrayList<>();
    
    public OrderedHashSet<Long> getOrderProductIds() {
        OrderedHashSet<Long> result = new OrderedHashSet<>();
        
        lineItems.forEach((lineItem) -> {
            result.add(lineItem.getProduct().getId());
        });
        
        return result;
    }
}

