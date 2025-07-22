package com.example.customerorder.adapter.out.persistance.entities;

import com.example.customerorder.domain.model.aggregate.CustomerOrder;
import com.example.customerorder.domain.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name = "customer_orders")
public class CustomerOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private Long customerId;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(length = 500)
    private String info;

    @ElementCollection
    @CollectionTable(
            name = "order_items",
            joinColumns = @JoinColumn(name = "order_id")
    )
    private List<OrderItemEntity> items = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "customer_order_manufacturing_orders",
            joinColumns = @JoinColumn(name = "customer_order_id"))
    @Column(name = "manufacturing_order_id")
    private List<Long> manufacturingOrderIds = new ArrayList<>();



    public static CustomerOrderEntity fromDomain(CustomerOrder order) {
        CustomerOrderEntity entity = new CustomerOrderEntity();
        entity.id = order.getId();
        entity.customerId = order.getCustomerId();
        entity.status = order.getStatus();
        entity.info = order.getInfo();
        entity.items = new ArrayList<>(order.getItems().stream()
                .map(OrderItemEntity::fromDomain)
                .toList());
        entity.manufacturingOrderIds = new ArrayList<>(order.getManufacturingOrderIds());
        return entity;
    }

    public CustomerOrder toDomain() {
        return CustomerOrder.recreate(
                this.id,
                this.customerId,
                new ArrayList<>(this.items.stream().map(OrderItemEntity::toDomain).toList()),
                this.status,
                this.info,
                new ArrayList<>(this.manufacturingOrderIds)
        );
    }

}
