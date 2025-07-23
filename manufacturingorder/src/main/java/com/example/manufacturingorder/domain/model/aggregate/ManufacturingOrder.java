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
    private final Long productId;
    private int requiredQuantity;
    private ManufacturingStatus status;
    private String info;

    public ManufacturingOrder(Long customerOrderId, Long productId, int requiredQuantity) {
        this.customerOrderId = customerOrderId;
        this.productId = productId;
        this.requiredQuantity = requiredQuantity;
        this.status = ManufacturingStatus.PENDING;
        this.info = null;
    }

    public static List<ManufacturingOrder> create(Long id, List<ManufacturingItem> items) {
        return items.stream()
                .map(item -> new ManufacturingOrder(
                        id,
                        item.componentId(),
                        item.requiredQuantity()))
                .toList();
    }

    public static ManufacturingOrder create(Long id, ManufacturingItem item) {
        return new ManufacturingOrder(id, item.componentId(), item.requiredQuantity());
    }

    public void markAsFailed(String reason) {
        this.status = ManufacturingStatus.FAILED;
        info = reason;
    }

    public void markAsCompleted() {
        this.status = ManufacturingStatus.COMPLETED;
        info = "Manufacturing completed successfully.";
    }

    public void markAsCancelled(String reason) {
        this.status = ManufacturingStatus.CANCELLED;
        info = reason;
    }

    public void markAsInProgress() {
        this.status = ManufacturingStatus.IN_PROGRESS;
        info = "Manufacturing is in progress.";
    }

    public void updateStatus(ManufacturingStatus manufacturingStatus, String info) {
        String infoString = info;
        if(infoString.isBlank()) {
            if (manufacturingStatus == ManufacturingStatus.COMPLETED) {
                infoString = "Manufacturing completed successfully.";
            } else if (manufacturingStatus == ManufacturingStatus.IN_PROGRESS) {
                infoString = "Manufacturing is in progress.";
            }
        }
        this.status = manufacturingStatus;
        this.info = infoString;

    }
}
