package com.megamart.order.service.impl;

import com.megamart.order.controller.OrderRequest;
import com.megamart.order.customer.CustomerClient;
import com.megamart.order.dto.OrderMapper;
import com.megamart.order.dto.PurchaseRequest;
import com.megamart.order.exception.BusinessException;
import com.megamart.order.oderline.OrderLineRequest;
import com.megamart.order.product.ProductClient;
import com.megamart.order.repository.OrderRepository;
import com.megamart.order.service.OrderLineService;
import com.megamart.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;

    @Override
    public Integer createOrder(OrderRequest request) {

        // check for the customer (using OpenFeign)
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> {
                    return new BusinessException("cannot create an order :: Customer Not Found");
                });

        // purchase the product (using REST template)
        this.productClient.purchaseProduct(request.products());

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

        return 0;
    }
}
