package com.example.manufacturingorder.adapter.dto.request;

import com.example.manufacturingorder.application.command.cancelManufacturingOrder.CancelManufacturingOrderCommand;

public record CancelManufacturingOrderRequest(
        Long manufacturingOrderId,
        String reason
) {
    public CancelManufacturingOrderCommand toCommand() {
        return new CancelManufacturingOrderCommand(
                manufacturingOrderId,
                reason
        );
    }
}
