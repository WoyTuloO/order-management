package com.example.manufacturingorder.domain.model.aggregate;

import com.example.manufacturingorder.domain.model.enums.ManufacturingStatus;
import com.example.manufacturingorder.domain.model.valueobject.ManufacturingItem;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ManufacturingOrder {
    private Long id;
    private final Long customerOrderId;
    private final List<ManufacturingItem> items;
    private ManufacturingStatus status;
    private String info;

    public ManufacturingOrder(Long customerOrderId, List<ManufacturingItem> items) {
        this.customerOrderId = customerOrderId;
        this.items = items;
        this.status = ManufacturingStatus.PENDING;
        this.info = null;
    }

    public static ManufacturingOrder create(Long customerOrderId, List<ManufacturingItem> items) {
        return new ManufacturingOrder(customerOrderId, items);
    }

    public void markAsFailed(String reason) {
        this.status = ManufacturingStatus.FAILED;
        info = reason;
    }
}
