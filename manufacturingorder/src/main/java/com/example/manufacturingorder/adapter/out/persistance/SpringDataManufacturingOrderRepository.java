package com.example.manufacturingorder.adapter.out.persistance;

import com.example.manufacturingorder.adapter.out.persistance.entity.ManufacturingOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataManufacturingOrderRepository extends JpaRepository<ManufacturingOrderEntity, Long> {
}
