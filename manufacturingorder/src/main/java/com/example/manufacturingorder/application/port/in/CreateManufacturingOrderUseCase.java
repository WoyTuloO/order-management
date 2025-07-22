package com.example.manufacturingorder.application.port.in;

import com.example.manufacturingorder.application.command.createManufacturingOrder.CreateManufacturingOrderCommand;
import com.example.manufacturingorder.application.command.createManufacturingOrders.CreateManufacturingOrdersCommand;


public interface CreateManufacturingOrderUseCase {
    void createManufacturingOrder(CreateManufacturingOrderCommand command);
}
