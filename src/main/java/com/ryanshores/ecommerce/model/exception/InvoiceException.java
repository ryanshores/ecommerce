package com.ryanshores.ecommerce.model.exception;

import com.ryanshores.ecommerce.enums.OrderStatus;

public class InvoiceException extends Exception {
    public InvoiceException(OrderStatus original, OrderStatus requested) {
        super("Unable to convert invoice from status: `" + original + "` to requested status: `" + requested + "`");
    }
}
