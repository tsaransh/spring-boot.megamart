package com.megamart.order.service;

import com.megamart.order.controller.OrderRequest;

public interface OrderService {


    Integer createOrder(OrderRequest request);
}
