package com.megamart.order.service;

import com.megamart.order.dto.PurchaseRequest;
import com.megamart.order.entity.OrderLine;
import com.megamart.order.oderline.OrderLineRequest;

public interface OrderLineService {


    Integer addOrderLine(OrderLineRequest orderLineRequest);
}
