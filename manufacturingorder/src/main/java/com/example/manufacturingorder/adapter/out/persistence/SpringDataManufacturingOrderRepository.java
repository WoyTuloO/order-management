package com.example.manufacturingorder.adapter.out.persistence;

import com.example.manufacturingorder.adapter.out.persistence.entities.ManufacturingOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataManufacturingOrderRepository extends JpaRepository<ManufacturingOrderEntity, Long> {
    List<ManufacturingOrderEntity> findAllByCustomerOrderId(Long customerOrderId);
}
