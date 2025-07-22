package com.example.manufacturingorder.domain.event;

import com.example.manufacturingorder.application.command.cancelCustomersManufacturingOrders.CancelCustomersManufacturingOrdersCommand;

public record CancelManufacturingOrdersEvent(
    Long customerOrderId,
    String reason
) {
    public CancelCustomersManufacturingOrdersCommand getCommand() {
        return new CancelCustomersManufacturingOrdersCommand(customerOrderId, reason);
    }
}