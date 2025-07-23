package com.example.customerorder.adapter.dto.response;

import com.example.customerorder.domain.model.enums.OrderItemStatus;
import com.example.manufacturingorder.adapter.dto.response.GetManufacturingOrderResponse;
import com.example.manufacturingorder.domain.model.enums.ManufacturingStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerOrderItemResponseTest {

    @Test
    void fromManufacturingOrderResponse_shouldConvertAllFieldsCorrectly() {

        GetManufacturingOrderResponse manufacturingResponse = new GetManufacturingOrderResponse(
                1L,
                101L,
                5L,
                1,
                ManufacturingStatus.COMPLETED,
                "test info"
        );

        CustomerOrderItemResponse result = CustomerOrderItemResponse.fromManufacturingOrderResponse(manufacturingResponse);

        assertEquals(5L, result.productId());
        assertEquals(1, result.quantity());
        assertEquals(OrderItemStatus.COMPLETED, result.status());
        assertEquals("test info", result.info());

    }
}