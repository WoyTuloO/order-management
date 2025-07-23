package com.example.manufacturingorder.application.query.getCustomersManufacturingOrders.handler;

import com.example.manufacturingorder.adapter.dto.response.GetManufacturingOrderResponse;
import com.example.manufacturingorder.application.port.in.GetCustomersManufacturingOrdersUseCase;
import com.example.manufacturingorder.application.port.out.ManufacturingOrderRepositoryPort;
import com.example.manufacturingorder.application.query.getCustomersManufacturingOrders.GetCustomersManufacturingOrdersQuery;
import com.example.manufacturingorder.domain.model.aggregate.ManufacturingOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCustomersManufacturingOrdersHandler implements GetCustomersManufacturingOrdersUseCase {

    private final ManufacturingOrderRepositoryPort manufacturingOrderRepositoryPort;

    @Override
    public List<GetManufacturingOrderResponse> getManufacturingOrders(GetCustomersManufacturingOrdersQuery query) {
        List<ManufacturingOrder> manufacturingOrders = manufacturingOrderRepositoryPort.findByCustomerOrderId(query.customerOrderId());
        return manufacturingOrders.stream().map(GetManufacturingOrderResponse::fromDomain).toList();
    }

}
