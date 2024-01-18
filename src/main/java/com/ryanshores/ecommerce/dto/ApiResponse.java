package com.ryanshores.ecommerce.dto;

import com.ryanshores.ecommerce.enums.ApiStatus;
import lombok.Data;

@Data
public abstract class ApiResponse<T> {
    private final ApiStatus status;
    private final T object;

    public ApiResponse(ApiStatus status, T object) {
        this.status = status;
        this.object = object;
    }
}

