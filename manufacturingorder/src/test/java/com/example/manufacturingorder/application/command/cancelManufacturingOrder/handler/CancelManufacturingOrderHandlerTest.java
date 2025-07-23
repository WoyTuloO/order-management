package com.example.manufacturingorder.application.command.cancelManufacturingOrder.handler;

import com.example.manufacturingorder.application.command.cancelManufacturingOrder.CancelManufacturingOrderCommand;
import com.example.manufacturingorder.application.port.out.ManufacturingOrderRepositoryPort;
import com.example.manufacturingorder.config.exceptions.ManufacturingResourceNotFound;
import com.example.manufacturingorder.domain.model.aggregate.ManufacturingOrder;
import com.example.manufacturingorder.domain.model.enums.ManufacturingStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CancelManufacturingOrderHandlerTest {

    @Mock
    private ManufacturingOrderRepositoryPort repositoryPort;

    @InjectMocks
    private CancelManufacturingOrderHandler handler;

    @Test
    void cancelManufacturingOrder_shouldMarkOrderAsCancelled() {
        Long orderId = 1L;
        String reason = "Test cancellation";
        var command = new CancelManufacturingOrderCommand(orderId, reason);

        ManufacturingOrder order = mock(ManufacturingOrder.class);
        when(repositoryPort.findById(orderId)).thenReturn(order);

        handler.cancelManufacturingOrder(command);

        verify(order).markAsCancelled(reason);
        verify(repositoryPort).update(order);
    }

    @Test
    void cancelManufacturingOrder_shouldUpdateRepository() {
        Long orderId = 2L;
        var command = new CancelManufacturingOrderCommand(orderId, "Reason");

        ManufacturingOrder order = new ManufacturingOrder(orderId, 1L, 1L, 5,
                ManufacturingStatus.PENDING, "Test order");
        when(repositoryPort.findById(orderId)).thenReturn(order);

        handler.cancelManufacturingOrder(command);

        assertEquals(ManufacturingStatus.CANCELLED, order.getStatus());
        verify(repositoryPort).update(order);
    }

    @Test
    void cancelManufacturingOrder_shouldHandleEmptyReason() {
        Long orderId = 3L;
        var command = new CancelManufacturingOrderCommand(orderId, "");

        ManufacturingOrder order = mock(ManufacturingOrder.class);
        when(repositoryPort.findById(orderId)).thenReturn(order);

        handler.cancelManufacturingOrder(command);

        verify(order).markAsCancelled("");
        verify(repositoryPort).update(order);
    }

    @Test
    void cancelManufacturingOrder_shouldHandleNullReason() {
        Long orderId = 4L;
        var command = new CancelManufacturingOrderCommand(orderId, null);

        ManufacturingOrder order = mock(ManufacturingOrder.class);
        when(repositoryPort.findById(orderId)).thenReturn(order);

        handler.cancelManufacturingOrder(command);

        verify(order).markAsCancelled(null);
        verify(repositoryPort).update(order);
    }

    @Test
    void cancelManufacturingOrder_shouldThrowWhenOrderNotFound() {
        Long nonExistentOrderId = 999L;
        var command = new CancelManufacturingOrderCommand(nonExistentOrderId, "Reason");

        when(repositoryPort.findById(nonExistentOrderId))
                .thenThrow(new ManufacturingResourceNotFound("Order not found"));

        assertThrows(ManufacturingResourceNotFound.class, () ->
                handler.cancelManufacturingOrder(command));
        verify(repositoryPort, never()).update(any());
    }
}