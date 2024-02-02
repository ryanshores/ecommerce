package com.ryanshores.ecommerce.service;

import com.ryanshores.ecommerce.enums.OrderStatus;
import com.ryanshores.ecommerce.model.Account;
import com.ryanshores.ecommerce.model.TestCarts;
import com.ryanshores.ecommerce.model.TestProducts;
import com.ryanshores.ecommerce.model.exception.InvoiceException;
import com.ryanshores.ecommerce.model.order.Invoice;
import com.ryanshores.ecommerce.repository.InvoiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class InvoiceTest {

    private final InvoiceRepository invoiceRepository = Mockito.mock(InvoiceRepository.class);
    private InvoiceService invoiceService;

    @BeforeEach
    public void initService() {
        invoiceService = new InvoiceService(invoiceRepository);
        when(invoiceRepository.save(any(Invoice.class))).thenAnswer(invocation -> {
            var invoice = (Invoice) invocation.getArguments()[0];
            invoice.setId(1L);
            return invoice;
        });
    }

    @Test
    public void createInvoice_success() {
        var cart = TestCarts.WithProduct(TestProducts.TestProduct(), 10L);
        var account = new Account();

        var invoice = invoiceService.create(account, cart);
        assertNotNull(invoice);
    }

    @Test
    void createInvoice_failure() {
        var cart = TestCarts.WithProduct(TestProducts.TestProduct(), 11L);
        var account = new Account();

        var invoice = invoiceService.create(account, cart);
        assertTrue(invoice.isEmpty());
    }

    @Test
    public void invoiceTransitions() {
        var invoice = new Invoice(
                TestCarts.WithProduct(TestProducts.TestProduct(), 1L),
                new Account());

        assertThrows(InvoiceException.class, () -> invoiceService.setStatus(invoice, OrderStatus.Shipped));
        assertThrows(InvoiceException.class, () -> invoiceService.setStatus(invoice, OrderStatus.Fulfilled));

        assertDoesNotThrow(() -> invoiceService.setStatus(invoice, OrderStatus.Paid));

        assertThrows(InvoiceException.class, () -> invoiceService.setStatus(invoice, OrderStatus.Fulfilled));

        assertDoesNotThrow(() -> invoiceService.setStatus(invoice, OrderStatus.Shipped));

        assertDoesNotThrow(() -> invoiceService.setStatus(invoice, OrderStatus.Fulfilled));

        assertThrows(InvoiceException.class, () -> invoiceService.setStatus(invoice, OrderStatus.Error));
    }
}
