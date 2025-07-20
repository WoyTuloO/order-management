package com.example.customerorder.domain.model.valueobject;


public record OrderItem(
        Long productId,
        int quantity
) {
    public OrderItem {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive");
    }
}