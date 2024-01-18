package com.ryanshores.ecommerce.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OrderColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Sale extends Base {

    @ElementCollection
    @OrderColumn
    private List<LineItem> lineItems;

    private SaleStatus status;

    @Range(min = 0, max = 100)
    private Integer discount;

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
                ", subtotal=" + subtotal() +
                ", total=" + total() +
                '}';
    }
}

