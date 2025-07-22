package com.example.manufacturingorder.application.port.in;

import com.example.manufacturingorder.application.command.updateManufacturingOrderStatus.UpdateManufacturingOrderStatusCommand;

public interface UpdateManufacturingOrderStatusUseCase {
    void updateManufacturingOrderStatus(UpdateManufacturingOrderStatusCommand command);
}
