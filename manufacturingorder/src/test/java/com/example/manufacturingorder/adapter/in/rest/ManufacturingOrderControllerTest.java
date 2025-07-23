package com.example.manufacturingorder.adapter.in.rest;

import com.example.manufacturingorder.adapter.in.rest.dto.request.CancelManufacturingOrderRequest;
import com.example.manufacturingorder.adapter.in.rest.dto.request.CreateManufacturingOrderRequest;
import com.example.manufacturingorder.adapter.in.rest.dto.request.UpdateManufacturingOrderStatusRequest;
import com.example.manufacturingorder.adapter.in.rest.dto.response.GetManufacturingOrderResponse;
import com.example.manufacturingorder.application.port.in.*;
import com.example.manufacturingorder.domain.model.aggregate.ManufacturingOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ManufacturingOrderControllerTest {

    @Mock
    private CreateManufacturingOrderUseCase createManufacturingOrderUseCase;

    @Mock
    private GetManufacturingOrderUseCase getManufacturingOrderUseCase;

    @Mock
    private GetCustomersManufacturingOrdersUseCase getManufacturingOrdersUseCase;

    @Mock
    private CancelManufacturingOrderUseCase cancelManufacturingOrderUseCase;

    @Mock
    private UpdateManufacturingOrderStatusUseCase updateManufacturingOrderStatusUseCase;

    @InjectMocks
    private ManufacturingOrderController controller;

    @Test
    void createOrder_shouldInvokeUseCaseAndReturnOk() {
        CreateManufacturingOrderRequest request = new CreateManufacturingOrderRequest(1L, null);

        ResponseEntity<Void> response = controller.createOrder(request);

        verify(createManufacturingOrderUseCase).createManufacturingOrder(request.toCommand());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getManufacturingOrder_shouldInvokeUseCaseAndReturnOk() {
        Long orderId = 1L;

        ManufacturingOrder expectedOrder = new ManufacturingOrder(orderId,1L, 1);
        when(getManufacturingOrderUseCase.getManufacturingOrder(any())).thenReturn(GetManufacturingOrderResponse.fromDomain(expectedOrder));

        ResponseEntity<?> response = controller.getManufacturingOrder(orderId);

        verify(getManufacturingOrderUseCase).getManufacturingOrder(any());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(GetManufacturingOrderResponse.fromDomain(expectedOrder), response.getBody());
    }

    @Test
    void getManufacturingOrdersByCustomerOrderId_shouldInvokeUseCaseAndReturnOk() {
        Long customerOrderId = 1L;
        List<GetManufacturingOrderResponse> expectedOrders = List.of(
                GetManufacturingOrderResponse.fromDomain(new ManufacturingOrder(1L, customerOrderId, 5)),
                        GetManufacturingOrderResponse.fromDomain(new ManufacturingOrder(2L, customerOrderId, 10))
        );

        when(getManufacturingOrdersUseCase.getManufacturingOrders(any())).thenReturn(expectedOrders);

        ResponseEntity<?> response = controller.getManufacturingOrdersByCustomerOrderId(customerOrderId);

        verify(getManufacturingOrdersUseCase).getManufacturingOrders(any());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedOrders, response.getBody());
    }

    @Test
    void cancelManufacturingOrder_shouldInvokeUseCaseAndReturnOk() {
        CancelManufacturingOrderRequest request = new CancelManufacturingOrderRequest(1L, "reason");

        ResponseEntity<Void> response = controller.cancelManufacturingOrder(request);

        verify(cancelManufacturingOrderUseCase).cancelManufacturingOrder(request.toCommand());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateManufacturingOrder_shouldInvokeUseCaseAndReturnOk() {
        UpdateManufacturingOrderStatusRequest request = new UpdateManufacturingOrderStatusRequest(1L, null, "info");

        ResponseEntity<Void> response = controller.updateManufacturingOrder(request);

        verify(updateManufacturingOrderStatusUseCase).updateManufacturingOrderStatus(request.toCommand());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}