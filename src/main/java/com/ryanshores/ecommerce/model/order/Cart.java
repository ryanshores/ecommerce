package com.ryanshores.ecommerce.model.order;

import com.ryanshores.ecommerce.model.Base;
import com.ryanshores.ecommerce.model.LineItem;
import com.ryanshores.ecommerce.model.Product;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.OrderColumn;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Getter
public class Cart extends Base {

    @ElementCollection
    @OrderColumn
    private List<LineItem> lineItems = new ArrayList<>();

    public Double subtotal = getSubtotal();
    @Range(min = 0, max = 100)
    private int discount = 0;
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

    private int getLineItemIndexForProductId(Long productId) {
        var filter = lineItems.stream().filter(i ->
                productId.equals(i.getProduct().getId())
        ).findFirst().orElse(null);
        return filter != null ? lineItems.indexOf(filter) : -1;
    }

    public void addProductByQuantity(Product product, Long quantity) {
        var index = getLineItemIndexForProductId(product.getId());

        if (index >= 0) { // add quantity to existing line item
            var lineItem = lineItems.get(index);
            lineItem.setQuantity(lineItem.getQuantity() + quantity);
            lineItems.set(index, lineItem);
        } else { // add new line item with quantity
            lineItems.add(new LineItem(product, quantity));
        }
    }

    public void removeProductByQuantity(Product product, Long quantity) {
        var index = getLineItemIndexForProductId(product.getId());

        if (index < 0) return; // nothing to remove

        var lineItem = lineItems.get(index);
        lineItem.setQuantity(lineItem.getQuantity() - quantity);
        if (lineItem.getQuantity() <= 0) { // remove line item if none remain
            lineItems.remove(index);
        } else { // set line item at index
            lineItems.set(index, lineItem);
        }
    }

    public void clearProduct(Product product) {
        var index = getLineItemIndexForProductId(product.getId());

        if (index < 0) return; // nothing to remove

        lineItems.remove(index);
    }
}

