package com.example.manufacturingorder.adapter.out.persistence.entities;

import com.example.manufacturingorder.domain.model.aggregate.ManufacturingOrder;
import com.example.manufacturingorder.domain.model.enums.ManufacturingStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManufacturingOrderEntityTest {
    @Test
    void fromDomain_shouldCreateEntityWithCorrectValues() {
        ManufacturingOrder order = new ManufacturingOrder(
                null, 1L, 2L, 5, ManufacturingStatus.PENDING, "Test order"
        );

        ManufacturingOrderEntity entity = ManufacturingOrderEntity.fromDomain(order);

        assertNull(entity.getId());
        assertEquals(1L, entity.getCustomerOrderId());
        assertEquals(2L, entity.getProductId());
        assertEquals(5, entity.getRequiredQuantity());
        assertEquals(ManufacturingStatus.PENDING, entity.getStatus());
        assertEquals("Test order", entity.getInfo());
    }

    @Test
    void fromDomainUpdated_shouldCreateEntityWithId() {
        ManufacturingOrder order = new ManufacturingOrder(
                10L, 1L, 2L, 5, ManufacturingStatus.IN_PROGRESS, "Updated order"
        );

        ManufacturingOrderEntity entity = ManufacturingOrderEntity.fromDomainUpdated(order);

        assertEquals(10L, entity.getId());
        assertEquals(1L, entity.getCustomerOrderId());
        assertEquals(ManufacturingStatus.IN_PROGRESS, entity.getStatus());
    }

    @Test
    void toDomain_shouldCreateDomainObjectWithCorrectValues() {
        ManufacturingOrderEntity entity = new ManufacturingOrderEntity();
        entity.setId(1L);
        entity.setCustomerOrderId(2L);
        entity.setProductId(3L);
        entity.setRequiredQuantity(10);
        entity.setStatus(ManufacturingStatus.COMPLETED);
        entity.setInfo("Completed order");

        ManufacturingOrder domain = entity.toDomain();

        assertEquals(1L, domain.getId());
        assertEquals(2L, domain.getCustomerOrderId());
        assertEquals(3L, domain.getProductId());
        assertEquals(10, domain.getRequiredQuantity());
        assertEquals(ManufacturingStatus.COMPLETED, domain.getStatus());
        assertEquals("Completed order", domain.getInfo());
    }

    @Test
    void fromDomain_shouldHandleNullValues() {
        ManufacturingOrder order = new ManufacturingOrder(
                null, null, null, 0, null, null
        );

        ManufacturingOrderEntity entity = ManufacturingOrderEntity.fromDomain(order);

        assertNull(entity.getId());
        assertNull(entity.getCustomerOrderId());
        assertNull(entity.getProductId());
        assertEquals(0, entity.getRequiredQuantity());
        assertNull(entity.getStatus());
        assertNull(entity.getInfo());
    }

    @Test
    void toDomain_shouldHandleNullValues() {
        ManufacturingOrderEntity entity = new ManufacturingOrderEntity();
        entity.setId(null);
        entity.setCustomerOrderId(null);
        entity.setProductId(null);
        entity.setRequiredQuantity(0);
        entity.setStatus(null);
        entity.setInfo(null);

        ManufacturingOrder domain = entity.toDomain();

        assertNull(domain.getId());
        assertNull(domain.getCustomerOrderId());
        assertNull(domain.getProductId());
        assertEquals(0, domain.getRequiredQuantity());
        assertNull(domain.getStatus());
        assertNull(domain.getInfo());
    }


}