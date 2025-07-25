package com.example.manufacturingorder.adapter.in.rest;

import com.example.manufacturingorder.adapter.dto.request.CancelManufacturingOrderRequest;
import com.example.manufacturingorder.adapter.dto.request.CreateManufacturingOrderRequest;
import com.example.manufacturingorder.adapter.dto.request.UpdateManufacturingOrderStatusRequest;
import com.example.manufacturingorder.application.port.in.*;
import com.example.manufacturingorder.application.query.getManufacturingOrder.GetManufacturingOrderQuery;
import com.example.manufacturingorder.application.query.getCustomersManufacturingOrders.GetCustomersManufacturingOrdersQuery;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/manufacturing-orders")
@RequiredArgsConstructor()
public class ManufacturingOrderController {

    private final CreateManufacturingOrderUseCase createManufacturingOrderUseCase;
    private final GetManufacturingOrderUseCase getManufacturingOrderUseCase;
    private final GetCustomersManufacturingOrdersUseCase getManufacturingOrdersUseCase;
    private final CancelManufacturingOrderUseCase cancelManufacturingOrderUseCase;
    private final UpdateManufacturingOrderStatusUseCase updateManufacturingOrderStatusUseCase;

    @PostMapping("/create")
    public ResponseEntity<Void> createOrder(@RequestBody @Valid CreateManufacturingOrderRequest request) {
        createManufacturingOrderUseCase.createManufacturingOrder(request.toCommand());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getManufacturingOrder(@PathVariable @Valid Long id) {
        GetManufacturingOrderQuery getManufacturingOrderQuery = new GetManufacturingOrderQuery(id);
        return ResponseEntity.ok(getManufacturingOrderUseCase.getManufacturingOrder(getManufacturingOrderQuery));
    }

    @GetMapping("/customer-order/{id}")
    public ResponseEntity<?> getManufacturingOrdersByCustomerOrderId(@PathVariable @Valid Long id) {
        GetCustomersManufacturingOrdersQuery getManufacturingOrdersQuery = new GetCustomersManufacturingOrdersQuery(id);
        return ResponseEntity.ok(getManufacturingOrdersUseCase.getManufacturingOrders(getManufacturingOrdersQuery));
    }

    @PatchMapping("/cancel")
    public ResponseEntity<Void> cancelManufacturingOrder(@RequestBody @Valid CancelManufacturingOrderRequest request){
        cancelManufacturingOrderUseCase.cancelManufacturingOrder(request.toCommand());
        return ResponseEntity.ok().build();
    }



    @Operation(
            summary = "Zmień status zamówienia",
            description = "Pozwala zmienić status lub automatycznie dać zamówienie na COMPLETED jeśli wszystkie podległe ordery również zostały zakończone. " +
                    "Wartości statusu: PENDING, IN_PROGRESS, COMPLETED, FAILED, CANCELLED "
    )
    @PatchMapping("/update-status")
    public ResponseEntity<Void> updateManufacturingOrder(@RequestBody @Valid UpdateManufacturingOrderStatusRequest request) {
        updateManufacturingOrderStatusUseCase.updateManufacturingOrderStatus(request.toCommand());
        return ResponseEntity.ok().build();
    }

}
