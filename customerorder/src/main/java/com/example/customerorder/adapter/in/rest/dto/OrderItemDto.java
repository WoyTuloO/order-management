package com.example.customerorder.adapter.in.rest.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemDto(
        UUID productId,
        int quantity,
        BigDecimal price) {
}
