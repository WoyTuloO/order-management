package com.example.manufacturingorder.domain.port;

import com.example.manufacturingorder.domain.model.aggregate.ManufacturingOrder;

import java.util.List;

public interface ManufacturingOrderRepositoryPort {
    void save(List<ManufacturingOrder> manufacturingOrders);
    void save(ManufacturingOrder manufacturingOrder);
    ManufacturingOrder findById(Long id);
    List<ManufacturingOrder> findByCustomerOrderId(Long customerOrderId);
    void update(ManufacturingOrder manufacturingOrder);
}
