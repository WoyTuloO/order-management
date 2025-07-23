package com.example.manufacturingorder.adapter.dto.response;

import com.example.manufacturingorder.domain.model.aggregate.ManufacturingOrder;
import com.example.manufacturingorder.domain.model.enums.ManufacturingStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

public record GetManufacturingOrderResponse(
        Long id,
        Long customerOrderId,
        Long productId,
        int quantity,
        ManufacturingStatus status,
        @JsonInclude(JsonInclude.Include.NON_NULL)
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
