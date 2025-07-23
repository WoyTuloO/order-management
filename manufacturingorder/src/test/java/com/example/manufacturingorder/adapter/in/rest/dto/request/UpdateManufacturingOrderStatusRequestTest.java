package com.example.manufacturingorder.adapter.in.rest.dto.request;

import com.example.manufacturingorder.application.command.updateManufacturingOrderStatus.UpdateManufacturingOrderStatusCommand;
import com.example.manufacturingorder.domain.model.enums.ManufacturingStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateManufacturingOrderStatusRequestTest {
    @Test
    void shouldCreateCommandWithCorrectValues() {

        Long expectedOrderId = 1L;
        ManufacturingStatus expectedStatus = ManufacturingStatus.IN_PROGRESS;
        String expectedInfo = "Starting production";

        UpdateManufacturingOrderStatusRequest request = new UpdateManufacturingOrderStatusRequest(
                expectedOrderId,
                expectedStatus,
                expectedInfo
        );

        UpdateManufacturingOrderStatusCommand command = request.toCommand();

        assertNotNull(command);
        assertEquals(expectedOrderId, command.manufacturingOrderId());
        assertEquals(expectedStatus, command.newStatus());
        assertEquals(expectedInfo, command.info());
    }

    @Test
    void shouldHandleNullValues() {

        UpdateManufacturingOrderStatusRequest request = new UpdateManufacturingOrderStatusRequest(
                null,
                null,
                null
        );

        UpdateManufacturingOrderStatusCommand command = request.toCommand();

        assertNull(command.manufacturingOrderId());
        assertNull(command.newStatus());
        assertNull(command.info());
    }

}