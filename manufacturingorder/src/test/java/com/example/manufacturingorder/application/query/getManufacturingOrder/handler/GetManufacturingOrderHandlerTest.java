package com.example.manufacturingorder.application.query.getManufacturingOrder.handler;

import com.example.manufacturingorder.adapter.dto.response.GetManufacturingOrderResponse;
import com.example.manufacturingorder.domain.port.ManufacturingOrderRepositoryPort;
import com.example.manufacturingorder.application.query.getManufacturingOrder.GetManufacturingOrderQuery;
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
class GetManufacturingOrderHandlerTest {

    @Mock
    private ManufacturingOrderRepositoryPort repositoryPort;

    @InjectMocks
    private GetManufacturingOrderHandler handler;

    @Test
    void getManufacturingOrder_shouldReturnMappedResponse() {
        Long orderId = 1L;
        GetManufacturingOrderQuery query = new GetManufacturingOrderQuery(orderId);

        ManufacturingOrder order = new ManufacturingOrder(
                orderId,
                1L,
                2L,
                5,
                ManufacturingStatus.IN_PROGRESS,
                "Test order"
        );
        when(repositoryPort.findById(orderId)).thenReturn(order);

        GetManufacturingOrderResponse response = handler.getManufacturingOrder(query);

        assertNotNull(response);
        assertEquals(orderId, response.id());
        assertEquals(1L, response.customerOrderId());
        assertEquals(2L, response.productId());
        assertEquals(5, response.quantity());
        assertEquals(ManufacturingStatus.IN_PROGRESS, response.status());
        assertEquals("Test order", response.info());
    }

    @Test
    void getManufacturingOrder_shouldThrowWhenOrderNotFound() {
        Long nonExistentOrderId = 2L;
        var query = new GetManufacturingOrderQuery(nonExistentOrderId);

        when(repositoryPort.findById(nonExistentOrderId))
                .thenThrow(new ManufacturingResourceNotFound("Order not found"));

        assertThrows(ManufacturingResourceNotFound.class, () ->
                handler.getManufacturingOrder(query));
    }

}