package com.example.customerorder.adapter.out.external;

import com.example.customerorder.domain.port.ManufacturingOrderClient;
import com.example.manufacturingorder.adapter.in.rest.dto.CreateManufacturingOrderRequest;
import com.example.manufacturingorder.adapter.in.rest.dto.ManufacturingOrderResponse;
import org.springframework.stereotype.Component;

@Component
public class ManufacturingOrderRestClient implements ManufacturingOrderClient {


    @Override
    public ManufacturingOrderResponse createManufacturingOrder(CreateManufacturingOrderRequest request) {
        return null;
    }
}
