package com.example.manufacturingorder.application.port.out;

import com.example.manufacturingorder.domain.model.aggregate.ManufacturingOrder;
import com.example.manufacturingorder.domain.model.enums.ManufacturingStatus;

import java.util.List;

public interface ManufacturingOrderRepositoryPort {
    void save(List<ManufacturingOrder> manufacturingOrders);
    void save(ManufacturingOrder manufacturingOrder);
    ManufacturingOrder findById(Long id);
    List<ManufacturingOrder> findByCustomerOrderId(Long customerOrderId);
    void update(ManufacturingOrder manufacturingOrder);
}
