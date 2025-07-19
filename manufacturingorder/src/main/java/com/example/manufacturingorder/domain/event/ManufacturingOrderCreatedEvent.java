package com.example.manufacturingorder.domain.event;

import com.example.manufacturingorder.domain.model.ManufacturingStatus;

import java.util.UUID;

public record ManufacturingOrderCreatedEvent(
        UUID orderId,
        ManufacturingStatus status
) {}
