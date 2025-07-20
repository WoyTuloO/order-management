package com.example.manufacturingorder.adapter.out.persistance.entity;


import com.example.manufacturingorder.domain.model.enums.ManufacturingStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "manufacturing_orders")
public class ManufacturingOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long customerOrderId;

    @Enumerated(EnumType.STRING)
    private ManufacturingStatus status;

    public static ManufacturingOrderEntity fromDomain(com.example.manufacturingorder.domain.model.aggregate.ManufacturingOrder order) {
        ManufacturingOrderEntity entity = new ManufacturingOrderEntity();
        entity.customerOrderId = order.getCustomerOrderId();
        entity.status = order.getStatus();
        return entity;
    }
}
