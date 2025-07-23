package com.example.customerorder.application.command.updateGlobalCustomerOrderStatus.handler;

import com.example.customerorder.application.command.updateGlobalCustomerOrderStatus.UpdateGlobalCustomerOrderStatusCommand;
import com.example.customerorder.application.port.in.UpdateGlobalCustomerOrderStatusUseCase;
import com.example.customerorder.domain.port.CustomerOrderRepositoryPort;
import com.example.customerorder.domain.port.ManufacturingOrderFacadePort;
import com.example.customerorder.domain.model.aggregate.CustomerOrder;
import com.example.customerorder.domain.model.enums.OrderStatus;
import com.example.manufacturingorder.adapter.dto.response.GetManufacturingOrderResponse;
import com.example.manufacturingorder.domain.model.enums.ManufacturingStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UpdateGlobalCustomerOrderStatusHandler implements UpdateGlobalCustomerOrderStatusUseCase {

    private final CustomerOrderRepositoryPort customerOrderRepositoryPort;
    private final ManufacturingOrderFacadePort manufacturingOrdersFacadeAdapter;

    @Override
    public void updateGlobalCustomerOrderStatus(UpdateGlobalCustomerOrderStatusCommand command) {

        CustomerOrder customerOrder = customerOrderRepositoryPort.findById(command.customerOrderId());

        List<GetManufacturingOrderResponse> manufacturingOrders = manufacturingOrdersFacadeAdapter.getCustomerOrdersManufacturingOrders(customerOrder.getCustomerId());
        boolean allOrdersCompleted = manufacturingOrders.stream().allMatch(o -> o.status() == ManufacturingStatus.COMPLETED || o.status() == ManufacturingStatus.CANCELLED);

        if(allOrdersCompleted){
            customerOrder.setStatus(OrderStatus.COMPLETED);
            customerOrderRepositoryPort.update(customerOrder);
        }else if(command.newStatus() != OrderStatus.COMPLETED) {
            customerOrder.setStatus(command.newStatus());
            customerOrder.setInfo(command.info());
            customerOrderRepositoryPort.update(customerOrder);
        }


    }
}
