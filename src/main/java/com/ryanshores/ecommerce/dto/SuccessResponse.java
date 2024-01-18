package com.ryanshores.ecommerce.dto;

import com.ryanshores.ecommerce.enums.ApiStatus;

public class SuccessResponse extends ApiResponse {
    public SuccessResponse(Object object) {
        super(ApiStatus.success, object);
    }
}
