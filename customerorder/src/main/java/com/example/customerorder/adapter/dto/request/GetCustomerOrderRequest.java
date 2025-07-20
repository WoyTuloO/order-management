package com.example.customerorder.adapter.dto.request;

import com.example.customerorder.application.query.GetCustomerOrderQuery;
import jakarta.validation.constraints.NotBlank;

public record GetCustomerOrderRequest(@NotBlank Long orderId) {
    public GetCustomerOrderQuery toCommand() {
        return new GetCustomerOrderQuery(orderId);
    }
}
