package com.example.manufacturingorder.domain.model.valueobject;


import com.example.manufacturingorder.config.exceptions.ManufacturingResourceNotFound;

//to jest po to zeby jakby sie okazalo ze ten item zostal np wycofany to zeby dalo sie zastapic go bez ingerowania w customerorder - tam jest orderItem
// po prostu mozna zrobic podmianke id w  manufacturingOrder
public record ManufacturingItem(
        Long componentId,
        int requiredQuantity
) {
    public ManufacturingItem {
        if (componentId == null || componentId <= 0) {
            throw new ManufacturingResourceNotFound("Component ID must be a positive number.");
        }
        if (requiredQuantity <= 0) {
            throw new ManufacturingResourceNotFound("Required quantity must be a positive number.");
        }
    }
}
