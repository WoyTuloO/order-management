package com.example.manufacturingorder.adapter.in.rest.dto.request;

import com.example.manufacturingorder.adapter.dto.request.CancelManufacturingOrderRequest;
import com.example.manufacturingorder.application.command.cancelManufacturingOrder.CancelManufacturingOrderCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CancelManufacturingOrderRequestTest {

    @Test
    void shouldCreateCommandWithCorrectValues() {

        Long expectedOrderId = 1L;
        String expectedReason = "Production issues";
        CancelManufacturingOrderRequest request = new CancelManufacturingOrderRequest(
                expectedOrderId,
                expectedReason
        );

        CancelManufacturingOrderCommand command = request.toCommand();

        assertNotNull(command);
        assertEquals(expectedOrderId, command.manufacturingOrderId());
        assertEquals(expectedReason, command.reason());
    }


    @Test
    void shouldHandleNullReason() {
        Long expectedOrderId = 1L;
        CancelManufacturingOrderRequest request = new CancelManufacturingOrderRequest(
                expectedOrderId,
                null
        );

        CancelManufacturingOrderCommand command = request.toCommand();

        assertNotNull(command);
        assertEquals(expectedOrderId, command.manufacturingOrderId());
        assertNull(command.reason());
    }

}