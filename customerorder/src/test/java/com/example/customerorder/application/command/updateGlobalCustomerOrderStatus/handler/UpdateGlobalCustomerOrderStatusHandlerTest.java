package com.example.customerorder.application.command.updateGlobalCustomerOrderStatus.handler;

import com.example.customerorder.application.command.updateGlobalCustomerOrderStatus.UpdateGlobalCustomerOrderStatusCommand;
import com.example.customerorder.application.port.out.CustomerOrderRepositoryPort;
import com.example.customerorder.application.port.out.ManufacturingOrderFacadePort;
import com.example.customerorder.domain.model.aggregate.CustomerOrder;
import com.example.customerorder.domain.model.enums.OrderStatus;
import com.example.manufacturingorder.adapter.in.rest.dto.response.GetManufacturingOrderResponse;
import com.example.manufacturingorder.domain.model.enums.ManufacturingStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateGlobalCustomerOrderStatusHandlerTest {

    @Mock
    private CustomerOrderRepositoryPort customerOrderRepositoryPort;

    @Mock
    private ManufacturingOrderFacadePort manufacturingOrdersFacadeAdapter;

    @InjectMocks
    private UpdateGlobalCustomerOrderStatusHandler handler;

    @Test
    void updateGlobalCustomerOrderStatus_shouldSetCompletedWhenAllManufacturingOrdersCompleted() {
        Long orderId = 1L;
        var command = new UpdateGlobalCustomerOrderStatusCommand(
                orderId,
                OrderStatus.CONFIRMED,
                "test"
        );

        CustomerOrder mockOrder = mock(CustomerOrder.class);
        when(mockOrder.getCustomerId()).thenReturn(100L);
        when(customerOrderRepositoryPort.findById(orderId)).thenReturn(mockOrder);

        List<GetManufacturingOrderResponse> completedOrders = List.of(
                new GetManufacturingOrderResponse(101L, 100L, 1L, 2, ManufacturingStatus.COMPLETED, "info"),
                new GetManufacturingOrderResponse(102L, 100L, 2L, 1, ManufacturingStatus.COMPLETED, "info")
        );
        when(manufacturingOrdersFacadeAdapter.getCustomerOrdersManufacturingOrders(100L))
                .thenReturn(completedOrders);

        handler.updateGlobalCustomerOrderStatus(command);

        verify(mockOrder).setStatus(OrderStatus.COMPLETED);
        verify(mockOrder, never()).setStatus(OrderStatus.CONFIRMED);
        verify(mockOrder, never()).setInfo(any());
        verify(customerOrderRepositoryPort).update(mockOrder);
    }

    @Test
    void updateGlobalCustomerOrderStatus_shouldSetCommandStatusWhenNotAllCompleted() {
        Long orderId = 1L;
        var command = new UpdateGlobalCustomerOrderStatusCommand(
                orderId,
                OrderStatus.CONFIRMED,
                "shipping info"
        );

        CustomerOrder mockOrder = mock(CustomerOrder.class);
        when(mockOrder.getCustomerId()).thenReturn(100L);
        when(customerOrderRepositoryPort.findById(orderId)).thenReturn(mockOrder);

        List<GetManufacturingOrderResponse> mixedStatusOrders = List.of(
                new GetManufacturingOrderResponse(101L, 100L, 1L, 2, ManufacturingStatus.COMPLETED, "info"),
                new GetManufacturingOrderResponse(102L, 100L, 2L, 1, ManufacturingStatus.PENDING, "info")
        );
        when(manufacturingOrdersFacadeAdapter.getCustomerOrdersManufacturingOrders(100L))
                .thenReturn(mixedStatusOrders);

        handler.updateGlobalCustomerOrderStatus(command);

        verify(mockOrder).setStatus(OrderStatus.CONFIRMED);
        verify(mockOrder).setInfo("shipping info");
        verify(customerOrderRepositoryPort).update(mockOrder);
    }

    @Test
    void updateGlobalCustomerOrderStatus_shouldHandleEmptyManufacturingOrdersList() {
        Long orderId = 1L;
        Long customerId = 1L;
        UpdateGlobalCustomerOrderStatusCommand command = new UpdateGlobalCustomerOrderStatusCommand(
                orderId,
                OrderStatus.CONFIRMED,
                "test"
        );

        CustomerOrder mockOrder = mock(CustomerOrder.class);
        when(mockOrder.getCustomerId()).thenReturn(customerId);
        when(customerOrderRepositoryPort.findById(orderId)).thenReturn(mockOrder);

        when(manufacturingOrdersFacadeAdapter.getCustomerOrdersManufacturingOrders(customerId))
                .thenReturn(List.of());

        handler.updateGlobalCustomerOrderStatus(command);

        verify(mockOrder).setStatus(OrderStatus.COMPLETED);
        verify(mockOrder, never()).setStatus(OrderStatus.CONFIRMED);
        verify(mockOrder, never()).setInfo(anyString());
        verify(customerOrderRepositoryPort).update(mockOrder);
    }

}