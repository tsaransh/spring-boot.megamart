package com.megamart.order.service;

import com.megamart.order.payload.OrderRequest;
import com.megamart.order.payload.OrderResponse;

import java.util.List;

public interface OrderService {


    Integer createOrder(OrderRequest request);

    List<OrderResponse> findAll();

    OrderResponse findById(Integer id);
}
