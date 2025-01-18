package com.restaurantApi.restaurantApi.model.custumers;

import java.util.List;

import com.restaurantApi.restaurantApi.model.orders.OrdersEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder

@Data
@Entity
@Table(name = "custumer")
public class CustumerEntity {
    @Id
    private Long id_custumer;
    private String name;
    private String email;
    private int phone;
    private String address;


    //bidireccional con orders para quye un custumer pueda ver todos sus ordenes
    @OneToMany(mappedBy = "custumer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdersEntity> orders;
}
