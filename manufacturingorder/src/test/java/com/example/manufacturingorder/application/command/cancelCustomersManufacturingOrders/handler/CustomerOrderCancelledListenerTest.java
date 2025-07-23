package com.example.manufacturingorder.application.command.cancelCustomersManufacturingOrders.handler;

import com.example.manufacturingorder.application.command.cancelCustomersManufacturingOrders.CancelCustomersManufacturingOrdersCommand;
import com.example.manufacturingorder.application.port.in.CancelCustomersManufacturingOrdersUseCase;
import com.example.manufacturingorder.domain.event.CancelManufacturingOrdersEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerOrderCancelledListenerTest {

    @Mock
    private CancelCustomersManufacturingOrdersUseCase useCase;

    @InjectMocks
    private CustomerOrderCancelledListener listener;

    @Test
    void handle_shouldDelegateToUseCaseWhenCorrectEvent() {

        CancelManufacturingOrdersEvent event = new CancelManufacturingOrdersEvent(1L, "Test reason");
        CancelCustomersManufacturingOrdersCommand command =
                new CancelCustomersManufacturingOrdersCommand(event.customerOrderId(), event.reason());

        listener.handle(event);

        verify(useCase).cancelManufacturingOrders(command);
    }


    @Test
    void handle_shouldProcessNullCommandInEvent() {
        CancelManufacturingOrdersEvent event = new CancelManufacturingOrdersEvent(1L, null);

        listener.handle(event);

        verify(useCase).cancelManufacturingOrders(event.getCommand());
    }

}