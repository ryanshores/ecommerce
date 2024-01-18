package com.ryanshores.ecommerce.controller;

import com.ryanshores.ecommerce.dto.ApiDto;
import com.ryanshores.ecommerce.dto.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/")
public class HomeController {

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
