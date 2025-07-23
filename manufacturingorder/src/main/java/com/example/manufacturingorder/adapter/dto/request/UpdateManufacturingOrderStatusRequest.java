package com.example.manufacturingorder.adapter.dto.request;

import com.example.manufacturingorder.application.command.updateManufacturingOrderStatus.UpdateManufacturingOrderStatusCommand;
import com.example.manufacturingorder.domain.model.enums.ManufacturingStatus;

public record UpdateManufacturingOrderStatusRequest(
        Long manufacturingOrderId,
        ManufacturingStatus newStatus,
        String info
){
    public UpdateManufacturingOrderStatusCommand toCommand() {
        return new UpdateManufacturingOrderStatusCommand(
                manufacturingOrderId,
                newStatus,
                info
        );
    }
}