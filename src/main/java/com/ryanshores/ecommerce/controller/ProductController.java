package com.ryanshores.ecommerce.controller;

import com.ryanshores.ecommerce.dto.ApiResponse;
import com.ryanshores.ecommerce.dto.ProductDto;
import com.ryanshores.ecommerce.model.Product;
import com.ryanshores.ecommerce.model.exception.NotFoundException;
import com.ryanshores.ecommerce.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<Stream<ProductDto>> getAll() {
        var products = service.getAll();
        return ApiResponse.success(products.stream().map(ProductDto::new));
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductDto> getById(@PathVariable Long id) throws NotFoundException {
        var optionalProduct = service.findById(id);

        if (optionalProduct.isEmpty()) throw new NotFoundException("product not found with id " + id);

        return ApiResponse.success(new ProductDto(optionalProduct.get()));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<ProductDto> post(@RequestBody ProductDto productDto) {
        var product = service.save(new Product(productDto));

        return ApiResponse.success(new ProductDto(product));
    }
}
