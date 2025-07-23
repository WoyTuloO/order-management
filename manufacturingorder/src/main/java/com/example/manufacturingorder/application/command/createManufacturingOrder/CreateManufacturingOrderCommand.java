package com.example.manufacturingorder.application.command.createManufacturingOrder;

import com.example.manufacturingorder.domain.model.valueobject.ManufacturingItem;

public record CreateManufacturingOrderCommand(
        Long customerOrderId,
        ManufacturingItem item
) {}
