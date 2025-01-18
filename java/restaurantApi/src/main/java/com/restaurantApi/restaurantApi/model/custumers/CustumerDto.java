package com.restaurantApi.restaurantApi.model.custumers;

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
}
