package com.example.customerorder.config;

import com.example.customerorder.domain.model.OrderItem;
import com.example.manufacturingorder.adapter.in.rest.dto.ManufacturingItemDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    ManufacturingItemDto toManufacturingItemDto(OrderItem orderItem);
    List<ManufacturingItemDto> toManufacturingItemDtoList(List<OrderItem> orderItems);
}
