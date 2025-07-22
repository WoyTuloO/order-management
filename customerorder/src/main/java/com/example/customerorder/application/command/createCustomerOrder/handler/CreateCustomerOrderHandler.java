package com.example.customerorder.application.command.createCustomerOrder.handler;

import com.example.customerorder.application.command.createCustomerOrder.CreateCustomerOrderCommand;
import com.example.customerorder.application.port.in.CreateCustomerOrderUseCase;
import com.example.customerorder.application.port.out.CustomerOrderRepositoryPort;
import com.example.customerorder.domain.model.aggregate.CustomerOrder;
import com.example.manufacturingorder.application.command.createManufacturingOrders.CreateManufacturingOrdersCommand;
import com.example.manufacturingorder.application.port.in.CreateMultipleManufacturingOrdersUseCase;
import com.example.manufacturingorder.domain.model.valueobject.ManufacturingItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateCustomerOrderHandler implements CreateCustomerOrderUseCase {

    private final CustomerOrderRepositoryPort customerOrderRepositoryPort;
    private final CreateMultipleManufacturingOrdersUseCase createManufacturingOrdersUseCase;


    @Override
    public void createCustomerOrder(CreateCustomerOrderCommand command) {
        CustomerOrder customerOrder = CustomerOrder.create(command.customerId(), command.items());

        customerOrder.setId(customerOrderRepositoryPort.save(customerOrder));

        CreateManufacturingOrdersCommand createManufacturingOrderCommand = new CreateManufacturingOrdersCommand(
                customerOrder.getId(),
                customerOrder.getItems().stream()
                        .map(item -> new ManufacturingItem(item.productId(), item.quantity()))
                        .toList()
        );

        List<Long> manufacturingOrdersIds = new ArrayList<>(createManufacturingOrdersUseCase.createManufacturingOrders(createManufacturingOrderCommand));

        customerOrder.setManufacturingOrderIds(manufacturingOrdersIds);
        customerOrderRepositoryPort.save(customerOrder);

    }
}
