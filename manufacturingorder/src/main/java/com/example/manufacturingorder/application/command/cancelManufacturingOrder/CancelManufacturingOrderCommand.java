package com.example.manufacturingorder.application.command.cancelManufacturingOrder;

public record CancelManufacturingOrderCommand(
        Long manufacturingOrderId,
        String reason
) {
}
