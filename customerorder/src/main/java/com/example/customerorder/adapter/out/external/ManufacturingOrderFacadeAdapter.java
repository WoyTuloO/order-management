package com.example.customerorder.adapter.out.external;

import com.example.customerorder.application.port.out.ManufacturingOrderFacadePort;
import com.example.manufacturingorder.adapter.in.rest.dto.response.GetManufacturingOrderResponse;
import com.example.manufacturingorder.application.port.in.GetCustomersManufacturingOrdersUseCase;
import com.example.manufacturingorder.application.query.getCustomersManufacturingOrders.GetCustomersManufacturingOrdersQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManufacturingOrderFacadeAdapter implements ManufacturingOrderFacadePort {

    private final GetCustomersManufacturingOrdersUseCase getCustomersManufacturingOrdersUseCase;

    @Override
    public List<GetManufacturingOrderResponse> getCustomerOrdersManufacturingOrders(Long customerOrderId) {
        return getCustomersManufacturingOrdersUseCase.getManufacturingOrders(new GetCustomersManufacturingOrdersQuery(customerOrderId));
    }
}
