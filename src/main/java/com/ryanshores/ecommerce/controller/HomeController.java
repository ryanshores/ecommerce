package com.ryanshores.ecommerce.controller;

import com.ryanshores.ecommerce.dto.ApiDto;
import com.ryanshores.ecommerce.dto.ApiResponse;
import com.ryanshores.ecommerce.dto.ProductDto;
import com.ryanshores.ecommerce.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/")
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getHome(Model model) {
        var products = productService.getAll();
        model.addAttribute("products", products.stream().map(ProductDto::new));
        return "home";
    }

    @GetMapping(path = "/api")
    @ResponseBody
    public ApiResponse<ApiDto> getApi() {
        return ApiResponse.success(new ApiDto());
    }

    @GetMapping(path = "/ping")
    @ResponseBody
    public ApiResponse<String> ping() {
        return ApiResponse.empty();
    }

}
