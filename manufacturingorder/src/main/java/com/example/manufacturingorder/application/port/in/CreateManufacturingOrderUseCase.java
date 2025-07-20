package com.example.manufacturingorder.application.port.in;

import com.example.manufacturingorder.application.command.CreateManufacturingOrderCommand;


public interface CreateManufacturingOrderUseCase {
    void createManufacturingOrder(CreateManufacturingOrderCommand command);
}
