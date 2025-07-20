package com.example.customerorder.adapter.out.persistance;

import com.example.customerorder.adapter.out.persistance.entities.CustomerOrderEntity;
import com.example.customerorder.application.port.out.CustomerOrderRepositoryPort;
import com.example.customerorder.domain.model.aggregate.CustomerOrder;
import com.example.customerorder.domain.model.valueobject.OrderItem;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class JpaCustomerOrderRepositoryAdapter implements CustomerOrderRepositoryPort {

    private final SpringDataCustomerOrderRepository orderRepository;

    public JpaCustomerOrderRepositoryAdapter(SpringDataCustomerOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Long save(CustomerOrder order) {
        CustomerOrderEntity customerOrderEntity = CustomerOrderEntity.fromDomain(order);
        return orderRepository.save(customerOrderEntity).getId();
    }

    @Override
    public CustomerOrder findById(Long id) {
        CustomerOrderEntity customerOrderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer order not found with id: " + id));

        List<OrderItem> items = customerOrderEntity.getItems().stream()
                .map(item -> new OrderItem(item.getProductId(), item.getQuantity()))
                .toList();
        return new CustomerOrder(customerOrderEntity.getId(), customerOrderEntity.getCustomerId(), items, customerOrderEntity.getStatus(), customerOrderEntity.getInfo());
    }
}
