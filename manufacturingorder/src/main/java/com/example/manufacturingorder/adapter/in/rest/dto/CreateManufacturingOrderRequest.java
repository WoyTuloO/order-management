package com.example.manufacturingorder.adapter.in.rest.dto;

import com.example.manufacturingorder.application.command.CreateManufacturingOrderCommand;

import java.util.List;
import java.util.UUID;

public record CreateManufacturingOrderRequest(
        UUID customerOrderId,
        List<ManufacturingItemDto> items
) {
    public CreateManufacturingOrderCommand toCommand() {
        return new CreateManufacturingOrderCommand(customerOrderId, items);
    }
}
