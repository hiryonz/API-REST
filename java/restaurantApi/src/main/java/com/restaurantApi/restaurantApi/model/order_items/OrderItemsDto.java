package com.restaurantApi.restaurantApi.model.order_items;

import com.restaurantApi.restaurantApi.model.orders.OrdersDto;
import com.restaurantApi.restaurantApi.model.plates.PlatesDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemsDto {
    private Long id_orderItem;

    //fk with orders
    private OrdersDto ordersDto;

    //fk with plates
    private PlatesDto platesDto;
}
