package com.megamart.order.payload;

public record OrderLineResponse(
        Integer id,
        double quantity
) {
}
