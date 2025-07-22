package com.example.customerorder.application.command.cancelCustomerOrder.handler;

import com.example.customerorder.adapter.out.external.ManufacturingOrderFacadeAdapter;
import com.example.customerorder.application.command.cancelCustomerOrder.CancelCustomerOrderCommand;
import com.example.customerorder.application.port.in.CancelCustomerOrderUseCase;
import com.example.customerorder.application.port.out.CustomerOrderRepositoryPort;
import com.example.customerorder.domain.model.aggregate.CustomerOrder;
import com.example.manufacturingorder.adapter.in.rest.dto.response.GetManufacturingOrderResponse;
import com.example.manufacturingorder.domain.event.CancelManufacturingOrdersEvent;
import com.example.manufacturingorder.domain.model.enums.ManufacturingStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CancelCustomerOrderHandler implements CancelCustomerOrderUseCase {

    private final CustomerOrderRepositoryPort customerOrderRepository;
    private final ManufacturingOrderFacadeAdapter manufacturingOrdersFacadeAdapter;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void cancelCustomerOrder(CancelCustomerOrderCommand cancelCustomerOrderCommand) {


        List<GetManufacturingOrderResponse> manufacturingOrders = manufacturingOrdersFacadeAdapter.getCustomerOrdersManufacturingOrders(cancelCustomerOrderCommand.customerOrderId());
        boolean isCancelable = manufacturingOrders.stream()
                .allMatch(o -> o.status() == ManufacturingStatus.PENDING || o.status() == ManufacturingStatus.CANCELLED);

        if(isCancelable){
            // tu jakby co chodzi o to ze jesli wszystkie zamowienia sa jeszcze pending lub cancelled
            // to mozna anulowac caly customer order - jak jakies jest juz confirmed to nie mozna anulowac calego - wiec zostaje o statusie
            // jaki aktualnie ma - natomiast pozniej mozna dac event zeby te manufacturing ordery ktore sa pending anulowac
            // tak zeby kilent mogl anulowac czesciowo swoje zamowienie
            CustomerOrder customerOrder = customerOrderRepository.findById(cancelCustomerOrderCommand.customerOrderId());
            customerOrder.cancel("Order cancelled by customer");
            customerOrderRepository.save(customerOrder);
        }


        eventPublisher.publishEvent(
                new CancelManufacturingOrdersEvent(cancelCustomerOrderCommand.customerOrderId(), "Cancelled by customer order")
        );





    }
}
