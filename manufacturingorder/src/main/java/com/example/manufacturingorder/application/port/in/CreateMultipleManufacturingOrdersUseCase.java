package com.example.manufacturingorder.application.port.in;

import com.example.manufacturingorder.application.command.createManufacturingOrders.CreateManufacturingOrdersCommand;

import java.util.List;

public interface CreateMultipleManufacturingOrdersUseCase {
    List<Long> createManufacturingOrders(CreateManufacturingOrdersCommand command);

}
