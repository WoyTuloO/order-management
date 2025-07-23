package com.example.customerorder.application.command.UpdateGlobalCustomerOrderStatus;

import com.example.customerorder.domain.model.enums.OrderStatus;

public record UpdateGlobalCustomerOrderStatusCommand(
        Long customerOrderId,
        OrderStatus newStatus,
        String info
) {
}
