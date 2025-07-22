package com.example.manufacturingorder.domain.event;

import com.example.manufacturingorder.application.command.createManufacturingOrders.CreateManufacturingOrdersCommand;
import com.example.manufacturingorder.domain.model.valueobject.ManufacturingItem;

import java.util.List;

public record CreateManufacturingOrdersEvent(
        Long customerOrderId,
        List<ManufacturingItem> items
) {
    public CreateManufacturingOrdersCommand getCommand() {
        return new CreateManufacturingOrdersCommand(customerOrderId, items);
    }
}