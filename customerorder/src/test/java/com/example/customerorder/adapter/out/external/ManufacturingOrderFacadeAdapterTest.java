package com.example.customerorder.adapter.out.external;

import com.example.manufacturingorder.adapter.dto.response.GetManufacturingOrderResponse;
import com.example.manufacturingorder.application.port.in.GetCustomersManufacturingOrdersUseCase;
import com.example.manufacturingorder.application.query.getCustomersManufacturingOrders.GetCustomersManufacturingOrdersQuery;
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
class ManufacturingOrderFacadeAdapterTest {

    @Mock
    private GetCustomersManufacturingOrdersUseCase getCustomersManufacturingOrdersUseCase;

    @InjectMocks
    private ManufacturingOrderFacadeAdapter adapter;

    @Test
    void getCustomerOrdersManufacturingOrders_shouldDelegateToUseCase() {

        Long customerOrderId = 1L;
        var expectedResponse = List.of(
                new GetManufacturingOrderResponse(1L, 2L, 1L, 2, ManufacturingStatus.IN_PROGRESS, "info1"),
                new GetManufacturingOrderResponse(2L, 3L, 2L, 1, ManufacturingStatus.IN_PROGRESS, "info2")
        );

        when(getCustomersManufacturingOrdersUseCase.getManufacturingOrders(
                new GetCustomersManufacturingOrdersQuery(customerOrderId)))
                .thenReturn(expectedResponse);

        List<GetManufacturingOrderResponse> result = adapter.getCustomerOrdersManufacturingOrders(customerOrderId);

        assertSame(expectedResponse, result);
        verify(getCustomersManufacturingOrdersUseCase, times(1))
                .getManufacturingOrders(new GetCustomersManufacturingOrdersQuery(customerOrderId));
    }

    @Test
    void getCustomerOrdersManufacturingOrders_shouldHandleNullInput() {

        when(getCustomersManufacturingOrdersUseCase.getManufacturingOrders(
                new GetCustomersManufacturingOrdersQuery(null)))
                .thenReturn(List.of());

        List<GetManufacturingOrderResponse> result = adapter.getCustomerOrdersManufacturingOrders(null);

        assertTrue(result.isEmpty());
    }
}