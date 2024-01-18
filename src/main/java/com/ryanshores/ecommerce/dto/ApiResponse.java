package com.ryanshores.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private T data;

    public static <T> ApiResponse<T> empty() {
        return success(null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .data(data)
                .build();
    }

    public static <ErrorStatus> ApiResponse<ErrorStatus> error(ErrorStatus status) {
        return ApiResponse.<ErrorStatus>builder()
                .success(false)
                .data(status)
                .build();
    }
}

