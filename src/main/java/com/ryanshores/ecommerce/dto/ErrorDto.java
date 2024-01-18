package com.ryanshores.ecommerce.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ErrorDto {

    private final String url;
    private final String ex;

    public ErrorDto(@NotNull String url, @NotNull Exception ex) {
        this.url = url;
        this.ex = ex.getLocalizedMessage();
    }
}
