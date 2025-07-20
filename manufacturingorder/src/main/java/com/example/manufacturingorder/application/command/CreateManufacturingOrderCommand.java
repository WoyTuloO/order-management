package com.example.manufacturingorder.application.command;

import com.example.manufacturingorder.domain.model.valueobject.ManufacturingItem;

import java.util.List;

public record CreateManufacturingOrderCommand(
        Long customerOrderId,
        List<ManufacturingItem> items
) {}
