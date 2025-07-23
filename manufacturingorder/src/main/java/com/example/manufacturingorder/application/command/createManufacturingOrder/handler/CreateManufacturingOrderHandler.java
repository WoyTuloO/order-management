package com.example.manufacturingorder.application.command.createManufacturingOrder.handler;

import com.example.manufacturingorder.application.command.createManufacturingOrder.CreateManufacturingOrderCommand;
import com.example.manufacturingorder.application.port.in.CreateManufacturingOrderUseCase;
import com.example.manufacturingorder.domain.port.ManufacturingOrderRepositoryPort;
import com.example.manufacturingorder.domain.model.aggregate.ManufacturingOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CreateManufacturingOrderHandler implements CreateManufacturingOrderUseCase {

    private final ManufacturingOrderRepositoryPort manufacturingOrderRepositoryPort;

    @Override
    public void createManufacturingOrder(CreateManufacturingOrderCommand command) {
        ManufacturingOrder manufacturingOrders = ManufacturingOrder.create(command.customerOrderId(), command.item());
        manufacturingOrderRepositoryPort.save(manufacturingOrders);
    }
}
