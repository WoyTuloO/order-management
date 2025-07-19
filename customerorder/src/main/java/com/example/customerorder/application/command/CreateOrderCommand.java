package com.example.customerorder.application.command;

import com.example.customerorder.adapter.in.rest.dto.OrderItemDto;

import java.util.List;
import java.util.UUID;

public record CreateOrderCommand(
        UUID customerId,
        List<OrderItemDto> items
) {}
