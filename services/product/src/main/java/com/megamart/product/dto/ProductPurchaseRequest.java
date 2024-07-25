package com.megamart.product.dto;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull
        Integer productId,
        @NotNull
        double quantity
) {
}
