package com.example.customerorder.adapter.out.persistence;

import com.example.customerorder.adapter.out.persistence.entities.CustomerOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCustomerOrderRepository extends JpaRepository<CustomerOrderEntity, Long> {
}
