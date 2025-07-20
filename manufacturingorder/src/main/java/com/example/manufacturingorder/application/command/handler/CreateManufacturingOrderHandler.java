package com.example.manufacturingorder.application.command.handler;

import com.example.manufacturingorder.application.command.CreateManufacturingOrderCommand;
import com.example.manufacturingorder.application.port.in.CreateManufacturingOrderUseCase;
import com.example.manufacturingorder.application.port.out.ManufacturingOrderRepositoryPort;
import com.example.manufacturingorder.domain.model.aggregate.ManufacturingOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateManufacturingOrderHandler implements CreateManufacturingOrderUseCase {

    private final ManufacturingOrderRepositoryPort manufacturingOrderRepositoryPort;

    @Override
    public void createManufacturingOrder(CreateManufacturingOrderCommand command) {
        ManufacturingOrder manufacturingOrder = ManufacturingOrder.create(command.customerOrderId(), command.items());
        manufacturingOrderRepositoryPort.save(manufacturingOrder);
    }
}
