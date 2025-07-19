package com.example.customerorder.adapter.in.rest;

import com.example.customerorder.adapter.in.rest.dto.CreateOrderRequest;
import com.example.customerorder.adapter.in.rest.dto.OrderResponse;
import com.example.customerorder.application.service.OrderApplicationService;
import com.example.customerorder.domain.service.CustomerOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/customer-order")
public class CustomerOrderController {

    private final OrderApplicationService orderApplicationService;

    @Autowired
    public CustomerOrderController(OrderApplicationService orderApplicationService) {
        this.orderApplicationService = orderApplicationService;
    }

    @PostMapping("/create")
    public OrderResponse createOrder(@RequestBody @Valid CreateOrderRequest request) {
        return orderApplicationService.createOrder(request);
    }

}


