package com.example.manufacturingorder.adapter.in.rest.dto;

import java.util.UUID;

public record ManufacturingItemDto(
        UUID productId,
        int quantity
) {}
