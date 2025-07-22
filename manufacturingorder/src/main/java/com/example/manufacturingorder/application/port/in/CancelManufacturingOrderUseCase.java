package com.example.manufacturingorder.application.port.in;

import com.example.manufacturingorder.application.command.cancelManufacturingOrder.CancelManufacturingOrderCommand;

public interface CancelManufacturingOrderUseCase {
    void cancelManufacturingOrder(CancelManufacturingOrderCommand command);
}
