package com.example.manufacturingorder.adapter.out.persistence.entities;


import com.example.manufacturingorder.domain.model.aggregate.ManufacturingOrder;
import com.example.manufacturingorder.domain.model.enums.ManufacturingStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "manufacturing_orders")
@Data
public class ManufacturingOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long customerOrderId;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private int requiredQuantity;

    @Enumerated(EnumType.STRING)
    private ManufacturingStatus status;

    private String info;

    public static ManufacturingOrderEntity fromDomain(ManufacturingOrder order) {
        ManufacturingOrderEntity entity = new ManufacturingOrderEntity();
        entity.customerOrderId = order.getCustomerOrderId();
        entity.productId = order.getProductId();
        entity.requiredQuantity = order.getRequiredQuantity();
        entity.status = order.getStatus();
        entity.info = order.getInfo();
        return entity;
    }

    public ManufacturingOrder toDomain() {
        return new ManufacturingOrder(
                this.id,
                this.customerOrderId,
                this.productId,
                this.requiredQuantity,
                this.status,
                this.info
        );
    }

    public static ManufacturingOrderEntity fromDomainUpdated(ManufacturingOrder order) {
        ManufacturingOrderEntity entity = new ManufacturingOrderEntity();
        entity.id = order.getId();
        entity.customerOrderId = order.getCustomerOrderId();
        entity.productId = order.getProductId();
        entity.requiredQuantity = order.getRequiredQuantity();
        entity.status = order.getStatus();
        entity.info = order.getInfo();
        return entity;
    }
}
