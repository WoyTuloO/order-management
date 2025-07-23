package com.example.customerorder.adapter.dto.request;

import com.example.customerorder.application.command.UpdateGlobalCustomerOrderStatus.UpdateGlobalCustomerOrderStatusCommand;
import com.example.customerorder.domain.model.enums.OrderStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateGlobalCustomerOrderStatusRequestTest {

        @Test
        void toCommand_shouldReturnCommand() {

            Long expectedOrderId = 1L;
            OrderStatus expectedStatus = OrderStatus.COMPLETED;
            String expectedInfo = "Order completed";

            UpdateGlobalCustomerOrderStatusRequest request = new UpdateGlobalCustomerOrderStatusRequest(
                    expectedOrderId, expectedStatus, expectedInfo);

            UpdateGlobalCustomerOrderStatusCommand command = request.toCommand();

            assertEquals(expectedOrderId, command.customerOrderId());
            assertEquals(expectedStatus, command.newStatus());
            assertEquals(expectedInfo, command.info());

        }
}