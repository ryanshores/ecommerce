package com.ryanshores.ecommerce.dto;

import com.ryanshores.ecommerce.enums.ApiStatus;

public class ErrorResponse extends ApiResponse {
    public ErrorResponse(String errMsg) {
        super(ApiStatus.success, errMsg);
    }
}
