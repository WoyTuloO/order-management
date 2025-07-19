package com.example.manufacturingorder.domain.service;


import com.example.manufacturingorder.application.command.CreateManufacturingOrderCommand;
import com.example.manufacturingorder.domain.model.ManufacturingOrder;
import org.springframework.stereotype.Service;

@Service
public class ManufacturingOrderService {
    public ManufacturingOrder createOrder(CreateManufacturingOrderCommand command) {
        return null;
    }
}
