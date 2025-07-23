package com.example.customerorder.adapter.dto.request;

import com.example.customerorder.application.command.updateGlobalCustomerOrderStatus.UpdateGlobalCustomerOrderStatusCommand;
import com.example.customerorder.domain.model.enums.OrderStatus;

public record UpdateGlobalCustomerOrderStatusRequest(
        Long customerOrderId,
        OrderStatus newStatus,
        String info
) {
    public UpdateGlobalCustomerOrderStatusCommand toCommand() {
        return new UpdateGlobalCustomerOrderStatusCommand(
                customerOrderId,
                newStatus,
                info
        );
    }
}
