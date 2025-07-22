package com.example.manufacturingorder.application.command.updateManufacturingOrderStatus;

import com.example.manufacturingorder.domain.model.enums.ManufacturingStatus;

public record UpdateManufacturingOrderStatusCommand(
        Long manufacturingOrderId,
        ManufacturingStatus newStatus,
        String info
) {
}
