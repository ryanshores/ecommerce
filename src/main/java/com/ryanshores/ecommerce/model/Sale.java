package com.ryanshores.ecommerce.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OrderColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
public class Sale extends Base {

    @ElementCollection
    @OrderColumn
    private List<LineItem> lineItems = new ArrayList<>();

    private SaleStatus status;
    public Double subtotal = getSubtotal();

    @ManyToOne
    private Account account;
    @Range(min = 0, max = 100)
    private Integer discount = 0;
    public Double total = getTotal();

    private Double getSubtotal() {
        var result = lineItems.stream().mapToDouble(LineItem::getTotal).reduce(Double::sum);
        return result.isPresent() ? result.getAsDouble() : Double.NaN;
    }

    private Double getTotal() {
        return discount > 0 && discount <= 100 ?
                (1 - (discount / 100)) * subtotal :
                subtotal;
    }
}

