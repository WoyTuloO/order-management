package com.example.customerorder.application.command.cancelCustomerOrder.handler;

import com.example.customerorder.application.command.cancelCustomerOrder.CancelCustomerOrderCommand;
import com.example.customerorder.application.port.in.CancelCustomerOrderUseCase;
import com.example.customerorder.application.port.out.CustomerOrderRepositoryPort;
import com.example.customerorder.domain.model.aggregate.CustomerOrder;
import com.example.manufacturingorder.adapter.in.rest.dto.response.GetManufacturingOrderResponse;
import com.example.manufacturingorder.application.command.cancelCustomersManufacturingOrders.CancelCustomersManufacturingOrdersCommand;
import com.example.manufacturingorder.application.port.in.CancelCustomersManufacturingOrdersUseCase;
import com.example.manufacturingorder.application.port.in.GetCustomersManufacturingOrdersUseCase;
import com.example.manufacturingorder.application.query.getCustomersManufacturingOrders.GetCustomersManufacturingOrdersQuery;
import com.example.manufacturingorder.application.query.getCustomersManufacturingOrders.handler.GetCustomersManufacturingOrdersHandler;
import com.example.manufacturingorder.domain.model.enums.ManufacturingStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CancelCustomerOrderHandler implements CancelCustomerOrderUseCase {

    private final CustomerOrderRepositoryPort customerOrderRepository;
    private final GetCustomersManufacturingOrdersUseCase getCustomersManufacturingOrdersUseCase;
    private final CancelCustomersManufacturingOrdersUseCase cancelCustomersManufacturingOrdersUseCase;

    @Override
    public void cancelCustomerOrder(CancelCustomerOrderCommand cancelCustomerOrderCommand) {

        List<GetManufacturingOrderResponse> manufacturingOrders = getCustomersManufacturingOrdersUseCase.getManufacturingOrders(new GetCustomersManufacturingOrdersQuery(cancelCustomerOrderCommand.customerOrderId()));
        boolean isCancelable = manufacturingOrders.stream()
                .allMatch(o -> o.status() == ManufacturingStatus.PENDING || o.status() == ManufacturingStatus.CANCELLED);

        if(isCancelable){
            CustomerOrder customerOrder = customerOrderRepository.findById(cancelCustomerOrderCommand.customerOrderId());
            customerOrder.cancel("Order cancelled by customer");
            customerOrderRepository.save(customerOrder);
        }


        cancelCustomersManufacturingOrdersUseCase.cancelManufacturingOrders(
                new CancelCustomersManufacturingOrdersCommand(cancelCustomerOrderCommand.customerOrderId(), "Cancelled by customer order cancellation")
        );



    }
}
