package com.example.manufacturingorder.adapter.out.persistance;

import com.example.manufacturingorder.adapter.out.persistance.entity.ManufacturingOrderEntity;
import com.example.manufacturingorder.domain.model.aggregate.ManufacturingOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataManufacturingOrderRepository extends JpaRepository<ManufacturingOrderEntity, Long> {
    List<ManufacturingOrderEntity> findAllByCustomerOrderId(Long customerOrderId);
}
