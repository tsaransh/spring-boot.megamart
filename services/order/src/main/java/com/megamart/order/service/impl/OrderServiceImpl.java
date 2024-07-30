package com.megamart.order.service.impl;

import com.megamart.order.kafka.OrderProducer;
import com.megamart.order.payload.*;
import com.megamart.order.customer.CustomerClient;
import com.megamart.order.exception.BusinessException;
import com.megamart.order.oderline.OrderLineRequest;
import com.megamart.order.payment.PaymentClient;
import com.megamart.order.product.ProductClient;
import com.megamart.order.repository.OrderRepository;
import com.megamart.order.service.OrderLineService;
import com.megamart.order.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    @Override
    public Integer createOrder(OrderRequest request) {

        // check for the customer (using OpenFeign)
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() ->
                    new BusinessException("cannot create an order :: Customer Not Found")
                );

        // purchase the product (using REST template)
        var purchaseProduct = this.productClient.purchaseProduct(request.products());

        // create an order
        var order = orderRepository.save(mapper.toOrder(request));

        // persist the order line
        for(PurchaseRequest purchaseRequest: request.products()) {
            orderLineService.addOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        // start payment
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        // send order confirmation (using kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchaseProduct
                )
        );

        return order.getId();
    }

    @Override
    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse findById(Integer id) {
        return orderRepository.findById(id).map(mapper::fromOrder).orElseThrow(
                () -> new EntityNotFoundException("No order found with id: "+id)
        );
    }
}
