package com.example.manufacturingorder.application.command.cancelCustomersManufacturingOrders.handler;

import com.example.manufacturingorder.application.command.cancelCustomersManufacturingOrders.CancelCustomersManufacturingOrdersCommand;
import com.example.manufacturingorder.application.port.in.CancelCustomersManufacturingOrdersUseCase;
import com.example.manufacturingorder.application.port.out.ManufacturingOrderRepositoryPort;
import com.example.manufacturingorder.domain.model.aggregate.ManufacturingOrder;
import com.example.manufacturingorder.domain.model.enums.ManufacturingStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CancelCustomersManufacturingOrdersHandler implements CancelCustomersManufacturingOrdersUseCase {

    private final ManufacturingOrderRepositoryPort manufacturingOrderRepositoryPort;

    @Override
    public void cancelManufacturingOrders(CancelCustomersManufacturingOrdersCommand command) {

        List<ManufacturingOrder> manufacturingOrders = manufacturingOrderRepositoryPort.findByCustomerOrderId(command.customerOrderId());
        manufacturingOrders.forEach(order -> cancelIfPending(order, command.reason()));
        manufacturingOrderRepositoryPort.save(manufacturingOrders);


    }

    private void cancelIfPending(ManufacturingOrder order, String reason) {
        if (order.getStatus() == ManufacturingStatus.PENDING) {
            order.markAsCancelled(reason);
        }
    }
}
