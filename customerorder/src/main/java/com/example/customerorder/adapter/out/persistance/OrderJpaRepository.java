package com.example.customerorder.adapter.out.persistance;

import com.example.customerorder.domain.model.CustomerOrder;
import com.example.customerorder.domain.port.OrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrderJpaRepository implements OrderRepository {
    @Override
    public void save(CustomerOrder order) {

    }
}
