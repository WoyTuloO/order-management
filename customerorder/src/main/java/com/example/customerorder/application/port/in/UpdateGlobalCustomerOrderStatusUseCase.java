package com.example.customerorder.application.port.in;

import com.example.customerorder.application.command.updateGlobalCustomerOrderStatus.UpdateGlobalCustomerOrderStatusCommand;

public interface UpdateGlobalCustomerOrderStatusUseCase {
    void updateGlobalCustomerOrderStatus(UpdateGlobalCustomerOrderStatusCommand command);
}
