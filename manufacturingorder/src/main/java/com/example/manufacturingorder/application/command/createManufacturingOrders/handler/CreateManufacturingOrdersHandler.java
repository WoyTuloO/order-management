package com.example.manufacturingorder.application.command.createManufacturingOrders.handler;

import com.example.manufacturingorder.application.command.createManufacturingOrders.CreateManufacturingOrdersCommand;
import com.example.manufacturingorder.application.port.in.CreateMultipleManufacturingOrdersUseCase;
import com.example.manufacturingorder.application.port.out.ManufacturingOrderRepositoryPort;
import com.example.manufacturingorder.domain.model.aggregate.ManufacturingOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CreateManufacturingOrdersHandler implements CreateMultipleManufacturingOrdersUseCase {

    private final ManufacturingOrderRepositoryPort manufacturingOrderRepositoryPort;

    @Override
    public void createManufacturingOrders(CreateManufacturingOrdersCommand command) {
        List<ManufacturingOrder> manufacturingOrders = ManufacturingOrder.create(command.customerOrderId(), command.items());
        manufacturingOrderRepositoryPort.save(manufacturingOrders);
    }
}
