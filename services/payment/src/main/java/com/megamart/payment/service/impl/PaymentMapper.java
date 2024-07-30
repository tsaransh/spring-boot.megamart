package com.megamart.payment.service.impl;

import com.megamart.payment.entity.Payment;
import com.megamart.payment.payload.PaymentRequest;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public Payment toPayment(PaymentRequest paymentRequest) {
        return new Payment().builder()
                .id(paymentRequest.id())
                .amount(paymentRequest.amount())
                .paymentMethod(paymentRequest.paymentMethod())
                .OrderId(paymentRequest.orderId())
                .build();
    }
}
