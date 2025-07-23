package com.example.customerorder.adapter.out.persistence.entities;

import com.example.customerorder.domain.model.valueobject.OrderItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemEntityTest {

    @Test
    void fromDomain_shouldConvertOrderItemCorrectly() {
        OrderItem item = new OrderItem(1L, 3);

        OrderItemEntity entity = OrderItemEntity.fromDomain(item);

        assertEquals(item.productId(), entity.getProductId());
        assertEquals(item.quantity(), entity.getQuantity());
    }

    @Test
    void toDomain_shouldConvertToOrderItemCorrectly() {
        OrderItemEntity entity = new OrderItemEntity();
        entity.setProductId(2L);
        entity.setQuantity(5);

        OrderItem item = entity.toDomain();

        assertEquals(entity.getProductId(), item.productId());
        assertEquals(entity.getQuantity(), item.quantity());

    }
}