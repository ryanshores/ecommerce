package com.ryanshores.ecommerce.service;

import com.ryanshores.ecommerce.model.Product;
import com.ryanshores.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends BaseService<Product> {

    public ProductService(ProductRepository repo) {
        super(repo);
    }
}
