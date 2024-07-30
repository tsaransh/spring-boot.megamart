package com.megamart.payment.service;

import com.megamart.payment.entity.Payment;
import com.megamart.payment.payload.PaymentRequest;

public interface PaymentService {

    Integer createPayment (PaymentRequest paymentRequest);

}
