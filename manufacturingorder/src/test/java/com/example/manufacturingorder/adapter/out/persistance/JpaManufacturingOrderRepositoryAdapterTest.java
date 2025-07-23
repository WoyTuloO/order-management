package com.example.manufacturingorder.adapter.out.persistance;

import com.example.manufacturingorder.adapter.out.persistance.entity.ManufacturingOrderEntity;
import com.example.manufacturingorder.config.exceptions.ManufacturingResourceNotFound;
import com.example.manufacturingorder.domain.model.aggregate.ManufacturingOrder;
import com.example.manufacturingorder.domain.model.enums.ManufacturingStatus;
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
class JpaManufacturingOrderRepositoryAdapterTest {

    @Mock
    private SpringDataManufacturingOrderRepository manufacturingOrderRepository;

    @InjectMocks
    private JpaManufacturingOrderRepositoryAdapter repositoryAdapter;

    @Test
    void save_shouldDelegateToRepository() {
        ManufacturingOrder order = new ManufacturingOrder(null, 1L, 2L, 5,
                ManufacturingStatus.PENDING, "Test");
        ManufacturingOrderEntity entity = ManufacturingOrderEntity.fromDomain(order);

        repositoryAdapter.save(order);

        verify(manufacturingOrderRepository).save(entity);
    }

    @Test
    void saveAll_shouldDelegateToRepository() {
        ManufacturingOrder order1 = new ManufacturingOrder(null, 1L, 2L, 5,
                ManufacturingStatus.PENDING, "Test1");
        ManufacturingOrder order2 = new ManufacturingOrder(10L, 1L, 3L, 10,
                ManufacturingStatus.IN_PROGRESS, "Test2");
        List<ManufacturingOrder> orders = List.of(order1, order2);

        repositoryAdapter.save(orders);

        verify(manufacturingOrderRepository).saveAll(anyList());
    }

    @Test
    void findById_shouldReturnDomainObjectWhenFound() {
        Long orderId = 1L;
        ManufacturingOrderEntity entity = new ManufacturingOrderEntity();
        entity.setId(orderId);
        when(manufacturingOrderRepository.findById(orderId)).thenReturn(Optional.of(entity));

        ManufacturingOrder result = repositoryAdapter.findById(orderId);

        assertNotNull(result);
        assertEquals(orderId, result.getId());
    }

    @Test
    void findById_shouldThrowExceptionWhenNotFound() {
        Long orderId = 2L;
        when(manufacturingOrderRepository.findById(orderId))
                .thenReturn(Optional.empty());

        assertThrows(ManufacturingResourceNotFound.class, () ->
                repositoryAdapter.findById(orderId));
    }

    @Test
    void findByCustomerOrderId_shouldReturnDomainObjects() {
        Long customerOrderId = 1L;
        ManufacturingOrderEntity entity1 = new ManufacturingOrderEntity();
        entity1.setId(1L);
        ManufacturingOrderEntity entity2 = new ManufacturingOrderEntity();
        entity2.setId(2L);

        when(manufacturingOrderRepository.findAllByCustomerOrderId(customerOrderId)).thenReturn(List.of(entity1, entity2));

        List<ManufacturingOrder> results = repositoryAdapter.findByCustomerOrderId(customerOrderId);

        assertEquals(2, results.size());
        verify(manufacturingOrderRepository).findAllByCustomerOrderId(customerOrderId);
    }

    @Test
    void update_shouldUseFromDomainUpdated() {
        ManufacturingOrder order = new ManufacturingOrder(1L, 1L, 2L, 5, ManufacturingStatus.COMPLETED, "Updated");

        repositoryAdapter.update(order);

        verify(manufacturingOrderRepository).save(any(ManufacturingOrderEntity.class));
    }

    @Test
    void findByCustomerOrderId_shouldReturnEmptyListWhenNoneFound() {
        Long customerOrderId = 999L;
        when(manufacturingOrderRepository.findAllByCustomerOrderId(customerOrderId)).thenReturn(List.of());

        List<ManufacturingOrder> results = repositoryAdapter.findByCustomerOrderId(customerOrderId);

        assertTrue(results.isEmpty());
    }
}