package com.example.customerorder.application.command.UpdateGlobalCustomerOrderStatus.handler;

import com.example.customerorder.application.command.UpdateGlobalCustomerOrderStatus.UpdateGlobalCustomerOrderStatusCommand;
import com.example.customerorder.application.port.in.UpdateGlobalCustomerOrderStatusUseCase;
import com.example.customerorder.application.port.out.CustomerOrderRepositoryPort;
import com.example.customerorder.domain.model.aggregate.CustomerOrder;
import com.example.customerorder.domain.model.enums.OrderStatus;
import com.example.manufacturingorder.adapter.in.rest.dto.response.GetManufacturingOrderResponse;
import com.example.manufacturingorder.application.port.in.GetCustomersManufacturingOrdersUseCase;
import com.example.manufacturingorder.application.query.getCustomersManufacturingOrders.GetCustomersManufacturingOrdersQuery;
import com.example.manufacturingorder.domain.model.enums.ManufacturingStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UpdateGlobalCustomerOrderStatusHandler implements UpdateGlobalCustomerOrderStatusUseCase {

    private final CustomerOrderRepositoryPort customerOrderRepositoryPort;
    private final GetCustomersManufacturingOrdersUseCase getCustomersManufacturingOrdersUseCase;

    @Override
    public void updateGlobalCustomerOrderStatus(UpdateGlobalCustomerOrderStatusCommand command) {

        CustomerOrder customerOrder = customerOrderRepositoryPort.findById(command.customerOrderId());

        List<GetManufacturingOrderResponse> manufacturingOrders = getCustomersManufacturingOrdersUseCase.getManufacturingOrders(new GetCustomersManufacturingOrdersQuery(customerOrder.getCustomerId()));
        boolean allOrdersCompleted = manufacturingOrders.stream().allMatch(o -> o.status() == ManufacturingStatus.COMPLETED);

        if(allOrdersCompleted){
            customerOrder.setStatus(OrderStatus.COMPLETED);
            customerOrderRepositoryPort.update(customerOrder);
        }else{
            customerOrder.setStatus(command.newStatus());
            customerOrder.setInfo(command.info());
            customerOrderRepositoryPort.update(customerOrder);
        }


    }
}
