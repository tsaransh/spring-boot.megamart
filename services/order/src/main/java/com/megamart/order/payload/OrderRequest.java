package com.megamart.order.payload;

import com.megamart.order.entity.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,

        @Positive(message="order amount should not be less then 0")
        BigDecimal amount,
        @NotNull(message="payment should be precised")
        PaymentMethod paymentMethod,
        @NotNull(message="customer should be required")
        @NotEmpty(message="customer should be required")
        @NotBlank(message="customer should be required")
        String customerId,

        @NotEmpty(message = "you should purchase at least 1 product")
        List<PurchaseRequest> products
) {
}
