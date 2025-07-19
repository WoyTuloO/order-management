package com.example.customerorder.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data

public class CustomerOrder {
    private UUID id;
    private UUID customerId;

    private List<OrderItem> items;
    private OrderStatus status;
    private String cancellationReason;

    public void cancel(String manufacturingOrderWasRejected) {
        this.status = OrderStatus.CANCELLED;
        this.cancellationReason = manufacturingOrderWasRejected;
    }
}