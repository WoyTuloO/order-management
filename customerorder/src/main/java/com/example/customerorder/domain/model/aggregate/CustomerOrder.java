package com.example.customerorder.domain.model.aggregate;

import com.example.customerorder.domain.model.enums.OrderStatus;
import com.example.customerorder.domain.model.valueobject.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
public class CustomerOrder {
    private Long id;
    private Long customerId;
    private List<OrderItem> items;
    private OrderStatus status;
    private String info;
    private List<Long> manufacturingOrderIds = new ArrayList<>();

    public static CustomerOrder create(Long customerId, List<OrderItem> items) {
        validateItems(items);
        return new CustomerOrder(customerId, items);
    }

    private CustomerOrder(Long customerId, List<OrderItem> items) {
        this.customerId = customerId;
        this.items = items;
        this.status = OrderStatus.PENDING;
        this.info = null;
    }


    public static CustomerOrder recreate(Long id, Long customerId, List<OrderItem> items,
                                         OrderStatus status, String cancellationReason, List<Long> manufacturingIds) {
        return new CustomerOrder(id, customerId, items, status, cancellationReason, manufacturingIds != null ? new ArrayList<>(manufacturingIds) : new ArrayList<>());
    }

    public void cancel(String reason) {
        this.status = OrderStatus.CANCELLED;
        this.info = reason;
    }

    private static void validateItems(List<OrderItem> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Order must have items");
        }
    }
}