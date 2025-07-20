package com.example.customerorder.application.query.handler;


import com.example.customerorder.application.port.in.GetCustomerOrderUseCase;
import com.example.customerorder.application.port.out.CustomerOrderRepositoryPort;
import com.example.customerorder.application.query.GetCustomerOrderQuery;
import com.example.customerorder.application.query.GetCustomerOrderResponse;
import com.example.customerorder.domain.model.aggregate.CustomerOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCustomerOrderHandler implements GetCustomerOrderUseCase {

    private final CustomerOrderRepositoryPort customerOrderRepositoryPort;

    @Override
    public GetCustomerOrderResponse getCustomerOrder(GetCustomerOrderQuery query) {
        CustomerOrder order = customerOrderRepositoryPort.findById(query.id());
        return new GetCustomerOrderResponse(
                order.getId(),
                order.getCustomerId(),
                order.getItems(),
                order.getStatus(),
                order.getInfo()
        );
    }
}
