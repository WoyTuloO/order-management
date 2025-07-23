package com.example.manufacturingorder.application.command.createManufacturingOrders.handler;


import com.example.manufacturingorder.application.command.createManufacturingOrders.CreateManufacturingOrdersCommand;
import com.example.manufacturingorder.application.port.in.CreateMultipleManufacturingOrdersUseCase;
import com.example.manufacturingorder.domain.event.CreateManufacturingOrdersEvent;
import com.example.manufacturingorder.domain.model.valueobject.ManufacturingItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateManufacturingOrdersListenerTest {

    @Mock
    private CreateMultipleManufacturingOrdersUseCase useCase;

    @InjectMocks
    private CreateManufacturingOrdersListener listener;

    @Test
    void handle_shouldDelegateToUseCaseForValidEvent() {
        CreateManufacturingOrdersEvent event = new CreateManufacturingOrdersEvent(1L,
                List.of(new ManufacturingItem(1L, 5)));
        CreateManufacturingOrdersCommand command = event.getCommand();

        listener.handle(event);

        verify(useCase).createManufacturingOrders(command);
    }

    @Test
    void handle_shouldProcessEventWithEmptyItems() {
        CreateManufacturingOrdersEvent event = new CreateManufacturingOrdersEvent(3L, List.of());
        CreateManufacturingOrdersCommand command = event.getCommand();

        listener.handle(event);

        verify(useCase).createManufacturingOrders(command);
    }
}