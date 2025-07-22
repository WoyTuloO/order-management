package com.example.manufacturingorder.application.command.cancelManufacturingOrder.handler;

import com.example.manufacturingorder.application.command.cancelManufacturingOrder.CancelManufacturingOrderCommand;
import com.example.manufacturingorder.application.port.in.CancelManufacturingOrderUseCase;
import com.example.manufacturingorder.application.port.out.ManufacturingOrderRepositoryPort;
import com.example.manufacturingorder.domain.model.aggregate.ManufacturingOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CancelManufacturingOrderHandler implements CancelManufacturingOrderUseCase {

    private final ManufacturingOrderRepositoryPort manufacturingOrderRepositoryPort;

    @Override
    public void cancelManufacturingOrder(CancelManufacturingOrderCommand command) {
        ManufacturingOrder manufacturingOrder = manufacturingOrderRepositoryPort.findById(command.manufacturingOrderId());
        manufacturingOrder.markAsCancelled(command.reason());
        manufacturingOrderRepositoryPort.update(manufacturingOrder);
    }
}
