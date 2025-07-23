package com.example.manufacturingorder.application.command.cancelCustomersManufacturingOrders.handler;

import com.example.manufacturingorder.application.command.cancelCustomersManufacturingOrders.CancelCustomersManufacturingOrdersCommand;
import com.example.manufacturingorder.application.port.out.ManufacturingOrderRepositoryPort;
import com.example.manufacturingorder.domain.model.aggregate.ManufacturingOrder;
import com.example.manufacturingorder.domain.model.enums.ManufacturingStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CancelCustomersManufacturingOrdersHandlerTest {

    @Mock
    private ManufacturingOrderRepositoryPort repositoryPort;

    @InjectMocks
    private CancelCustomersManufacturingOrdersHandler handler;

    @Test
    void cancelManufacturingOrders_shouldCancelOnlyPendingOrders() {
        Long customerOrderId = 1L;
        String reason = "Customer request";
        CancelCustomersManufacturingOrdersCommand command = new CancelCustomersManufacturingOrdersCommand(customerOrderId, reason);

        ManufacturingOrder pendingOrder = mock(ManufacturingOrder.class);
        when(pendingOrder.getStatus()).thenReturn(ManufacturingStatus.PENDING);

        ManufacturingOrder completedOrder = mock(ManufacturingOrder.class);
        when(completedOrder.getStatus()).thenReturn(ManufacturingStatus.COMPLETED);

        when(repositoryPort.findByCustomerOrderId(customerOrderId))
                .thenReturn(List.of(pendingOrder, completedOrder));

        handler.cancelManufacturingOrders(command);

        verify(pendingOrder).markAsCancelled(reason);
        verify(completedOrder, never()).markAsCancelled(any());
        verify(repositoryPort).save(List.of(pendingOrder, completedOrder));
    }

    @Test
    void cancelManufacturingOrders_shouldNotSaveWhenNoOrdersFound() {
        Long customerOrderId = 2L;
        CancelCustomersManufacturingOrdersCommand command = new CancelCustomersManufacturingOrdersCommand(customerOrderId, "reason");

        when(repositoryPort.findByCustomerOrderId(customerOrderId))
                .thenReturn(List.of());

        handler.cancelManufacturingOrders(command);

        verify(repositoryPort, never()).save(anyList());
    }

    @Test
    void cancelManufacturingOrders_shouldHandleEmptyReason() {
        Long customerOrderId = 3L;
        CancelCustomersManufacturingOrdersCommand command = new CancelCustomersManufacturingOrdersCommand(customerOrderId, "");

        ManufacturingOrder order = mock(ManufacturingOrder.class);
        when(order.getStatus()).thenReturn(ManufacturingStatus.PENDING);

        when(repositoryPort.findByCustomerOrderId(customerOrderId))
                .thenReturn(List.of(order));

        handler.cancelManufacturingOrders(command);

        verify(order).markAsCancelled("");
        verify(repositoryPort).save(List.of(order));
    }

    @Test
    void cancelManufacturingOrders_shouldNotModifyNonPendingOrders() {
        Long customerOrderId = 4L;
        CancelCustomersManufacturingOrdersCommand command = new CancelCustomersManufacturingOrdersCommand(customerOrderId, "reason");

        ManufacturingOrder inProgressOrder = mock(ManufacturingOrder.class);
        when(inProgressOrder.getStatus()).thenReturn(ManufacturingStatus.IN_PROGRESS);

        when(repositoryPort.findByCustomerOrderId(customerOrderId))
                .thenReturn(List.of(inProgressOrder));

        handler.cancelManufacturingOrders(command);

        verify(inProgressOrder, never()).markAsCancelled(any());
        verify(repositoryPort).save(List.of(inProgressOrder));
    }

}