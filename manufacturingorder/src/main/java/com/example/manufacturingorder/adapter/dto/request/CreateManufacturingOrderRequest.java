package com.example.manufacturingorder.adapter.dto.request;

import com.example.manufacturingorder.application.command.createManufacturingOrder.CreateManufacturingOrderCommand;
import com.example.manufacturingorder.domain.model.valueobject.ManufacturingItem;

public record CreateManufacturingOrderRequest(
        Long customerOrderId,
        ManufacturingItem item
) {
    public CreateManufacturingOrderCommand toCommand() {
        return new CreateManufacturingOrderCommand(customerOrderId, item);
    }
}
