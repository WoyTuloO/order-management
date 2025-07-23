package com.example.customerorder.adapter.out.persistence;


import com.example.customerorder.adapter.out.persistence.entities.CustomerOrderEntity;
import com.example.customerorder.config.exceptions.CustomerResourceNotFound;
import com.example.customerorder.domain.model.aggregate.CustomerOrder;
import com.example.customerorder.domain.model.enums.OrderStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JpaCustomerOrderRepositoryAdapterTest {

    @Mock
    private SpringDataCustomerOrderRepository orderRepository;

    @InjectMocks
    private JpaCustomerOrderRepositoryAdapter repositoryAdapter;

    @Test
    void save_shouldConvertAndSaveEntity() {
        CustomerOrder order = CustomerOrder.recreate(
                1L, 100L, List.of(), OrderStatus.CONFIRMED, "test");
        CustomerOrderEntity savedEntity = CustomerOrderEntity.fromDomain(order);
        savedEntity.setId(1L);

        when(orderRepository.save(any(CustomerOrderEntity.class))).thenReturn(savedEntity);

        Long resultId = repositoryAdapter.save(order);

        assertEquals(1L, resultId);
        verify(orderRepository, times(1)).save(any(CustomerOrderEntity.class));
    }

    @Test
    void findById_shouldReturnDomainObjectWhenFound() {
        Long orderId = 1L;
        CustomerOrderEntity entity = new CustomerOrderEntity();
        entity.setId(orderId);
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(entity));

        CustomerOrder result = repositoryAdapter.findById(orderId);

        assertNotNull(result);
        assertEquals(orderId, result.getId());
    }

    @Test
    void findById_shouldThrowExceptionWhenNotFound() {
        Long orderId = 2L;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThrows(CustomerResourceNotFound.class, () ->
            repositoryAdapter.findById(orderId)
        );
    }

    @Test
    void update_shouldUpdateExistingEntity() {
        Long orderId = 1L;
        CustomerOrder order = CustomerOrder.recreate(
                orderId, 1L, List.of(), OrderStatus.COMPLETED, "Updated info");

        CustomerOrderEntity existingEntity = new CustomerOrderEntity();
        existingEntity.setId(orderId);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingEntity));
        when(orderRepository.save(any(CustomerOrderEntity.class))).thenReturn(existingEntity);

        repositoryAdapter.update(order);

        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, times(1)).save(existingEntity);
        assertEquals(OrderStatus.COMPLETED, existingEntity.getStatus());
        assertEquals("Updated info", existingEntity.getInfo());

    }

    @Test
    void update_shouldThrowExceptionWhenOrderNotFound() {
        Long orderId = 999L;
        CustomerOrder order = CustomerOrder.recreate(
                orderId, 100L, List.of(), OrderStatus.CONFIRMED, "Test");

        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThrows(CustomerResourceNotFound.class, () ->
            repositoryAdapter.update(order)
        );
        verify(orderRepository, never()).save(any());
    }


}