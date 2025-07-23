package com.example.customerorder.application.port.in;

import com.example.customerorder.application.command.cancelCustomerOrder.CancelCustomerOrderCommand;

public interface CancelCustomerOrderUseCase {
    void cancelCustomerOrder(CancelCustomerOrderCommand cancelCustomerOrderCommand);
}
