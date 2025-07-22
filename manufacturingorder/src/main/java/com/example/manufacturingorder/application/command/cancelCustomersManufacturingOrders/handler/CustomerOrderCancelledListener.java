package com.example.manufacturingorder.application.command.cancelCustomersManufacturingOrders.handler;


import com.example.manufacturingorder.application.port.in.CancelCustomersManufacturingOrdersUseCase;
import com.example.manufacturingorder.domain.event.CancelManufacturingOrdersEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerOrderCancelledListener {
    private final CancelCustomersManufacturingOrdersUseCase cancelCustomersManufacturingOrdersUseCase;

    @EventListener
    public void handle(CancelManufacturingOrdersEvent event) {
        cancelCustomersManufacturingOrdersUseCase.cancelManufacturingOrders(event.getCommand());
    }

}
