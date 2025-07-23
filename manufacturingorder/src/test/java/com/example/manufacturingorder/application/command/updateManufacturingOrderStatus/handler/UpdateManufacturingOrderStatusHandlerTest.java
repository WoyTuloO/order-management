package com.example.manufacturingorder.application.command.updateManufacturingOrderStatus.handler;

import com.example.manufacturingorder.application.command.updateManufacturingOrderStatus.UpdateManufacturingOrderStatusCommand;
import com.example.manufacturingorder.domain.port.ManufacturingOrderRepositoryPort;
import com.example.manufacturingorder.config.exceptions.CannotUpdateCompletedOrderException;
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
class UpdateManufacturingOrderStatusHandlerTest {

    @Mock
    private ManufacturingOrderRepositoryPort orderRepository;

    @InjectMocks
    private UpdateManufacturingOrderStatusHandler handler;

    @Test
    void updateManufacturingOrderStatus_shouldUpdateStatusWhenNotCompleted() {
        Long orderId = 1L;
        UpdateManufacturingOrderStatusCommand command = new UpdateManufacturingOrderStatusCommand(
                orderId, ManufacturingStatus.IN_PROGRESS, "Starting production");

        ManufacturingOrder order = mock(ManufacturingOrder.class);
        when(order.getStatus()).thenReturn(ManufacturingStatus.PENDING);
        when(orderRepository.findById(orderId)).thenReturn(order);

        handler.updateManufacturingOrderStatus(command);

        verify(order).updateStatus(command.newStatus(), command.info());
        verify(orderRepository).update(order);
    }

    @Test
    void updateManufacturingOrderStatus_shouldThrowWhenOrderIsCompleted() {
        Long orderId = 2L;
        UpdateManufacturingOrderStatusCommand command = new UpdateManufacturingOrderStatusCommand(
                orderId, ManufacturingStatus.CANCELLED, "Cancelling");

        ManufacturingOrder order = mock(ManufacturingOrder.class);
        when(order.getStatus()).thenReturn(ManufacturingStatus.COMPLETED);
        when(orderRepository.findById(orderId)).thenReturn(order);

        assertThrows(CannotUpdateCompletedOrderException.class, () ->
                handler.updateManufacturingOrderStatus(command));

        verify(order, never()).updateStatus(any(), any());
        verify(orderRepository, never()).update(any());
    }

    @Test
    void updateManufacturingOrderStatus_shouldHandleNullInfo() {
        Long orderId = 3L;
        UpdateManufacturingOrderStatusCommand command = new UpdateManufacturingOrderStatusCommand(
                orderId, ManufacturingStatus.IN_PROGRESS, null);

        ManufacturingOrder order = mock(ManufacturingOrder.class);
        when(order.getStatus()).thenReturn(ManufacturingStatus.PENDING);
        when(orderRepository.findById(orderId)).thenReturn(order);

        handler.updateManufacturingOrderStatus(command);

        verify(order).updateStatus(command.newStatus(), null);
        verify(orderRepository).update(order);
    }

    @Test
    void updateManufacturingOrderStatus_shouldHandleSameStatusUpdate() {
        Long orderId = 4L;
        UpdateManufacturingOrderStatusCommand command = new UpdateManufacturingOrderStatusCommand(
                orderId, ManufacturingStatus.PENDING, "No change");

        ManufacturingOrder order = mock(ManufacturingOrder.class);
        when(order.getStatus()).thenReturn(ManufacturingStatus.PENDING);
        when(orderRepository.findById(orderId)).thenReturn(order);

        handler.updateManufacturingOrderStatus(command);

        verify(order).updateStatus(command.newStatus(), command.info());
        verify(orderRepository).update(order);
    }

    @Test
    void updateManufacturingOrderStatus_shouldNotUpdateWhenOrderNotFound() {
        Long nonExistentOrderId = 999L;
        UpdateManufacturingOrderStatusCommand command = new UpdateManufacturingOrderStatusCommand(
                nonExistentOrderId, ManufacturingStatus.IN_PROGRESS, "Test");

        when(orderRepository.findById(nonExistentOrderId))
                .thenThrow(new ManufacturingResourceNotFound("Order not found"));

        assertThrows(ManufacturingResourceNotFound.class, () ->
                handler.updateManufacturingOrderStatus(command));

        verify(orderRepository, never()).update(any());
    }
}