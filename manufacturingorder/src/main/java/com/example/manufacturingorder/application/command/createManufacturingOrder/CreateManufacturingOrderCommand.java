package com.example.manufacturingorder.application.command.createManufacturingOrder;

import com.example.manufacturingorder.domain.model.valueobject.ManufacturingItem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateManufacturingOrderCommand(
        @NotBlank
        Long customerOrderId,
        @NotNull
        ManufacturingItem item
) {}
