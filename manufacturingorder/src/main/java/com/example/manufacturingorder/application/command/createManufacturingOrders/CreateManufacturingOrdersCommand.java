package com.example.manufacturingorder.application.command.createManufacturingOrders;

import com.example.manufacturingorder.domain.model.valueobject.ManufacturingItem;

import java.util.List;

public record CreateManufacturingOrdersCommand(
        Long customerOrderId,
        List<ManufacturingItem> items
) {}
