package com.example.manufacturingorder.adapter.out;

import com.example.manufacturingorder.domain.model.ManufacturingOrder;
import com.example.manufacturingorder.domain.port.ManufacturingOrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ManufacturingOrderJpaRepository implements ManufacturingOrderRepository {

    @Override
    public void save(ManufacturingOrder order) {

    }
}
