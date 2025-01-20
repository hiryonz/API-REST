package com.restaurantApi.restaurantApi.model.custumers;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.restaurantApi.restaurantApi.model.orders.OrdersEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_custumer;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private int phone;

    @Column(nullable = false)
    private String address;


    //bidireccional con orders para quye un custumer pueda ver todos sus ordenes
    @OneToMany(mappedBy = "custumer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference //indica que se manejara como json
    private List<OrdersEntity> orders;
}
