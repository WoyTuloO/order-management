package com.example.manufacturingorder.adapter.in.rest.dto;

import com.example.manufacturingorder.application.command.CreateManufacturingOrderCommand;
import com.example.manufacturingorder.domain.model.valueobject.ManufacturingItem;

import java.util.List;
import java.util.UUID;

public record CreateManufacturingOrderRequest(
        Long customerOrderId,
        List<ManufacturingItem> items
) {
    public CreateManufacturingOrderCommand toCommand() {
        return new CreateManufacturingOrderCommand(customerOrderId, items);
    }
}
