package com.example.manufacturingorder.application.port.in;

import com.example.manufacturingorder.adapter.in.rest.dto.response.GetManufacturingOrderResponse;
import com.example.manufacturingorder.application.query.getManufacturingOrder.GetManufacturingOrderQuery;
import com.example.manufacturingorder.domain.model.aggregate.ManufacturingOrder;

public interface GetManufacturingOrderUseCase {
    GetManufacturingOrderResponse getManufacturingOrder(GetManufacturingOrderQuery query);
}
