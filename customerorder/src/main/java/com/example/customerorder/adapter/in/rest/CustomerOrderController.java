package com.example.customerorder.adapter.in.rest;


import com.example.customerorder.adapter.dto.request.CancelCustomerOrderRequest;
import com.example.customerorder.adapter.dto.request.CreateCustomerOrderRequest;
import com.example.customerorder.adapter.dto.request.UpdateGlobalCustomerOrderStatusRequest;
import com.example.customerorder.application.port.in.CancelCustomerOrderUseCase;
import com.example.customerorder.application.port.in.CreateCustomerOrderUseCase;
import com.example.customerorder.application.port.in.GetCustomerOrderUseCase;
import com.example.customerorder.application.port.in.UpdateGlobalCustomerOrderStatusUseCase;
import com.example.customerorder.application.query.GetCustomerOrderQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer-order")
@RequiredArgsConstructor
public class CustomerOrderController {

    private final CreateCustomerOrderUseCase createCustomerOrderUseCase;
    private final GetCustomerOrderUseCase getCustomerOrderUseCase;
    private final CancelCustomerOrderUseCase cancelCustomerOrderUseCase;
    private final UpdateGlobalCustomerOrderStatusUseCase updateGlobalCustomerOrderStatusUseCase;


    @PostMapping("/create")
    public ResponseEntity<Void> createOrder(@RequestBody @Valid CreateCustomerOrderRequest request) {
        createCustomerOrderUseCase.createCustomerOrder(request.toCommand());
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerOrder(@PathVariable @Valid Long id) {
        GetCustomerOrderQuery query = new GetCustomerOrderQuery(id);
        return ResponseEntity.ok(getCustomerOrderUseCase.getCustomerOrder(query));
    }

    @PatchMapping("/cancel")
    public ResponseEntity<Void> cancelCustomerOrder(@RequestBody @Valid CancelCustomerOrderRequest request) {
        cancelCustomerOrderUseCase.cancelCustomerOrder(request.toCommand());
        return ResponseEntity.ok().build();

    }

    @Schema(description = "Status zamówienia")
    public enum OrderStatus {
        @Schema(description = "Zamówienie oczekujące")
        PENDING,

        @Schema(description = "Zamówienie potwierdzone")
        CONFIRMED,

        @Schema(description = "Zamówienie anulowane")
        CANCELLED,

        @Schema(description = "Zamówienie zrealizowane")
        COMPLETED
    }
    @PatchMapping("/update-status")
    public ResponseEntity<Void> updateCustomersManufacturingOrders(@RequestBody @Valid UpdateGlobalCustomerOrderStatusRequest request) {
        updateGlobalCustomerOrderStatusUseCase.updateGlobalCustomerOrderStatus(request.toCommand());
        return ResponseEntity.ok().build();
    }

}
