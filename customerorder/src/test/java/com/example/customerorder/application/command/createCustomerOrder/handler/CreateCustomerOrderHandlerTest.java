package com.example.customerorder.application.command.createCustomerOrder.handler;

import com.example.customerorder.application.command.createCustomerOrder.CreateCustomerOrderCommand;
import com.example.customerorder.domain.port.CustomerOrderRepositoryPort;
import com.example.customerorder.domain.model.aggregate.CustomerOrder;
import com.example.customerorder.domain.model.valueobject.OrderItem;
import com.example.manufacturingorder.domain.event.CreateManufacturingOrdersEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateCustomerOrderHandlerTest {

    @Mock
    private CustomerOrderRepositoryPort customerOrderRepositoryPort;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private CreateCustomerOrderHandler handler;

    @Test
    void createCustomerOrder_shouldCreateOrderAndPublishEvent() {
        Long customerId = 1L;
        List<OrderItem> items = List.of(
                new OrderItem(1L, 2),
                new OrderItem(2L, 1)
        );
        var command = new CreateCustomerOrderCommand(customerId, items);

        Long savedOrderId = 1L;

        when(customerOrderRepositoryPort.save(any(CustomerOrder.class)))
                .thenAnswer(invocation -> {
                    CustomerOrder order = invocation.getArgument(0);
                    order.setId(savedOrderId);
                    return savedOrderId;
                });

        handler.createCustomerOrder(command);

        ArgumentCaptor<CustomerOrder> orderCaptor = ArgumentCaptor.forClass(CustomerOrder.class);
        verify(customerOrderRepositoryPort, times(2)).save(orderCaptor.capture());

        List<CustomerOrder> savedOrders = orderCaptor.getAllValues();

        assertEquals(items, savedOrders.getFirst().getItems());
        assertEquals(1L, savedOrders.getFirst().getId());

        ArgumentCaptor<CreateManufacturingOrdersEvent> eventCaptor =
                ArgumentCaptor.forClass(CreateManufacturingOrdersEvent.class);
        verify(eventPublisher).publishEvent(eventCaptor.capture());

        CreateManufacturingOrdersEvent publishedEvent = eventCaptor.getValue();
        assertEquals(savedOrderId, publishedEvent.customerOrderId());
        assertEquals(2, publishedEvent.items().size());
    }

    @Test
    void createCustomerOrder_shouldHandleEmptyItemsList() {
        Long customerId = 1L;
        var command = new CreateCustomerOrderCommand(customerId, List.of());

        assertThrows(IllegalArgumentException.class, () -> handler.createCustomerOrder(command));
    }

    @Test
    void createCustomerOrder_shouldHandleEmptyManufacturingOrdersResponse() {
        Long customerId = 1L;
        List<OrderItem> items = List.of(new OrderItem(1L, 1));
        var command = new CreateCustomerOrderCommand(customerId, items);

        Long savedOrderId = 1L;

        when(customerOrderRepositoryPort.save(any(CustomerOrder.class)))
                .thenAnswer(invocation -> {
                    CustomerOrder order = invocation.getArgument(0);
                    order.setId(savedOrderId);
                    return savedOrderId;
                });

        handler.createCustomerOrder(command);

        ArgumentCaptor<CustomerOrder> orderCaptor = ArgumentCaptor.forClass(CustomerOrder.class);
        verify(customerOrderRepositoryPort, times(2)).save(orderCaptor.capture());
    }
}