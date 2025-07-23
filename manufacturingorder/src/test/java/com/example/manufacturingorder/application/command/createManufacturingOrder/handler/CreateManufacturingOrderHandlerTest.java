package com.example.manufacturingorder.application.command.createManufacturingOrder.handler;

import com.example.manufacturingorder.application.command.createManufacturingOrder.CreateManufacturingOrderCommand;
import com.example.manufacturingorder.domain.port.ManufacturingOrderRepositoryPort;
import com.example.manufacturingorder.domain.model.aggregate.ManufacturingOrder;
import com.example.manufacturingorder.domain.model.valueobject.ManufacturingItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateManufacturingOrderHandlerTest {

    @Mock
    private ManufacturingOrderRepositoryPort repositoryPort;

    @InjectMocks
    private CreateManufacturingOrderHandler handler;

    @Test
    void createManufacturingOrder_shouldCreateAndSaveNewOrder() {
        Long customerOrderId = 1L;
        ManufacturingItem item = new ManufacturingItem(1L, 5);
        CreateManufacturingOrderCommand command = new CreateManufacturingOrderCommand(customerOrderId, item);

        handler.createManufacturingOrder(command);

        ArgumentCaptor<ManufacturingOrder> orderCaptor = ArgumentCaptor.forClass(ManufacturingOrder.class);
        verify(repositoryPort).save(orderCaptor.capture());

        ManufacturingOrder savedOrder = orderCaptor.getValue();
        assertEquals(customerOrderId, savedOrder.getCustomerOrderId());
        assertEquals(item.componentId(), savedOrder.getProductId());
        assertEquals(item.requiredQuantity(), savedOrder.getRequiredQuantity());

    }

    @Test
    void createManufacturingOrder_shouldHandleNullCustomerOrderId() {
        ManufacturingItem item = new ManufacturingItem(1L, 3);
        CreateManufacturingOrderCommand command = new CreateManufacturingOrderCommand(null, item);

        handler.createManufacturingOrder(command);

        ArgumentCaptor<ManufacturingOrder> orderCaptor = ArgumentCaptor.forClass(ManufacturingOrder.class);
        verify(repositoryPort).save(orderCaptor.capture());

        assertNull(orderCaptor.getValue().getCustomerOrderId());
    }


}