package com.ryanshores.ecommerce.enums;

import com.ryanshores.ecommerce.model.exception.InvoiceException;

import java.util.Collections;
import java.util.Set;

public enum OrderStatus {
    Error(Collections.emptySet()),
    Fulfilled(Collections.emptySet()),
    Shipped(Set.of(Fulfilled, Error)),
    Paid(Set.of(Shipped, Error)),
    Submitted(Set.of(Paid, Error));

    private final Set<OrderStatus> validTransitions;

    OrderStatus(final Set<OrderStatus> validTransitions) {
        this.validTransitions = validTransitions;
    }

    public OrderStatus transitionTo(final OrderStatus next) throws InvoiceException {
        if (!validTransitions.contains(next))
            throw new InvoiceException(this, next);
        return next;
    }
}
