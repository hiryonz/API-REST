package com.restaurantApi.restaurantApi.model.order_items;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.restaurantApi.restaurantApi.model.orders.OrdersEntity;
import com.restaurantApi.restaurantApi.model.plates.PlatesEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "order_item")
public class OrderItemsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_orderItem;

    //fk with orders
    @ManyToOne
    @JoinColumn(name = "id_orders")
    @JsonBackReference //evita ciclos infinitos
    private OrdersEntity orders;

    //fk with plates
    @ManyToOne
    @JoinColumn(name = "id_plates")
    private PlatesEntity plates;
}
