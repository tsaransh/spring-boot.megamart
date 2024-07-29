package com.megamart.order.service.impl;

import com.megamart.order.payload.OrderLineMapper;
import com.megamart.order.oderline.OrderLineRequest;
import com.megamart.order.payload.OrderLineResponse;
import com.megamart.order.repository.OrderLineRepository;
import com.megamart.order.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineServiceImpl implements OrderLineService {

    private final OrderLineRepository repository;
    private final OrderLineMapper orderLineMapper;

    @Override
    public Integer addOrderLine(OrderLineRequest orderLineRequest) {
        var order = orderLineMapper.toOrderLine(orderLineRequest);
        return repository.save(order).getId();
    }

    @Override
    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return repository.findAllByOrderId(orderId)
                .stream().map(orderLineMapper::toOrderLineResponse)
                .collect(Collectors.toList());
    }
}
