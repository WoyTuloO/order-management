package com.example.customerorder.domain.model.valueobject;


import com.example.customerorder.config.exceptions.NonPositiveAmountException;


public record OrderItem(
        Long productId,
        int quantity
) {
    public OrderItem {
        if (quantity <= 0 ) throw new NonPositiveAmountException("Quantity must be positive");
        if (productId == null || productId == 0) throw new IllegalArgumentException("Product ID must be valid");
    }
}