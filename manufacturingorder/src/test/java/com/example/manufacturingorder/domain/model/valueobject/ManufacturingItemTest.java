package com.example.manufacturingorder.domain.model.valueobject;

import com.example.manufacturingorder.config.exceptions.ManufacturingResourceNotFound;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ManufacturingItemTest {

    @Test
    void shouldCreateValidManufacturingItem() {
        ManufacturingItem item = new ManufacturingItem(101L, 5);

        assertEquals(101L, item.componentId());
        assertEquals(5, item.requiredQuantity());
    }

    @ParameterizedTest
    @ValueSource(longs = {0, -1, -100})
    void shouldRejectInvalidComponentIds(long invalidId) {
        ManufacturingResourceNotFound exception = assertThrows(
                ManufacturingResourceNotFound.class,
                () -> new ManufacturingItem(invalidId, 1)
        );

        assertEquals("Component ID must be a positive number.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -10})
    void shouldRejectInvalidQuantities(int invalidQuantity) {

        ManufacturingResourceNotFound exception = assertThrows(
                ManufacturingResourceNotFound.class,
                () -> new ManufacturingItem(1L, invalidQuantity)
        );

        assertEquals("Required quantity must be a positive number.", exception.getMessage());
    }

}