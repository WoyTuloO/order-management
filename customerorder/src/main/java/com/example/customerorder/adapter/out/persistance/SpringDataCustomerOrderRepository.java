package com.example.customerorder.adapter.out.persistance;

import com.example.customerorder.adapter.out.persistance.entities.CustomerOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCustomerOrderRepository extends JpaRepository<CustomerOrderEntity, Long> {
}
