package com.example.customerorder.domain.service;

import com.example.customerorder.application.command.CreateOrderCommand;
import com.example.customerorder.domain.model.CustomerOrder;
import org.springframework.stereotype.Service;

@Service
public class CustomerOrderService {

    public CustomerOrder createOrder(CreateOrderCommand command) {
        return null;
    }
}
