package com.example.customerorder.domain.port;

import com.example.customerorder.domain.model.aggregate.CustomerOrder;

public interface CustomerOrderRepositoryPort {
    Long save(CustomerOrder order);
    CustomerOrder findById(Long id);
    void update(CustomerOrder order);
}
