package com.example.manufacturingorder.adapter.in.rest.dto.response;

import com.example.manufacturingorder.domain.model.aggregate.ManufacturingOrder;
import com.example.manufacturingorder.domain.model.enums.ManufacturingStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetManufacturingOrderResponseTest {
    @Test
    void shouldCorrectlyInitializeFields() {
        GetManufacturingOrderResponse response = new GetManufacturingOrderResponse(
                1L, 2L, 3L, 5, ManufacturingStatus.COMPLETED, "Done");

        assertEquals(1L, response.id());
        assertEquals(2L, response.customerOrderId());
        assertEquals(3L, response.productId());
        assertEquals(5, response.quantity());
        assertEquals(ManufacturingStatus.COMPLETED, response.status());
        assertEquals("Done", response.info());
    }

    @Test
    void shouldCreateResponseFromDomainObject() {
        ManufacturingOrder order = new ManufacturingOrder(1L, 2L, 3L, 5, ManufacturingStatus.IN_PROGRESS, "Processing");

        GetManufacturingOrderResponse response = GetManufacturingOrderResponse.fromDomain(order);

        assertEquals(order.getId(), response.id());
        assertEquals(order.getCustomerOrderId(), response.customerOrderId());
        assertEquals(order.getProductId(), response.productId());
        assertEquals(order.getRequiredQuantity(), response.quantity());
        assertEquals(order.getStatus(), response.status());
        assertEquals(order.getInfo(), response.info());
    }

    @Test
    void shouldHandleNullValuesInFromDomain() {
        ManufacturingOrder order = new ManufacturingOrder(null, null, null, 0, null, null);

        GetManufacturingOrderResponse response = GetManufacturingOrderResponse.fromDomain(order);

        assertNull(response.id());
        assertNull(response.customerOrderId());
        assertNull(response.productId());
        assertEquals(0, response.quantity());
        assertNull(response.status());
        assertNull(response.info());
    }

    @Test
    void shouldHandleNullDomainObject() {
        GetManufacturingOrderResponse response = GetManufacturingOrderResponse.fromDomain(null);
        assertNull(response);
    }
}