package com.example.manufacturingorder.adapter.out.persistance;

import com.example.manufacturingorder.adapter.out.persistance.entity.ManufacturingOrderEntity;
import com.example.manufacturingorder.application.port.out.ManufacturingOrderRepositoryPort;
import com.example.manufacturingorder.config.exceptions.ManufacturingResourceNotFound;
import com.example.manufacturingorder.domain.model.aggregate.ManufacturingOrder;
import com.example.manufacturingorder.domain.model.enums.ManufacturingStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaManufacturingOrderRepositoryAdapter implements ManufacturingOrderRepositoryPort {

    SpringDataManufacturingOrderRepository manufacturingOrderRepository;

    public JpaManufacturingOrderRepositoryAdapter(SpringDataManufacturingOrderRepository manufacturingOrderRepository) {
        this.manufacturingOrderRepository = manufacturingOrderRepository;
    }

    @Override
    public void save(List<ManufacturingOrder> manufacturingOrders) {
        manufacturingOrderRepository.saveAll(manufacturingOrders.stream()
                .map(ManufacturingOrderEntity::fromDomainUpdated)
                .toList());
    }

    @Override
    public void save(ManufacturingOrder manufacturingOrder) {
        manufacturingOrderRepository.save(ManufacturingOrderEntity.fromDomain(manufacturingOrder));
    }

    @Override
    public ManufacturingOrder findById(Long id) {
        ManufacturingOrderEntity manufacturingOrderEntity = manufacturingOrderRepository.findById(id)
                .orElseThrow(() -> new ManufacturingResourceNotFound("Manufacturing order not found with id: " + id));

        return manufacturingOrderEntity.toDomain();
    }

    @Override
    public List<ManufacturingOrder> findByCustomerOrderId(Long customerOrderId) {
        return manufacturingOrderRepository.findAllByCustomerOrderId(customerOrderId).stream()
                .map(ManufacturingOrderEntity::toDomain)
                .toList();
    }

    @Override
    public void update(ManufacturingOrder manufacturingOrder) {
        manufacturingOrderRepository.save(ManufacturingOrderEntity.fromDomainUpdated(manufacturingOrder));
    }


}
