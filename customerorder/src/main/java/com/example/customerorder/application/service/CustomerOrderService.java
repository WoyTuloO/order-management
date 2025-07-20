package com.example.customerorder.application.service;


import com.example.customerorder.application.command.CreateCustomerOrderCommand;
import com.example.customerorder.application.port.out.CustomerOrderRepositoryPort;
import com.example.customerorder.domain.model.aggregate.CustomerOrder;
import org.springframework.stereotype.Service;

@Service
public class CustomerOrderService {

    private final CustomerOrderRepositoryPort customerOrderRepositoryPort;

    public CustomerOrderService(CustomerOrderRepositoryPort customerOrderRepositoryPort) {
        this.customerOrderRepositoryPort = customerOrderRepositoryPort;
    }

    public void createCustomerOrder(CustomerOrder order) {
        customerOrderRepositoryPort.save(order);
    }


    public CustomerOrder getCustomerOrder(long id) {
        return customerOrderRepositoryPort.findById(id);
    }


    public void createCustomerOrder(CreateCustomerOrderCommand command) {

    }
}
