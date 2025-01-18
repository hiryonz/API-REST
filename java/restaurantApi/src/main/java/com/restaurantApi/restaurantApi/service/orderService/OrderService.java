package com.restaurantApi.restaurantApi.service.orderService;

import java.util.List;
import java.util.Optional;

import com.restaurantApi.restaurantApi.model.order_items.OrderItemsDto;
import com.restaurantApi.restaurantApi.model.order_items.OrderItemsEntity;
import com.restaurantApi.restaurantApi.model.orders.OrdersDto;
import com.restaurantApi.restaurantApi.model.orders.OrdersEntity;


public interface OrderService {

    OrdersDto convertToDto(OrdersEntity entity);

    OrdersEntity convertToEntity(OrdersDto dto);

    OrdersDto save(OrdersDto orders);

    boolean deleteById(String id);

    Optional<OrdersDto> findById(String id);

    List<OrdersDto> findAll();

    boolean isExistsOrders(String id);
}
