package com.example.manufacturingorder.application.query.getCustomersManufacturingOrders.handler;

import com.example.manufacturingorder.adapter.dto.response.GetManufacturingOrderResponse;
import com.example.manufacturingorder.domain.port.ManufacturingOrderRepositoryPort;
import com.example.manufacturingorder.application.query.getCustomersManufacturingOrders.GetCustomersManufacturingOrdersQuery;
import com.example.manufacturingorder.domain.model.aggregate.ManufacturingOrder;
import com.example.manufacturingorder.domain.model.enums.ManufacturingStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetCustomersManufacturingOrdersHandlerTest {

    @Mock
    private ManufacturingOrderRepositoryPort repositoryPort;

    @InjectMocks
    private GetCustomersManufacturingOrdersHandler handler;

    @Test
    void getManufacturingOrders_shouldReturnMappedResponses() {
        Long customerOrderId = 1L;
        GetCustomersManufacturingOrdersQuery query = new GetCustomersManufacturingOrdersQuery(customerOrderId);

        ManufacturingOrder order1 = new ManufacturingOrder(1L, customerOrderId, 1L, 5,
                ManufacturingStatus.PENDING, "Order 1");
        ManufacturingOrder order2 = new ManufacturingOrder(2L, customerOrderId, 2L, 10,
                ManufacturingStatus.IN_PROGRESS, "Order 2");

        when(repositoryPort.findByCustomerOrderId(customerOrderId))
                .thenReturn(List.of(order1, order2));

        List<GetManufacturingOrderResponse> responses = handler.getManufacturingOrders(query);

        assertEquals(2, responses.size());

        assertEquals(1L, responses.getFirst().id());
        assertEquals(customerOrderId, responses.getFirst().customerOrderId());
        assertEquals(1L, responses.getFirst().productId());
        assertEquals(5, responses.getFirst().quantity());
        assertEquals(ManufacturingStatus.PENDING, responses.getFirst().status());
        assertEquals("Order 1", responses.getFirst().info());

        assertEquals(2L, responses.get(1).id());
        assertEquals(customerOrderId, responses.get(1).customerOrderId());
        assertEquals(ManufacturingStatus.IN_PROGRESS, responses.get(1).status());
        assertEquals(2L, responses.get(1).productId());
        assertEquals(10, responses.get(1).quantity());
        assertEquals("Order 2", responses.get(1).info());
    }

    @Test
    void getManufacturingOrders_shouldReturnEmptyListWhenNoOrdersFound() {
        Long customerOrderId = 2L;
        GetCustomersManufacturingOrdersQuery query = new GetCustomersManufacturingOrdersQuery(customerOrderId);

        when(repositoryPort.findByCustomerOrderId(customerOrderId))
                .thenReturn(List.of());

        List<GetManufacturingOrderResponse> responses = handler.getManufacturingOrders(query);

        assertTrue(responses.isEmpty());
    }

}