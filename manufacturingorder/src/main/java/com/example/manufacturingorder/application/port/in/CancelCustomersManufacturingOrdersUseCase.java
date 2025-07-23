package com.example.manufacturingorder.application.port.in;

import com.example.manufacturingorder.application.command.cancelCustomersManufacturingOrders.CancelCustomersManufacturingOrdersCommand;

public interface CancelCustomersManufacturingOrdersUseCase {
    void cancelManufacturingOrders(CancelCustomersManufacturingOrdersCommand command);
}
