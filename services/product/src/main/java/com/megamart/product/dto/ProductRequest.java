package com.megamart.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        Integer id,
        @NotNull(message= "Product name can't be empty")
        String name,
        @NotNull(message= "Product description can't be empty")
        String description,
        @Positive(message= "Product quantity can't be less then 0")
        double availableQuantity,
        @Positive(message= "Product price can't be less then 0")
        BigDecimal price,
        @NotNull(message= "Product category is required")
        Integer categoryId
) {
}
