package com.example.manufacturingorder.domain.model.aggregate;

import com.example.manufacturingorder.domain.model.enums.ManufacturingStatus;
import com.example.manufacturingorder.domain.model.valueobject.ManufacturingItem;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManufacturingOrderTest {

    @Test
    void createFromItem_shouldInitializeCorrectly() {
        ManufacturingItem item = new ManufacturingItem(3L, 10);

        ManufacturingOrder order = ManufacturingOrder.create(1L, item);

        assertEquals(1L, order.getCustomerOrderId());
        assertEquals(3L, order.getProductId());
        assertEquals(10, order.getRequiredQuantity());
        assertEquals(ManufacturingStatus.PENDING, order.getStatus());
    }

    @Test
    void createFromItems_shouldGenerateMultipleOrders() {
        List<ManufacturingItem> items = List.of(
                new ManufacturingItem(1L, 5),
                new ManufacturingItem(2L, 10)
        );

        List<ManufacturingOrder> orders = ManufacturingOrder.create(1L, items);

        assertEquals(2, orders.size());
        assertEquals(1L, orders.getFirst().getProductId());
        assertEquals(2L, orders.get(1).getProductId());
        assertEquals(1L, orders.getFirst().getCustomerOrderId());
        assertEquals(1L, orders.get(1).getCustomerOrderId());
    }

    @Test
    void markAsCancelled_shouldUpdateStatusAndInfo() {
        ManufacturingOrder order = new ManufacturingOrder(1L, 2L, 5);
        String reason = "Customer request";

        order.markAsCancelled(reason);

        assertEquals(ManufacturingStatus.CANCELLED, order.getStatus());
        assertEquals(reason, order.getInfo());
    }

    @Test
    void markAsCancelled_shouldHandleNullReason() {
        ManufacturingOrder order = new ManufacturingOrder(1L, 2L, 5);

        order.markAsCancelled(null);

        assertEquals(ManufacturingStatus.CANCELLED, order.getStatus());
        assertNull(order.getInfo());
    }

    @Test
    void updateStatus_shouldSetStatusAndInfo() {
        ManufacturingOrder order = new ManufacturingOrder(1L, 2L, 5);
        String customInfo = "Custom status update";

        order.updateStatus(ManufacturingStatus.IN_PROGRESS, customInfo);

        assertEquals(ManufacturingStatus.IN_PROGRESS, order.getStatus());
        assertEquals(customInfo, order.getInfo());
    }

    @Test
    void updateStatus_shouldSetDefaultInfoWhenBlank() {
        ManufacturingOrder order = new ManufacturingOrder(1L, 2L, 5);

        order.updateStatus(ManufacturingStatus.COMPLETED, "");

        assertEquals(ManufacturingStatus.COMPLETED, order.getStatus());
        assertEquals("Manufacturing completed successfully.", order.getInfo());
    }

    @Test
    void updateStatus_shouldSetInProgressDefaultInfo() {
        ManufacturingOrder order = new ManufacturingOrder(1L, 2L, 5);

        order.updateStatus(ManufacturingStatus.IN_PROGRESS, "  ");

        assertEquals(ManufacturingStatus.IN_PROGRESS, order.getStatus());
        assertEquals("Manufacturing is in progress.", order.getInfo());
    }

    @Test
    void updateStatus_shouldPreserveNullInfo() {
        ManufacturingOrder order = new ManufacturingOrder(1L, 2L, 5);

        order.updateStatus(ManufacturingStatus.PENDING, null);

        assertEquals(ManufacturingStatus.PENDING, order.getStatus());
        assertNull(order.getInfo());
    }


}