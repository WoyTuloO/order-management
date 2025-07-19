package com.example.customerorder.application.service;

import com.example.customerorder.adapter.in.rest.dto.CreateOrderRequest;
import com.example.customerorder.adapter.in.rest.dto.OrderResponse;
import com.example.customerorder.application.command.CreateOrderCommand;
import com.example.customerorder.config.OrderItemMapper;
import com.example.customerorder.domain.model.CustomerOrder;
import com.example.customerorder.domain.port.ManufacturingOrderClient;
import com.example.customerorder.domain.port.OrderRepository;
import com.example.customerorder.domain.service.CustomerOrderService;
import com.example.manufacturingorder.adapter.in.rest.dto.CreateManufacturingOrderRequest;
import com.example.manufacturingorder.adapter.in.rest.dto.ManufacturingOrderResponse;
import com.example.manufacturingorder.domain.model.ManufacturingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderApplicationService {

    private final CustomerOrderService customerOrderService;
    private final OrderRepository orderRepository;
    private final ManufacturingOrderClient manufacturingOrderClient;
    private final OrderItemMapper orderItemMapper;

    @Autowired
    public OrderApplicationService(CustomerOrderService customerOrderService, OrderRepository orderRepository, ManufacturingOrderClient manufacturingOrderClient, OrderItemMapper orderItemMapper) {
        this.customerOrderService = customerOrderService;
        this.orderRepository = orderRepository;
        this.manufacturingOrderClient = manufacturingOrderClient;
        this.orderItemMapper = orderItemMapper;
    }

    public OrderResponse createOrder(CreateOrderRequest request) {

        CustomerOrder customerOrder = customerOrderService.createOrder(request.toCommand());

        orderRepository.save(customerOrder);

        ManufacturingOrderResponse productionOrderResponse = manufacturingOrderClient.createManufacturingOrder(
                new CreateManufacturingOrderRequest(customerOrder.getId(), orderItemMapper.toManufacturingItemDtoList(customerOrder.getItems()))
        );

        if(productionOrderResponse.status() == ManufacturingStatus.REJECTED){
            customerOrder.cancel("Manufacturing order was rejected");
            orderRepository.save(customerOrder);
        }

        return OrderResponse.fromDomain(customerOrder);
    }
}
