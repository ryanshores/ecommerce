package com.ryanshores.ecommerce.service;

import com.ryanshores.ecommerce.model.Cart;
import com.ryanshores.ecommerce.model.LineItem;
import com.ryanshores.ecommerce.repository.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService extends BaseService<Cart> {

    private final CartRepository cartRepo;
    private final ProductService productService;

    public CartService(CartRepository cartRepository, ProductService productService) {
        super(cartRepository, Cart.class);
        this.cartRepo = cartRepository;
        this.productService = productService;
    }

    public Cart createCart() {
        var cart = new Cart();
        cartRepo.save(cart);
        return cart;
    }

    public Cart addProduct(Long cartId, Long productId) throws Exception {
        var cart = findById(cartId);
        var product = productService.findById(productId);

        if (product.getQuantity() == 0) throw new Exception("unable to add product to cart");

        var lineItems = cart.getLineItems();

        if (lineItems.stream().anyMatch(lineItem -> lineItem.getProduct() == product)) {
            for (int i = 0; i < lineItems.size(); i++) {
                if (lineItems.get(i).getProduct() == product) {
                    var newLineItem = lineItems.get(i);
                    newLineItem.setQuantity(newLineItem.getQuantity() + 1);
                    lineItems.set(i, newLineItem);
                }
            }
        } else {
            lineItems.add(new LineItem(product, 1L));
        }

        cart.setLineItems(lineItems);

        return cartRepo.save(cart);
    }
}
