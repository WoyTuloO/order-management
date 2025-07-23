package com.example.customerorder.adapter.in.rest;

import com.example.customerorder.adapter.dto.request.CancelCustomerOrderRequest;
import com.example.customerorder.adapter.dto.request.CreateCustomerOrderRequest;
import com.example.customerorder.adapter.dto.request.UpdateGlobalCustomerOrderStatusRequest;
import com.example.customerorder.adapter.dto.response.CustomerOrderItemResponse;
import com.example.customerorder.adapter.dto.response.GetCustomerOrderResponse;
import com.example.customerorder.application.port.in.CancelCustomerOrderUseCase;
import com.example.customerorder.application.port.in.CreateCustomerOrderUseCase;
import com.example.customerorder.application.port.in.GetCustomerOrderUseCase;
import com.example.customerorder.application.port.in.UpdateGlobalCustomerOrderStatusUseCase;
import com.example.customerorder.config.exceptions.CustomerResourceNotFound;
import com.example.customerorder.config.exceptions.NonPositiveAmountException;
import com.example.customerorder.domain.model.enums.OrderItemStatus;
import com.example.customerorder.domain.model.enums.OrderStatus;
import com.example.customerorder.domain.model.valueobject.OrderItem;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerOrderControllerTest {

    @Mock
    private CreateCustomerOrderUseCase createCustomerOrderUseCase;

    @Mock
    private GetCustomerOrderUseCase getCustomerOrderUseCase;

    @Mock
    private CancelCustomerOrderUseCase cancelCustomerOrderUseCase;

    @Mock
    private UpdateGlobalCustomerOrderStatusUseCase updateGlobalCustomerOrderStatusUseCase;

    @InjectMocks
    private CustomerOrderController customerOrderController;


    @Test
    void createOrder_ShouldReturnOk_WhenRequestIsValid() {

        CreateCustomerOrderRequest request = new CreateCustomerOrderRequest(1L,
                List.of(new OrderItem(1L,2)));

        ResponseEntity<Void> response = customerOrderController.createOrder(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(createCustomerOrderUseCase, times(1)).createCustomerOrder(any());
    }

    @Test
    void createOrder_ShouldThrowException_WhenOrderItemHasZeroQuantity() {  //test jakby ktos dal id=0 przy createOrder
        assertThrows(
                NonPositiveAmountException.class,
                () -> new OrderItem(0L, 0)
        );
    }

    @Test
    void getCustomerOrder_ShouldReturnOrder_WhenIdExists() {

        long orderId = 1L;
        GetCustomerOrderResponse expectedResponse = new GetCustomerOrderResponse(
                orderId,
                1L,
                List.of(new CustomerOrderItemResponse(
                        1L,
                        2,
                        OrderItemStatus.PENDING,
                        ""
                )),
                OrderStatus.PENDING,
                ""
        );
        when(getCustomerOrderUseCase.getCustomerOrder(any())).thenReturn(expectedResponse);

        ResponseEntity<?> response = customerOrderController.getCustomerOrder(orderId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(getCustomerOrderUseCase, times(1)).getCustomerOrder(any());
    }


    @Test
    void getCustomerOrder_ShouldThrowNotFound_WhenIdNotExists() {

        Long orderId = 5L;
        String errorMessage = "Customer order not found with id: " + orderId;

        when(getCustomerOrderUseCase.getCustomerOrder(any()))
                .thenThrow(new CustomerResourceNotFound(errorMessage));

        CustomerResourceNotFound exception = assertThrows(CustomerResourceNotFound.class,
                () -> customerOrderController.getCustomerOrder(orderId));

        assertEquals(errorMessage, exception.getMessage());

        verify(getCustomerOrderUseCase, times(1)).getCustomerOrder(any());
    }

    @Test
    void cancelCustomerOrder_ShouldReturnOk_WhenRequestIsValid() {

        CancelCustomerOrderRequest request = new CancelCustomerOrderRequest(
                1L
        );

        ResponseEntity<Void> response = customerOrderController.cancelCustomerOrder(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(cancelCustomerOrderUseCase, times(1)).cancelCustomerOrder(any());
    }

    @Test
    void cancelCustomerOrder_ShouldReturnOk_WhenRequestIsNotValid() {
        Long orderId = 5L;
        CancelCustomerOrderRequest request = new CancelCustomerOrderRequest(orderId);
        String errorMessage = "Customer order not found with id: " + orderId;

        doThrow(new CustomerResourceNotFound(errorMessage))
                .when(cancelCustomerOrderUseCase)
                .cancelCustomerOrder(any());

        CustomerResourceNotFound exception = assertThrows(
                CustomerResourceNotFound.class,
                () -> customerOrderController.cancelCustomerOrder(request)
        );

        assertEquals(errorMessage, exception.getMessage());
        verify(cancelCustomerOrderUseCase, times(1))
                .cancelCustomerOrder(any());
    }

    @Test
    void updateGlobalCustomerOrderStatus_ShouldReturnOk_WhenRequestIsValid() {

        UpdateGlobalCustomerOrderStatusRequest request = new UpdateGlobalCustomerOrderStatusRequest(
                1L,
                OrderStatus.CONFIRMED,
                "Order confirmed"
        );

        ResponseEntity<Void> response = customerOrderController.updateCustomersManufacturingOrders(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(updateGlobalCustomerOrderStatusUseCase, times(1)).updateGlobalCustomerOrderStatus(any());
    }


    @Test
    void updateGlobalCustomerOrderStatus_ShouldThrow_WhenRequestIsNotValid() {

        long orderId = 5L;

        UpdateGlobalCustomerOrderStatusRequest request = new UpdateGlobalCustomerOrderStatusRequest(
                1L,
                OrderStatus.CONFIRMED,
                "Order confirmed"
        );

        String errorMessage = "Customer order not found with id: " + orderId;

        doThrow(new CustomerResourceNotFound(errorMessage))
                .when(updateGlobalCustomerOrderStatusUseCase)
                .updateGlobalCustomerOrderStatus(any());

        CustomerResourceNotFound exception = assertThrows(
                CustomerResourceNotFound.class,
                () -> customerOrderController.updateCustomersManufacturingOrders(request)
        );

        assertEquals(errorMessage, exception.getMessage());
        verify(updateGlobalCustomerOrderStatusUseCase, times(1))
                .updateGlobalCustomerOrderStatus(any());


    }


}