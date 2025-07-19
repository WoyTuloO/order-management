package com.example.customerorder.adapter.in.rest.dto;

import com.example.customerorder.domain.model.CustomerOrder;
import com.example.customerorder.domain.model.OrderStatus;

import java.util.UUID;

public record OrderResponse(
        UUID orderId,
        OrderStatus status,
        String message
) {
    public static OrderResponse fromDomain(CustomerOrder order) {
        return new OrderResponse(order.getId(), order.getStatus(), "Order created");
    }
}
