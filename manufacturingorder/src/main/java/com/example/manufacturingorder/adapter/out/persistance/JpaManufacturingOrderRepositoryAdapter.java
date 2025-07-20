package com.example.manufacturingorder.adapter.out.persistance;

import com.example.manufacturingorder.adapter.out.persistance.entity.ManufacturingOrderEntity;
import com.example.manufacturingorder.application.port.out.ManufacturingOrderRepositoryPort;
import com.example.manufacturingorder.domain.model.aggregate.ManufacturingOrder;
import org.springframework.stereotype.Repository;

@Repository
public class JpaManufacturingOrderRepositoryAdapter implements ManufacturingOrderRepositoryPort {

    SpringDataManufacturingOrderRepository manufacturingOrderRepository;

    public JpaManufacturingOrderRepositoryAdapter(SpringDataManufacturingOrderRepository manufacturingOrderRepository) {
        this.manufacturingOrderRepository = manufacturingOrderRepository;
    }

    @Override
    public void save(ManufacturingOrder manufacturingOrder) {
        ManufacturingOrderEntity manufacturingOrderEntity = ManufacturingOrderEntity.fromDomain(manufacturingOrder);
        manufacturingOrderRepository.save(manufacturingOrderEntity);
    }

    @Override
    public ManufacturingOrder findById(Long id) {
        ManufacturingOrderEntity manufacturingOrderEntity = manufacturingOrderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Manufacturing order not found with id: " + id));

//        ManufacturingOrder manufacturingOrder = new ManufacturingOrder();
        return null;
    }
}
