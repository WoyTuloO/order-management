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

    public static CustomerOrderEntity fromDomain(CustomerOrder order) {
        CustomerOrderEntity entity = new CustomerOrderEntity();
        entity.customerId = order.getCustomerId();
        entity.status = order.getStatus();
        entity.info = order.getInfo();
        entity.items = order.getItems().stream()
                .map(OrderItemEntity::fromDomain)
                .toList();
        return entity;
    }

    public CustomerOrder toDomain() {
        return CustomerOrder.recreate(
                this.id,
                this.customerId,
                this.items.stream().map(OrderItemEntity::toDomain).toList(),
                this.status,
                this.info
        );
    }
}
