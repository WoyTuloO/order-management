package com.example.customerorder.adapter.dto.response;

import com.example.customerorder.domain.model.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public record GetCustomerOrderResponse(
        long id,
        Long customerId,
        List<CustomerOrderItemResponse> items,
        OrderStatus status,
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        String info
) {
}
