package com.example.manufacturingorder.application.command.createManufacturingOrders.handler;

import com.example.manufacturingorder.application.port.in.CreateMultipleManufacturingOrdersUseCase;
import com.example.manufacturingorder.domain.event.CreateManufacturingOrdersEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;



@RequiredArgsConstructor
@Service
public class CreateManufacturingOrdersListener {
    private final CreateMultipleManufacturingOrdersUseCase createMultipleManufacturingOrdersUseCase;

    @EventListener
    public void handle(CreateManufacturingOrdersEvent event) {
        createMultipleManufacturingOrdersUseCase.createManufacturingOrders(event.getCommand());
    }

}