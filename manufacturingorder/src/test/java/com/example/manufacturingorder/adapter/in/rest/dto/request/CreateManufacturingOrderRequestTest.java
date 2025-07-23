package com.example.manufacturingorder.adapter.in.rest.dto.request;

import com.example.manufacturingorder.application.command.createManufacturingOrder.CreateManufacturingOrderCommand;
import com.example.manufacturingorder.domain.model.valueobject.ManufacturingItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateManufacturingOrderRequestTest {

    @Test
    void shouldCreateCommandWithCorrectValues() {
        Long expectedCustomerOrderId = 1L;
        ManufacturingItem expectedItem = new ManufacturingItem(1L, 5);
        CreateManufacturingOrderRequest request = new CreateManufacturingOrderRequest(
                expectedCustomerOrderId,
                expectedItem
        );

        CreateManufacturingOrderCommand command = request.toCommand();

        assertNotNull(command);
        assertEquals(expectedCustomerOrderId, command.customerOrderId());
        assertEquals(expectedItem, command.item());
    }

    @Test
    void shouldHandleNullItem() {
        Long expectedCustomerOrderId = 1L;
        CreateManufacturingOrderRequest request = new CreateManufacturingOrderRequest(
                expectedCustomerOrderId,
                null
        );

        CreateManufacturingOrderCommand command = request.toCommand();

        assertNotNull(command);
        assertEquals(expectedCustomerOrderId, command.customerOrderId());
        assertNull(command.item());
    }


    @Test
    void shouldHandleNullCustomerOrderId() {
        ManufacturingItem item = new ManufacturingItem(1L, 1);
        CreateManufacturingOrderRequest request = new CreateManufacturingOrderRequest(
                null,
                item
        );

        CreateManufacturingOrderCommand command = request.toCommand();

        assertNotNull(command);
        assertNull(command.customerOrderId());
        assertEquals(item, command.item());
    }
}