package com.example.manufacturingorder.adapter.in.rest.dto;

import com.example.manufacturingorder.domain.model.enums.ManufacturingStatus;


public record ManufacturingOrderResponse(
        Long orderId,
        ManufacturingStatus status
) {}
