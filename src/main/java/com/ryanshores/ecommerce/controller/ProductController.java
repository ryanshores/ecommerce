package com.ryanshores.ecommerce.controller;

import com.ryanshores.ecommerce.dto.ApiResponse;
import com.ryanshores.ecommerce.dto.ProductDto;
import com.ryanshores.ecommerce.model.Product;
import com.ryanshores.ecommerce.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping("/api/product")
public class ProductController extends BaseRestController {

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
    public ApiResponse<ProductDto> getById(@PathVariable Long id) throws Exception {
        return ApiResponse.success(new ProductDto(service.findById(id)));
    }

    @PostMapping
    public ApiResponse<ProductDto> post(@RequestBody ProductDto productDto) {
        var product = service.save(new Product(productDto));

        return ApiResponse.success(new ProductDto(product));
    }
}
