package com.ryanshores.ecommerce.model.order;

import com.ryanshores.ecommerce.enums.OrderStatus;
import com.ryanshores.ecommerce.model.Account;
import com.ryanshores.ecommerce.model.Base;
import com.ryanshores.ecommerce.model.exception.InvoiceException;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@NoArgsConstructor
public class Invoice extends Base {

    @OneToOne
    private Cart cart;
    @ManyToOne
    private Account account;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Invoice(Cart cart, Account account) {
        this.cart = cart;
        this.account = account;
        this.status = OrderStatus.Submitted;
    }

    public void setOrderStatus(OrderStatus status) throws InvoiceException {
        this.status = this.status.transitionTo(status);
    }
}
