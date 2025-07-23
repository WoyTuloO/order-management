package com.example.manufacturingorder.application.port.in;

import com.example.manufacturingorder.adapter.dto.response.GetManufacturingOrderResponse;
import com.example.manufacturingorder.application.query.getManufacturingOrder.GetManufacturingOrderQuery;

public interface GetManufacturingOrderUseCase {
    GetManufacturingOrderResponse getManufacturingOrder(GetManufacturingOrderQuery query);
}
