package com.example.manufacturingorder.domain.event;

import com.example.manufacturingorder.application.command.cancelCustomersManufacturingOrders.CancelCustomersManufacturingOrdersCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CancelManufacturingOrdersEventTest {

    @Test
    void getCommand_shouldReturnCorrectCommand() {
        Long customerOrderId = 1L;
        String reason = "Test cancellation";
        CancelManufacturingOrdersEvent event = new CancelManufacturingOrdersEvent(customerOrderId, reason);

        CancelCustomersManufacturingOrdersCommand command = event.getCommand();

        assertEquals(customerOrderId, command.customerOrderId());
        assertEquals(reason, command.reason());
    }

    @Test
    void getCommand_shouldHandleEmptyReason() {
        CancelManufacturingOrdersEvent event = new CancelManufacturingOrdersEvent(1L, "");

        CancelCustomersManufacturingOrdersCommand command = event.getCommand();

        assertEquals(1L, command.customerOrderId());
        assertEquals("", command.reason());
    }


}