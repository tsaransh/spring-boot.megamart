package com.megamart.order.payload;

import com.megamart.order.customer.CustomerResponse;
import com.megamart.order.entity.PaymentMethod;
import com.megamart.order.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
