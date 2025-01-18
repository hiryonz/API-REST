package com.restaurantApi.restaurantApi.model.orders;

import java.util.Date;
import java.util.List;

import com.restaurantApi.restaurantApi.model.custumers.CustumerEntity;
import com.restaurantApi.restaurantApi.model.order_items.OrderItemsEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "orders")
public class OrdersEntity {
    @Id
    private Long id_orders;
    private Date order_date;
    private double total;
    private int status; //enum (pendiente, preparacion, listo, entregado)

    //id_custumer fk
    @OneToMany
    @JoinColumn(name = "id_custumers")
    private CustumerEntity custumerEntity;

    //bidirectional fk with order_items
    @OneToOne(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemsEntity> orderItems;
}
