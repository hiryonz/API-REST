package com.restaurantApi.restaurantApi.service.orderService;

import java.util.List;
import java.util.Optional;

import com.restaurantApi.restaurantApi.model.orders.OrdersDto;
import com.restaurantApi.restaurantApi.model.orders.OrdersEntity;


public interface OrderService {

    OrdersDto convertToDto(OrdersEntity entity);

    OrdersEntity convertToEntity(OrdersDto dto);

    OrdersDto save(OrdersDto orders);

    boolean deleteById(Long id);

    Optional<OrdersDto> findById(Long id);

    List<OrdersDto> findAll();

    boolean isExistsOrders(Long id);
}
