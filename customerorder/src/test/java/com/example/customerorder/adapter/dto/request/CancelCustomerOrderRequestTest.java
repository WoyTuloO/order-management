package com.example.customerorder.adapter.dto.request;

import com.example.customerorder.application.command.cancelCustomerOrder.CancelCustomerOrderCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CancelCustomerOrderRequestTest {

    @Test
    void toCommand_shouldReturnCommand() {
        Long expectedOrderId = 123L;
        CancelCustomerOrderRequest request = new CancelCustomerOrderRequest(expectedOrderId);
        CancelCustomerOrderCommand command = request.toCommand();
        assertEquals(expectedOrderId, command.customerOrderId());
    }
}