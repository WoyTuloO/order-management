package com.example.manufacturingorder.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class ManufacturingOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private UUID id;
    private UUID customerOrderId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ManufacturingItem> items;
    private ManufacturingStatus status;

}
