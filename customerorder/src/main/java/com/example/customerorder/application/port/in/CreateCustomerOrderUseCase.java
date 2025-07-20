package com.example.customerorder.application.port.in;
import com.example.customerorder.application.command.CreateCustomerOrderCommand;

public interface CreateCustomerOrderUseCase {
    void createCustomerOrder(CreateCustomerOrderCommand command);
}
