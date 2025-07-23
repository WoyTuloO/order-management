package com.example.manufacturingorder.adapter.in.rest.dto.request;

import com.example.manufacturingorder.adapter.dto.request.CreateMultipleManufacturingOrdersRequest;
import com.example.manufacturingorder.application.command.createManufacturingOrders.CreateManufacturingOrdersCommand;
import com.example.manufacturingorder.domain.model.valueobject.ManufacturingItem;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreateMultipleManufacturingOrdersRequestTest {
    @Test
    void shouldCreateCommandWithCorrectValues() {
        Long expectedCustomerOrderId = 1L;
        List<ManufacturingItem> expectedItems = List.of(
                new ManufacturingItem(1L, 5),
                new ManufacturingItem(2L, 10)
        );
        CreateMultipleManufacturingOrdersRequest request = new CreateMultipleManufacturingOrdersRequest(
                expectedCustomerOrderId,
                expectedItems
        );

        CreateManufacturingOrdersCommand command = request.toCommand();

        assertNotNull(command);
        assertEquals(expectedCustomerOrderId, command.customerOrderId());
        assertEquals(expectedItems, command.items());
    }

    @Test
    void shouldHandleNullCustomerOrderId() {
        List<ManufacturingItem> items = List.of(new ManufacturingItem(1L, 1));
        CreateMultipleManufacturingOrdersRequest request = new CreateMultipleManufacturingOrdersRequest(
                null,
                items
        );

        CreateManufacturingOrdersCommand command = request.toCommand();

        assertNull(command.customerOrderId(), "Customer order ID should be null");
        assertEquals(items, command.items(), "Items should match");
    }

}