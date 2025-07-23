package com.example.customerorder.application.port.in;

import com.example.customerorder.application.query.GetCustomerOrderQuery;
import com.example.customerorder.adapter.dto.response.GetCustomerOrderResponse;

public interface GetCustomerOrderUseCase {
    GetCustomerOrderResponse getCustomerOrder(GetCustomerOrderQuery query);
}
