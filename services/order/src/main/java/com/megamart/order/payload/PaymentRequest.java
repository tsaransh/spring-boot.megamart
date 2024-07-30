package com.megamart.order.payload;

import com.megamart.order.customer.CustomerResponse;
import com.megamart.order.entity.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
