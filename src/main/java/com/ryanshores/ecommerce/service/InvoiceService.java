package com.ryanshores.ecommerce.service;

import com.ryanshores.ecommerce.enums.OrderStatus;
import com.ryanshores.ecommerce.model.Account;
import com.ryanshores.ecommerce.model.LineItem;
import com.ryanshores.ecommerce.model.exception.InvoiceException;
import com.ryanshores.ecommerce.model.order.Cart;
import com.ryanshores.ecommerce.model.order.Invoice;
import com.ryanshores.ecommerce.repository.InvoiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final Logger logger;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
        this.logger = LoggerFactory.getLogger(InvoiceService.class);
    }

    public Optional<Invoice> create(Account account, Cart cart) {
        // check all product quantities vs line item quantity
        var inStock = cart.getLineItems().stream().allMatch(this::validateInStock);

        if (!inStock) return Optional.empty();

        return Optional.of(invoiceRepository.save(new Invoice(cart, account)));
    }

    private boolean validateInStock(LineItem lineItem) {
        var product = lineItem.getProduct();
        var inStock = product.getQuantity() >= lineItem.getQuantity();
        if (!inStock) logger.info("OUT OF STOCK | `" + product.getId() + "` " + product.getName());
        return inStock;
    }

    public Invoice setStatus(Invoice invoice, OrderStatus status) throws InvoiceException {
        invoice.setOrderStatus(status);
        return invoiceRepository.save(invoice);
    }
}
