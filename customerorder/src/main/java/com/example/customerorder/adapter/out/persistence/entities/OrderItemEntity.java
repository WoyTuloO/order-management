package com.example.customerorder.adapter.out.persistence.entities;

import com.example.customerorder.domain.model.valueobject.OrderItem;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemEntity {
    private Long productId;
    private int quantity;

    public static OrderItemEntity fromDomain(OrderItem item) {
        return new OrderItemEntity(item.productId(), item.quantity());
    }
    public OrderItem toDomain() {
        return new OrderItem(this.productId, this.quantity);
    }
}
