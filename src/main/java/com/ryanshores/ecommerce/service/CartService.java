package com.ryanshores.ecommerce.service;

import com.ryanshores.ecommerce.model.Cart;
import com.ryanshores.ecommerce.model.LineItem;
import com.ryanshores.ecommerce.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CartService extends BaseService<Cart> {

    private final CartRepository cartRepo;
    private final ProductService productService;

    public CartService(CartRepository cartRepository, ProductService productService) {
        super(cartRepository);
        this.cartRepo = cartRepository;
        this.productService = productService;
    }

    public Cart createCart() {
        var cart = new Cart();
        return cartRepo.save(cart);
    }

    public Cart addProduct(Long cartId, Long productId) throws Exception {
        var cart = findById(cartId);
        var product = productService.findById(productId);

        if (product.getQuantity() == 0) throw new Exception("unable to add product to cart");

        var lineItems = cart.getLineItems();

        var filter = lineItems.stream().filter(i -> Objects.equals(i.getProduct().getId(), productId)).findFirst().orElse(null);

        // if the existing line item is found add +1 to quantity
        if (filter != null) {
            var index = lineItems.indexOf(filter);
            if (index < 0) throw new Exception("unable to find filtered line item index");
            var lineItem = lineItems.get(index);
            lineItem.setQuantity(lineItem.getQuantity() + 1L);
            lineItems.set(index, lineItem);
        } else { // add new line item
            lineItems.add(new LineItem(product, 1L));
        }

        cart.setLineItems(lineItems);

        return cartRepo.save(cart);
    }
}
