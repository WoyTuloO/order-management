package com.example.customerorder.application.command.createCustomerOrder.handler;

import com.example.customerorder.application.command.createCustomerOrder.CreateCustomerOrderCommand;
import com.example.customerorder.application.port.in.CreateCustomerOrderUseCase;
import com.example.customerorder.domain.port.CustomerOrderRepositoryPort;
import com.example.customerorder.domain.model.aggregate.CustomerOrder;
import com.example.manufacturingorder.domain.event.CreateManufacturingOrdersEvent;
import com.example.manufacturingorder.domain.model.valueobject.ManufacturingItem;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CreateCustomerOrderHandler implements CreateCustomerOrderUseCase {

    private final CustomerOrderRepositoryPort customerOrderRepositoryPort;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void createCustomerOrder(CreateCustomerOrderCommand command) {
        CustomerOrder customerOrder = CustomerOrder.create(command.customerId(), command.items());

        customerOrder.setId(customerOrderRepositoryPort.save(customerOrder));

        CreateManufacturingOrdersEvent createManufacturingOrdersEvent = new CreateManufacturingOrdersEvent(
                customerOrder.getId(),
                customerOrder.getItems().stream()
                        .map(item -> new ManufacturingItem(item.productId(), item.quantity()))
                        .toList()
        );

        eventPublisher.publishEvent(createManufacturingOrdersEvent);
        customerOrderRepositoryPort.save(customerOrder);

    }
}
