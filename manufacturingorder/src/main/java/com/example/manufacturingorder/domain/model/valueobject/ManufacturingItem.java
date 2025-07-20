package com.example.manufacturingorder.domain.model.valueobject;

public record ManufacturingItem(
        Long componentId,
        int requiredQuantity
) {}
