package com.example.customerorder.application.command;

import com.example.customerorder.domain.model.valueobject.OrderItem;

import java.util.List;

public record CreateCustomerOrderCommand(
        Long customerId,
        List<OrderItem> items

) {
}
