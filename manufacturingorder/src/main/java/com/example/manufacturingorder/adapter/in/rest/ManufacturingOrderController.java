package com.example.manufacturingorder.adapter.in.rest;

import com.example.manufacturingorder.adapter.in.rest.dto.CreateManufacturingOrderRequest;
import com.example.manufacturingorder.adapter.in.rest.dto.ManufacturingOrderResponse;
import com.example.manufacturingorder.application.port.in.CreateManufacturingOrderUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManufacturingOrderController {

    private final CreateManufacturingOrderUseCase createManufacturingOrderUseCase;

    @Autowired
    public ManufacturingOrderController(CreateManufacturingOrderUseCase createManufacturingOrderUseCase) {
        this.createManufacturingOrderUseCase = createManufacturingOrderUseCase;
    }

    @PostMapping("/production-orders")
    public ResponseEntity<Void> createOrder(@RequestBody @Valid CreateManufacturingOrderRequest request) {
        createManufacturingOrderUseCase.createManufacturingOrder(request.toCommand());
        return ResponseEntity.ok().build();
    }
}
