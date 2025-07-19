package com.example.customerorder.domain.port;

import com.example.customerorder.domain.model.CustomerOrder;


public interface OrderRepository {
    void save(CustomerOrder order);
}
