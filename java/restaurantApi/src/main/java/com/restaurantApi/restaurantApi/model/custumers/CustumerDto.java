package com.restaurantApi.restaurantApi.model.custumers;

import java.util.List;

import com.restaurantApi.restaurantApi.model.orders.OrdersDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustumerDto {
    private Long id_custumer;
    private String name;
    private String email;
    private int phone;
    private String address;
    private List<OrdersDto> orders;
}
