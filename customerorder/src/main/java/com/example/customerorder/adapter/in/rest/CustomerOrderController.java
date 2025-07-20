package com.example.customerorder.adapter.in.rest;


import com.example.customerorder.adapter.dto.request.CreateCustomerOrderRequest;
import com.example.customerorder.application.command.CreateCustomerOrderCommand;
import com.example.customerorder.application.port.in.CreateCustomerOrderUseCase;
import com.example.customerorder.application.port.in.GetCustomerOrderUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer-order")
@RequiredArgsConstructor
public class CustomerOrderController {

    private final CreateCustomerOrderUseCase createCustomerOrderUseCase;
    private final GetCustomerOrderUseCase getCustomerOrderUseCase;


    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody @Valid CreateCustomerOrderRequest request) {
        createCustomerOrderUseCase.createCustomerOrder(request.toCommand());
        return ResponseEntity.ok().build();
    }


}
