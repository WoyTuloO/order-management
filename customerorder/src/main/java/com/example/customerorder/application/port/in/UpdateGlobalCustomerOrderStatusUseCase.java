package com.example.customerorder.application.port.in;

import com.example.customerorder.application.command.UpdateGlobalCustomerOrderStatus.UpdateGlobalCustomerOrderStatusCommand;

public interface UpdateGlobalCustomerOrderStatusUseCase {
    void updateGlobalCustomerOrderStatus(UpdateGlobalCustomerOrderStatusCommand command);
}
