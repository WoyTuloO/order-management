package com.example.customerorder.application.command.handler;

import com.example.customerorder.application.command.CreateCustomerOrderCommand;
import com.example.customerorder.application.port.in.CreateCustomerOrderUseCase;
import com.example.customerorder.application.port.out.CustomerOrderRepositoryPort;
import com.example.customerorder.domain.model.aggregate.CustomerOrder;
import com.example.manufacturingorder.application.command.CreateManufacturingOrderCommand;
import com.example.manufacturingorder.application.port.in.CreateManufacturingOrderUseCase;
import com.example.manufacturingorder.domain.model.valueobject.ManufacturingItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCustomerOrderHandler implements CreateCustomerOrderUseCase {

    private final CustomerOrderRepositoryPort customerOrderRepositoryPort;
    private final CreateManufacturingOrderUseCase createManufacturingOrderUseCase;



    @Override
    public void createCustomerOrder(CreateCustomerOrderCommand command) {
        CustomerOrder customerOrder = CustomerOrder.create(command.customerId(), command.items());

        customerOrder.setId(customerOrderRepositoryPort.save(customerOrder));


        CreateManufacturingOrderCommand createManufacturingOrderCommand = new CreateManufacturingOrderCommand(
                customerOrder.getId(),
                customerOrder.getItems().stream()
                        .map(item -> new ManufacturingItem(item.productId(), item.quantity()))
                        .toList()
        );
        createManufacturingOrderUseCase.createManufacturingOrder(createManufacturingOrderCommand);


    }
}
