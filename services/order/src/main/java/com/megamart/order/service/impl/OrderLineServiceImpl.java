package com.megamart.order.service.impl;

import com.megamart.order.dto.OrderLineMapper;
import com.megamart.order.oderline.OrderLineRequest;
import com.megamart.order.repository.OrderLineRepository;
import com.megamart.order.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineServiceImpl implements OrderLineService {

    private final OrderLineRepository repository;
    private final OrderLineMapper orderLineMapper;

    @Override
    public void addOrderLine(OrderLineRequest orderLineRequest) {

    }
}
