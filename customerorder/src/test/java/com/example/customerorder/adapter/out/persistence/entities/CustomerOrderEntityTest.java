package com.example.customerorder.adapter.out.persistence.entities;

import com.example.customerorder.domain.model.aggregate.CustomerOrder;
import com.example.customerorder.domain.model.enums.OrderStatus;
import com.example.customerorder.domain.model.valueobject.OrderItem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerOrderEntityTest {

    @Test
    void fromDomain_shouldConvertAllFieldsCorrectly() {

        CustomerOrder order = CustomerOrder.recreate(
                1L,
                100L,
                List.of(new OrderItem(1L, 1), new OrderItem(2L, 2)),
                OrderStatus.CONFIRMED,
                "Special order",
                List.of(101L, 102L)
        );

        CustomerOrderEntity entity = CustomerOrderEntity.fromDomain(order);

        assertEquals(order.getId(), entity.getId());
        assertEquals(order.getCustomerId(), entity.getCustomerId());
        assertEquals(order.getStatus(), entity.getStatus());
        assertEquals(order.getInfo(), entity.getInfo());
        assertEquals(order.getManufacturingOrderIds(), entity.getManufacturingOrderIds());
        assertEquals(order.getItems().size(), entity.getItems().size());
    }

    @Test
    void toDomain_shouldConvertAllFieldsCorrectly() {

        CustomerOrderEntity entity = new CustomerOrderEntity();
        entity.setId(1L);
        entity.setCustomerId(100L);
        entity.setStatus(OrderStatus.CONFIRMED);
        entity.setInfo("Urgent delivery");
        entity.setItems(new ArrayList<>(List.of(
                new OrderItemEntity(1L, 2),
                new OrderItemEntity(2L, 1)
        )));
        entity.setManufacturingOrderIds(new ArrayList<>(List.of(1L, 2L)));

        CustomerOrder domain = CustomerOrderEntity.toDomain(entity);

        assertEquals(entity.getId(), domain.getId());
        assertEquals(entity.getCustomerId(), domain.getCustomerId());
        assertEquals(entity.getStatus(), domain.getStatus());
        assertEquals(entity.getInfo(), domain.getInfo());
        assertEquals(entity.getManufacturingOrderIds(), domain.getManufacturingOrderIds());
        assertEquals(entity.getItems().size(), domain.getItems().size());

    }
}