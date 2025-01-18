package com.restaurantApi.restaurantApi.model.orders;

import java.util.Date;
import java.util.List;

import com.restaurantApi.restaurantApi.model.custumers.CustumerDto;
import com.restaurantApi.restaurantApi.model.order_items.OrderItemsDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersDto {
    private Long id_orders;
    private Date order_date;
    private double total;
    private int status; //enum (pendiente, preparacion, listo, entregado)
    private List<OrderItemsDto> order_items;
    //id_custumer fk
    private CustumerDto custumerDto;
}
