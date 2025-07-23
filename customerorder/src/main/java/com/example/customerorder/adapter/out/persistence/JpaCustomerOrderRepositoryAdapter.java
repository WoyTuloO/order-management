package com.example.customerorder.adapter.out.persistence;

import com.example.customerorder.adapter.out.persistence.entities.CustomerOrderEntity;
import com.example.customerorder.domain.port.CustomerOrderRepositoryPort;
import com.example.customerorder.config.exceptions.CustomerResourceNotFound;
import com.example.customerorder.domain.model.aggregate.CustomerOrder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
public class JpaCustomerOrderRepositoryAdapter implements CustomerOrderRepositoryPort {

    private final SpringDataCustomerOrderRepository orderRepository;

    public JpaCustomerOrderRepositoryAdapter(SpringDataCustomerOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @Transactional
    @Override
    public Long save(CustomerOrder order) {
        CustomerOrderEntity customerOrderEntity = CustomerOrderEntity.fromDomain(order);
        return orderRepository.save(customerOrderEntity).getId();
    }

    @Override
    public CustomerOrder findById(Long id) {
        CustomerOrderEntity customerOrderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new CustomerResourceNotFound("Customer order not found with id: " + id));

        return CustomerOrderEntity.toDomain(customerOrderEntity);
    }


    @Transactional
    @Override
    public void update(CustomerOrder order) {

        CustomerOrderEntity entity = orderRepository.findById(order.getId())
                .orElseThrow(() -> new CustomerResourceNotFound("Customer order not found with id: " + order.getId()));

        entity.setStatus(order.getStatus());
        entity.setInfo(order.getInfo());

        orderRepository.save(entity);

    }
}
