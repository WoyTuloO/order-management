package com.example.customerorder.adapter.in.rest.dto;

import com.example.customerorder.application.command.CreateOrderCommand;

import java.util.List;
import java.util.UUID;

public record CreateOrderRequest(
        UUID customerId,
        List<OrderItemDto> items
        ) {
    public CreateOrderCommand toCommand() {
        return new CreateOrderCommand(customerId, items);
    }
}
