package com.example.customerorder.application.query.handler;

import com.example.customerorder.adapter.dto.response.CustomerOrderItemResponse;
import com.example.customerorder.adapter.dto.response.GetCustomerOrderResponse;
import com.example.customerorder.domain.port.CustomerOrderRepositoryPort;
import com.example.customerorder.domain.port.ManufacturingOrderFacadePort;
import com.example.customerorder.application.query.GetCustomerOrderQuery;
import com.example.customerorder.domain.model.aggregate.CustomerOrder;
import com.example.customerorder.domain.model.enums.OrderItemStatus;
import com.example.customerorder.domain.model.enums.OrderStatus;
import com.example.manufacturingorder.adapter.dto.response.GetManufacturingOrderResponse;
import com.example.manufacturingorder.domain.model.enums.ManufacturingStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetCustomerOrderHandlerTest {

    @Mock
    private CustomerOrderRepositoryPort customerOrderRepositoryPort;

    @Mock
    private ManufacturingOrderFacadePort manufacturingOrdersFacadeAdapter;

    @InjectMocks
    private GetCustomerOrderHandler handler;

    @Test
    void getCustomerOrder_shouldReturnCorrectResponse() {
        Long orderId = 1L;
        var query = new GetCustomerOrderQuery(orderId);

        CustomerOrder order = new CustomerOrder( orderId, 3L, List.of(), OrderStatus.CONFIRMED, "Test order");

        List<GetManufacturingOrderResponse> manufacturingResponses = List.of(
                new GetManufacturingOrderResponse(1L, orderId, 1L, 2, ManufacturingStatus.PENDING, "Item 1"),
                new GetManufacturingOrderResponse(2L, orderId, 2L, 1, ManufacturingStatus.COMPLETED, "Item 2")
        );

        when(customerOrderRepositoryPort.findById(orderId)).thenReturn(order);
        when(manufacturingOrdersFacadeAdapter.getCustomerOrdersManufacturingOrders(orderId))
                .thenReturn(manufacturingResponses);

        GetCustomerOrderResponse response = handler.getCustomerOrder(query);

        assertNotNull(response);
        assertEquals(orderId, response.id());
        assertEquals(3L, response.customerId());
        assertEquals(OrderStatus.CONFIRMED, response.status());
        assertEquals("Test order", response.info());
        assertEquals(2, response.items().size());

        CustomerOrderItemResponse firstItem = response.items().getFirst();
        assertEquals(1L, firstItem.productId());
        assertEquals(2, firstItem.quantity());
        assertEquals(OrderItemStatus.PENDING, firstItem.status());

        CustomerOrderItemResponse secondItem = response.items().get(1);
        assertEquals(2L, secondItem.productId());
        assertEquals(1, secondItem.quantity());
        assertEquals(OrderItemStatus.COMPLETED, secondItem.status());

        verify(customerOrderRepositoryPort).findById(orderId);
        verify(manufacturingOrdersFacadeAdapter).getCustomerOrdersManufacturingOrders(orderId);
    }

    @Test
    void getCustomerOrder_shouldHandleEmptyManufacturingOrders() {
        Long orderId = 1L;
        GetCustomerOrderQuery query = new GetCustomerOrderQuery(orderId);

        CustomerOrder order = new CustomerOrder(orderId, 1L, List.of(), OrderStatus.PENDING, "Test order");

        when(customerOrderRepositoryPort.findById(orderId)).thenReturn(order);
        when(manufacturingOrdersFacadeAdapter.getCustomerOrdersManufacturingOrders(orderId))
                .thenReturn(List.of());

        GetCustomerOrderResponse response = handler.getCustomerOrder(query);

        assertNotNull(response);
        assertEquals(orderId, response.id());
        assertTrue(response.items().isEmpty());
    }


}