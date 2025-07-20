package com.example.customerorder.application.query;

import com.example.customerorder.domain.model.aggregate.CustomerOrder;
import com.example.customerorder.domain.model.valueobject.OrderItem;
import com.example.customerorder.domain.model.enums.OrderStatus;

import java.util.List;

public record GetCustomerOrderResponse(
        long id,
        Long customerId,
        List<OrderItem> items,
        OrderStatus status,
        String info
) {
    public static GetCustomerOrderResponse fromDomain(CustomerOrder order) {
        return new GetCustomerOrderResponse(
                order.getId(),
                order.getCustomerId(),
                order.getItems(),
                order.getStatus(),
                order.getInfo()
        );
    }
}
