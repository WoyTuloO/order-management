package com.example.manufacturingorder.adapter.dto.request;

import com.example.manufacturingorder.application.command.createManufacturingOrders.CreateManufacturingOrdersCommand;
import com.example.manufacturingorder.domain.model.valueobject.ManufacturingItem;

import java.util.List;

public record CreateMultipleManufacturingOrdersRequest(
        Long customerOrderId,
        List<ManufacturingItem> items
) {
    public CreateManufacturingOrdersCommand toCommand() {
        return new CreateManufacturingOrdersCommand(customerOrderId, items);
    }
}
