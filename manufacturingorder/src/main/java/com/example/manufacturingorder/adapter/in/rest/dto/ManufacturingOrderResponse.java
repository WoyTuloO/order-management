package com.example.manufacturingorder.adapter.in.rest.dto;

import com.example.manufacturingorder.domain.model.ManufacturingStatus;

import java.util.UUID;

public record ManufacturingOrderResponse(
        UUID orderId,
        ManufacturingStatus status
) {}
