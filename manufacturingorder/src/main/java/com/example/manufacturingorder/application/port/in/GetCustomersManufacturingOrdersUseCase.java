package com.example.manufacturingorder.application.port.in;

import com.example.manufacturingorder.adapter.dto.response.GetManufacturingOrderResponse;
import com.example.manufacturingorder.application.query.getCustomersManufacturingOrders.GetCustomersManufacturingOrdersQuery;


import java.util.List;

public interface GetCustomersManufacturingOrdersUseCase {
    List<GetManufacturingOrderResponse> getManufacturingOrders(GetCustomersManufacturingOrdersQuery query);
}
