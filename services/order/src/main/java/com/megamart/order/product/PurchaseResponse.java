package com.megamart.order.product;

import java.math.BigDecimal;

public record PurchaseResponse(
        String productId,
        String productName,
        String description,
        BigDecimal price,
        double quantity
) {
}
