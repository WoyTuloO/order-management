package com.example.manufacturingorder.domain.event;

import com.example.manufacturingorder.application.command.createManufacturingOrders.CreateManufacturingOrdersCommand;
import com.example.manufacturingorder.domain.model.valueobject.ManufacturingItem;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreateManufacturingOrdersEventTest {

    @Test
    void getCommand_shouldReturnCorrectCommand() {
        Long customerOrderId = 1L;
        List<ManufacturingItem> items = List.of(
                new ManufacturingItem(1L, 5),
                new ManufacturingItem(2L, 10)
        );
        CreateManufacturingOrdersEvent event = new CreateManufacturingOrdersEvent(customerOrderId, items);

        CreateManufacturingOrdersCommand command = event.getCommand();

        assertEquals(customerOrderId, command.customerOrderId());
        assertEquals(2, command.items().size());
        assertEquals(1L, command.items().getFirst().componentId());
        assertEquals(5, command.items().getFirst().requiredQuantity());

        assertEquals(2L, command.items().get(1).componentId());
        assertEquals(10, command.items().get(1).requiredQuantity());
    }

    @Test
    void getCommand_shouldHandleEmptyItemsList() {
        CreateManufacturingOrdersEvent event = new CreateManufacturingOrdersEvent(1L, List.of());

        CreateManufacturingOrdersCommand command = event.getCommand();

        assertEquals(1L, command.customerOrderId());
        assertTrue(command.items().isEmpty());
    }

}