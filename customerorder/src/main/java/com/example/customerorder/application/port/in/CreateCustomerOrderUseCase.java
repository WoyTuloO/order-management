package com.example.customerorder.application.port.in;
import com.example.customerorder.application.command.createCustomerOrder.CreateCustomerOrderCommand;

public interface CreateCustomerOrderUseCase {
    void createCustomerOrder(CreateCustomerOrderCommand command);
}
