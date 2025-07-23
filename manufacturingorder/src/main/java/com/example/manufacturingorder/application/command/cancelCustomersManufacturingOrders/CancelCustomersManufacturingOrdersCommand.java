package com.example.manufacturingorder.application.command.cancelCustomersManufacturingOrders;

public record CancelCustomersManufacturingOrdersCommand(
        Long customerOrderId,
        String reason
) {
}
