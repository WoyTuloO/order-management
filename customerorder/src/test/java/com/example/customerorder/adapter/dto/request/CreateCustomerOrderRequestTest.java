package com.example.customerorder.adapter.dto.request;

import com.example.customerorder.application.command.createCustomerOrder.CreateCustomerOrderCommand;
import com.example.customerorder.domain.model.valueobject.OrderItem;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreateCustomerOrderRequestTest {

    @Test
    void toCommand_shouldReturnCommand() {

        Long customerId = 123L;
        List<OrderItem> items = List.of(new OrderItem(1L, 1),new OrderItem(2L, 2));
        CreateCustomerOrderRequest request = new CreateCustomerOrderRequest(customerId, items);

        CreateCustomerOrderCommand command = request.toCommand();

        assertEquals(customerId, command.customerId());
        assertEquals(items, command.items());
    }
}