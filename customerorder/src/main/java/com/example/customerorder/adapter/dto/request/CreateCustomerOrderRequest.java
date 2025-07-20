package com.example.customerorder.adapter.dto.request;

import com.example.customerorder.application.command.CreateCustomerOrderCommand;
import com.example.customerorder.domain.model.valueobject.OrderItem;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateCustomerOrderRequest(
        @NotNull
        Long customerId,
        @NotEmpty
        List<OrderItem> items
) {
    public CreateCustomerOrderCommand toCommand(){
        return new CreateCustomerOrderCommand(
                customerId,
                items
        );
    }
}
