package com.example.manufacturingorder.domain.port;

import com.example.manufacturingorder.domain.model.ManufacturingOrder;

public interface ManufacturingOrderRepository {
    void save(ManufacturingOrder order);
}
