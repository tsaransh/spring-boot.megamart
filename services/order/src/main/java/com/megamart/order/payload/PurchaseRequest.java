package com.megamart.order.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull(message = "product is is required")
        Integer productId,
        @Positive(message = "Product purchase quantity is not less then 0")
        double quantity
) {
}
