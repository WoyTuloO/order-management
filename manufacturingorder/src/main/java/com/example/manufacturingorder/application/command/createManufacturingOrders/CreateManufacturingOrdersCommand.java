package com.example.manufacturingorder.application.command.createManufacturingOrders;

import com.example.manufacturingorder.domain.model.valueobject.ManufacturingItem;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CreateManufacturingOrdersCommand(
        Long customerOrderId,
        @NotEmpty
        List<ManufacturingItem> items
) {}
