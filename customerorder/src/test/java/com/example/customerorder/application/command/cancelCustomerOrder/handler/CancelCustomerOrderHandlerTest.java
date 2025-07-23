package com.example.customerorder.application.command.cancelCustomerOrder.handler;

import com.example.customerorder.adapter.out.external.ManufacturingOrderFacadeAdapter;
import com.example.customerorder.application.command.cancelCustomerOrder.CancelCustomerOrderCommand;
import com.example.customerorder.application.port.out.CustomerOrderRepositoryPort;
import com.example.customerorder.domain.model.aggregate.CustomerOrder;
import com.example.manufacturingorder.adapter.dto.response.GetManufacturingOrderResponse;
import com.example.manufacturingorder.domain.event.CancelManufacturingOrdersEvent;
import com.example.manufacturingorder.domain.model.enums.ManufacturingStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CancelCustomerOrderHandlerTest {

    @Mock
    private CustomerOrderRepositoryPort customerOrderRepository;

    @Mock
    private ManufacturingOrderFacadeAdapter manufacturingOrdersFacadeAdapter;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private CancelCustomerOrderHandler handler;

    @Test
    void cancelCustomerOrder_shouldCancelWhenAllManufacturingOrdersAreCancelable() {
        Long orderId = 1L;
        CancelCustomerOrderCommand command = new CancelCustomerOrderCommand(orderId);

        when(manufacturingOrdersFacadeAdapter.getCustomerOrdersManufacturingOrders(orderId))
                .thenReturn(List.of(
                        new GetManufacturingOrderResponse(1L, 2L,1L, 1, ManufacturingStatus.PENDING, "info"),
                        new GetManufacturingOrderResponse(2L, 3L,2L, 2, ManufacturingStatus.CANCELLED, "info")
                ));

        CustomerOrder mockOrder = mock(CustomerOrder.class);
        when(customerOrderRepository.findById(orderId)).thenReturn(mockOrder);

        handler.cancelCustomerOrder(command);

        verify(mockOrder).cancel("Order cancelled by customer");
        verify(customerOrderRepository).save(mockOrder);
        verify(eventPublisher).publishEvent(any(CancelManufacturingOrdersEvent.class));
    }

    @Test
    void cancelCustomerOrder_shouldNotCancelWhenSomeManufacturingOrdersAreNotCancelable() {
        Long orderId = 2L;
        CancelCustomerOrderCommand command = new CancelCustomerOrderCommand(orderId);

        when(manufacturingOrdersFacadeAdapter.getCustomerOrdersManufacturingOrders(orderId))
                .thenReturn(List.of(
                        new GetManufacturingOrderResponse(1L, 2L,1L, 1, ManufacturingStatus.PENDING, "info"),
                        new GetManufacturingOrderResponse(2L, 3L,2L, 2, ManufacturingStatus.IN_PROGRESS, "info")
                ));

        handler.cancelCustomerOrder(command);

        verify(customerOrderRepository, never()).findById(any());
        verify(customerOrderRepository, never()).save(any());
        verify(eventPublisher).publishEvent(any(CancelManufacturingOrdersEvent.class));
    }

    @Test
    void cancelCustomerOrder_shouldPublishEventEvenWhenNotCancelable() {
        Long orderId = 3L;
        CancelCustomerOrderCommand command = new CancelCustomerOrderCommand(orderId);

        when(manufacturingOrdersFacadeAdapter.getCustomerOrdersManufacturingOrders(orderId))
                .thenReturn(List.of(
                        new GetManufacturingOrderResponse(101L, 2L,1L, 1, ManufacturingStatus.COMPLETED, "info")
                ));

        handler.cancelCustomerOrder(command);
        verify(eventPublisher).publishEvent(any(CancelManufacturingOrdersEvent.class));
    }

    @Test
    void cancelCustomerOrder_shouldHandleEmptyManufacturingOrdersList() {
        Long orderId = 4L;
        CancelCustomerOrderCommand command = new CancelCustomerOrderCommand(orderId);

        when(manufacturingOrdersFacadeAdapter.getCustomerOrdersManufacturingOrders(orderId))
                .thenReturn(List.of());

        CustomerOrder mockOrder = mock(CustomerOrder.class);
        when(customerOrderRepository.findById(orderId)).thenReturn(mockOrder);

        handler.cancelCustomerOrder(command);

        verify(mockOrder).cancel("Order cancelled by customer");
        verify(customerOrderRepository).save(mockOrder);
        verify(eventPublisher).publishEvent(any(CancelManufacturingOrdersEvent.class));
    }

}