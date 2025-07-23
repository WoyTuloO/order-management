package com.example.customerorder.adapter.dto.response;

import com.example.customerorder.domain.model.enums.OrderItemStatus;
import com.example.manufacturingorder.adapter.dto.response.GetManufacturingOrderResponse;
import com.fasterxml.jackson.annotation.JsonInclude;

public record CustomerOrderItemResponse(
        Long productId,
        int quantity,
        OrderItemStatus status,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String info
) {
    public static CustomerOrderItemResponse fromManufacturingOrderResponse(GetManufacturingOrderResponse getManufacturingOrderResponse) {
        return new CustomerOrderItemResponse(
                getManufacturingOrderResponse.productId(),
                getManufacturingOrderResponse.quantity(),
                OrderItemStatus.valueOf(getManufacturingOrderResponse.status().name()),
                getManufacturingOrderResponse.info()
        );
    }
}
