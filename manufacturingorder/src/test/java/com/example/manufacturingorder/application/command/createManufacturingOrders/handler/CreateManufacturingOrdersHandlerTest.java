package com.example.manufacturingorder.application.command.createManufacturingOrders.handler;


import com.example.manufacturingorder.application.command.createManufacturingOrders.CreateManufacturingOrdersCommand;
import com.example.manufacturingorder.domain.port.ManufacturingOrderRepositoryPort;
import com.example.manufacturingorder.domain.model.aggregate.ManufacturingOrder;
import com.example.manufacturingorder.domain.model.enums.ManufacturingStatus;
import com.example.manufacturingorder.domain.model.valueobject.ManufacturingItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateManufacturingOrdersHandlerTest {

    @Mock
    private ManufacturingOrderRepositoryPort repositoryPort;

    @InjectMocks
    private CreateManufacturingOrdersHandler handler;

    @Test
    void createManufacturingOrders_shouldCreateAndSaveMultipleOrders() {
        Long customerOrderId = 1L;
        List<ManufacturingItem> items = List.of(
                new ManufacturingItem(1L, 5),
                new ManufacturingItem(2L, 10)
        );
        CreateManufacturingOrdersCommand command = new CreateManufacturingOrdersCommand(customerOrderId, items);

        handler.createManufacturingOrders(command);

        ArgumentCaptor<List<ManufacturingOrder>> ordersCaptor = ArgumentCaptor.forClass(List.class);
        verify(repositoryPort).save(ordersCaptor.capture());

        List<ManufacturingOrder> savedOrders = ordersCaptor.getValue();
        assertEquals(2, savedOrders.size());
        assertEquals(customerOrderId, savedOrders.getFirst().getCustomerOrderId());
        assertEquals(1L, savedOrders.getFirst().getProductId());
        assertEquals(ManufacturingStatus.PENDING, savedOrders.getFirst().getStatus());

    }

    @Test
    void createManufacturingOrders_shouldHandleNullCustomerOrderId() {
        var command = new CreateManufacturingOrdersCommand(null,
                List.of(new ManufacturingItem(1L, 1)));

        handler.createManufacturingOrders(command);

        ArgumentCaptor<List<ManufacturingOrder>> ordersCaptor = ArgumentCaptor.forClass(List.class);
        verify(repositoryPort).save(ordersCaptor.capture());

        assertNull(ordersCaptor.getValue().getFirst().getCustomerOrderId());
    }


}