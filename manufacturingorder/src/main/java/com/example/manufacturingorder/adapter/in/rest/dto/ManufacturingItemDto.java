package com.example.manufacturingorder.adapter.in.rest.dto;

import java.util.UUID;

public record ManufacturingItemDto(
        Long productId,
        int quantity
) {}
