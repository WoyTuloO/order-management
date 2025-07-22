package com.example.customerorder.application.query.handler;


import com.example.customerorder.adapter.dto.response.CustomerOrderItemResponse;
import com.example.customerorder.application.port.in.GetCustomerOrderUseCase;
import com.example.customerorder.application.port.out.CustomerOrderRepositoryPort;
import com.example.customerorder.application.port.out.ManufacturingOrderFacadePort;
import com.example.customerorder.application.query.GetCustomerOrderQuery;
import com.example.customerorder.adapter.dto.response.GetCustomerOrderResponse;
import com.example.customerorder.domain.model.aggregate.CustomerOrder;
import com.example.manufacturingorder.adapter.in.rest.dto.response.GetManufacturingOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCustomerOrderHandler implements GetCustomerOrderUseCase {

    private final CustomerOrderRepositoryPort customerOrderRepositoryPort;
    private final ManufacturingOrderFacadePort manufacturingOrdersFacadeAdapter;

    @Override
    public GetCustomerOrderResponse getCustomerOrder(GetCustomerOrderQuery query) {
        CustomerOrder order = customerOrderRepositoryPort.findById(query.id());

        List<GetManufacturingOrderResponse> manufacturingOrders = manufacturingOrdersFacadeAdapter.getCustomerOrdersManufacturingOrders(query.id());

        return new GetCustomerOrderResponse(
                order.getId(),
                order.getCustomerId(),
                manufacturingOrders.stream().map(CustomerOrderItemResponse::fromManufacturingOrderResponse).toList(),
                order.getStatus(),
                order.getInfo()
        );
    }
}
