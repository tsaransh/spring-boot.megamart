package com.megamart.payment.service.impl;

import com.megamart.payment.entity.Payment;
import com.megamart.payment.notification.NotificationProducer;
import com.megamart.payment.payload.PaymentNotificationRequest;
import com.megamart.payment.payload.PaymentRequest;
import com.megamart.payment.repository.PaymentRepository;
import com.megamart.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final NotificationProducer notificationProducer;

    @Override
    public Integer createPayment(PaymentRequest paymentRequest) {
        var payment = paymentRepository.save(paymentMapper.toPayment(paymentRequest));

        notificationProducer.sendNotification( new PaymentNotificationRequest(
                paymentRequest.orderReference(),
                paymentRequest.amount(),
                paymentRequest.paymentMethod(),
                paymentRequest.customer().firstName(),
                paymentRequest.customer().lastName(),
                paymentRequest.customer().email()
        ));

        return payment.getId();
    }
}
