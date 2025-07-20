package com.example.manufacturingorder.application.port.out;

import com.example.manufacturingorder.domain.model.aggregate.ManufacturingOrder;

public interface ManufacturingOrderRepositoryPort {
    void save(ManufacturingOrder manufacturingOrder);
    ManufacturingOrder findById(Long id);
}
