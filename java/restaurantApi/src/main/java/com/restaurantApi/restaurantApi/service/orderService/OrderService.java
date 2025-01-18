package com.restaurantApi.restaurantApi.service.orderService;

import java.util.List;
import java.util.Optional;

import com.restaurantApi.restaurantApi.model.orders.OrdersDto;


public interface OrderService {
    OrdersDto save(OrdersDto orders);

    boolean deleteById(String id);

    Optional<OrdersDto> findById(String id);

    List<OrdersDto> findAll();

    boolean isExistsOrders(String id);
}
