package com.example.customerorder.adapter.dto.request;

import com.example.customerorder.application.command.cancelCustomerOrder.CancelCustomerOrderCommand;

public record CancelCustomerOrderRequest(
        Long customerOrderId
) {
    public CancelCustomerOrderCommand toCommand() {
        return new CancelCustomerOrderCommand(
                customerOrderId
        );
    }

}
