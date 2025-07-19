package com.example.manufacturingorder.application.command;

import com.example.manufacturingorder.adapter.in.rest.dto.ManufacturingItemDto;

import java.util.List;
import java.util.UUID;

public record CreateManufacturingOrderCommand(
        UUID customerOrderId,
        List<ManufacturingItemDto> items
) {}
