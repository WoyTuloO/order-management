package com.example.customerorder.application.port.out;

import com.example.customerorder.domain.model.aggregate.CustomerOrder;

public interface CustomerOrderRepositoryPort {
    Long save(CustomerOrder order);
    CustomerOrder findById(Long id);
    void update(CustomerOrder order);
}
