package com.example.manufacturingorder.application.usecase;

import com.example.manufacturingorder.adapter.in.rest.dto.ManufacturingOrderResponse;
import com.example.manufacturingorder.application.command.CreateManufacturingOrderCommand;
import org.springframework.stereotype.Service;

@Service
public class CreateManufacturingOrderUseCase {
    public ManufacturingOrderResponse execute(CreateManufacturingOrderCommand command) {
        return null;
    }
}
