package com.example.customerorder.domain.port;

import com.example.manufacturingorder.adapter.dto.response.GetManufacturingOrderResponse;

import java.util.List;

public interface ManufacturingOrderFacadePort {
    List<GetManufacturingOrderResponse>  getCustomerOrdersManufacturingOrders(Long customerOrderId);

}
