package com.example.manufacturingorder.application.query.getManufacturingOrder.handler;

import com.example.manufacturingorder.adapter.in.rest.dto.response.GetManufacturingOrderResponse;
import com.example.manufacturingorder.application.port.in.GetManufacturingOrderUseCase;
import com.example.manufacturingorder.application.port.out.ManufacturingOrderRepositoryPort;
import com.example.manufacturingorder.application.query.getManufacturingOrder.GetManufacturingOrderQuery;
import com.example.manufacturingorder.domain.model.aggregate.ManufacturingOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetManufacturingOrderHandler implements GetManufacturingOrderUseCase {

    private final ManufacturingOrderRepositoryPort manufacturingOrderRepositoryPort;

    @Override
    public GetManufacturingOrderResponse getManufacturingOrder(GetManufacturingOrderQuery query) {
        ManufacturingOrder manufacturingOrder = manufacturingOrderRepositoryPort.findById(query.id());
        return GetManufacturingOrderResponse.fromDomain(manufacturingOrder);
    }
}
