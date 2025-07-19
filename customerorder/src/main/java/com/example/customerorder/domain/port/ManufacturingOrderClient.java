package com.example.customerorder.domain.port;

import com.example.manufacturingorder.adapter.in.rest.dto.CreateManufacturingOrderRequest;
import com.example.manufacturingorder.adapter.in.rest.dto.ManufacturingOrderResponse;

public interface ManufacturingOrderClient {
    ManufacturingOrderResponse createManufacturingOrder(CreateManufacturingOrderRequest request);
}
