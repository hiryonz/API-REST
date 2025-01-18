package com.restaurantApi.restaurantApi.model.orders;

import java.util.Date;

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

    //id_custumer fk
}
