package com.example.manufacturingorder.application.command.updateManufacturingOrderStatus.handler;

import com.example.manufacturingorder.application.command.updateManufacturingOrderStatus.UpdateManufacturingOrderStatusCommand;
import com.example.manufacturingorder.application.port.in.UpdateManufacturingOrderStatusUseCase;
import com.example.manufacturingorder.application.port.out.ManufacturingOrderRepositoryPort;
import com.example.manufacturingorder.config.exceptions.CannotUpdateCompletedOrderException;
import com.example.manufacturingorder.domain.model.aggregate.ManufacturingOrder;
import com.example.manufacturingorder.domain.model.enums.ManufacturingStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UpdateManufacturingOrderStatusHandler implements UpdateManufacturingOrderStatusUseCase {

    private final ManufacturingOrderRepositoryPort orderRepository;

    @Override
    public void updateManufacturingOrderStatus(UpdateManufacturingOrderStatusCommand command) {

        ManufacturingOrder manufacturingOrder = orderRepository.findById(command.manufacturingOrderId());

        if(manufacturingOrder.getStatus() == ManufacturingStatus.COMPLETED)
            throw new CannotUpdateCompletedOrderException();

        manufacturingOrder.updateStatus(command.newStatus(), command.info());

    }
}
