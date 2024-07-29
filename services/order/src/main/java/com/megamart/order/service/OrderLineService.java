package com.megamart.order.service;

import com.megamart.order.oderline.OrderLineRequest;
import com.megamart.order.payload.OrderLineResponse;

import java.util.List;

public interface OrderLineService {


    Integer addOrderLine(OrderLineRequest orderLineRequest);

    List<OrderLineResponse> findAllByOrderId(Integer orderId);
}
