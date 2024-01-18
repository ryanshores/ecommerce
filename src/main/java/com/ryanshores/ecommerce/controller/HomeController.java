package com.ryanshores.ecommerce.controller;

import com.ryanshores.ecommerce.dto.ApiResponse;
import com.ryanshores.ecommerce.dto.SuccessResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/")
public class HomeController {

    @GetMapping
    public String get() {
        return "index";
    }

    @GetMapping(path = "/api")
    @ResponseBody
    public ApiResponse<String> getApi() {
        return new SuccessResponse("Hello, world!");
    }

}
