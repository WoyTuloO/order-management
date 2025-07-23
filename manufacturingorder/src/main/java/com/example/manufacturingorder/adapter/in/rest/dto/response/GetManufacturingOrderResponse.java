package com.example.manufacturingorder.adapter.in.rest.dto.response;

import com.example.manufacturingorder.domain.model.aggregate.ManufacturingOrder;
import com.example.manufacturingorder.domain.model.enums.ManufacturingStatus;

public record GetManufacturingOrderResponse(
        Long id,
        Long customerOrderId,
        Long productId,
        int quantity,
        ManufacturingStatus status,
        String info
) {
    public static GetManufacturingOrderResponse fromDomain(ManufacturingOrder manufacturingOrder){
        if (manufacturingOrder == null) {
            return null;
        }
        return new GetManufacturingOrderResponse(manufacturingOrder.getId(),
                manufacturingOrder.getCustomerOrderId(),
                manufacturingOrder.getProductId(),
                manufacturingOrder.getRequiredQuantity(),
                manufacturingOrder.getStatus(),
                manufacturingOrder.getInfo()
        );
    }
}
