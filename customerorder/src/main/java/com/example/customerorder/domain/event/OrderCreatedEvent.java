package com.example.customerorder.domain.event;

import com.example.customerorder.domain.model.OrderItem;

import java.util.List;
import java.util.UUID;

public record OrderCreatedEvent(
        UUID orderId,
        List<OrderItem> items
) {}
