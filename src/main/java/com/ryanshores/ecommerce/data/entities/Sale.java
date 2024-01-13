package com.ryanshores.ecommerce.data.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Sale extends Base {

    public Sale(List<LineItem> lineItems) {
        this.status = SaleStatus.Submitted;
        this.lineItems = lineItems;
    }

    @ElementCollection
    @OrderColumn
    private List<LineItem> lineItems;

    private SaleStatus status;

    private Integer discount;

    @Nullable
    @ManyToOne
    private Account account;

    public Double subtotal() {
        var result = lineItems.stream().mapToDouble(LineItem::getTotal).reduce(Double::sum);
        return result.isPresent() ? result.getAsDouble() : Double.NaN;
    }

    public Double total() {
        return discount > 0 && discount <= 100 ?
                (1 - (discount / 100)) * subtotal() :
                subtotal();
    }


    @Override
    public String toString() {
        return "Sale{" +
                "lineItems=" + lineItems +
                ", status=" + status +
                ", discount=" + discount +
                ", account=" + account +
                '}';
    }
}

