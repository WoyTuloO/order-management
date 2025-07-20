package com.example.manufacturingorder.domain.event;

import com.example.manufacturingorder.domain.model.enums.ManufacturingStatus;

public record ManufacturingOrderCreatedEvent(
        Long orderId,
        ManufacturingStatus status
) {}
