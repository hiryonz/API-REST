package com.restaurantApi.restaurantApi.model.orders;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.restaurantApi.restaurantApi.model.custumers.CustumerEntity;
import com.restaurantApi.restaurantApi.model.order_items.OrderItemsEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "orders")
public class OrdersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_orders;

    @Column(nullable = false)
    private Date order_date;

    @Column(nullable = false)
    private double total;

    @Column(nullable = false)
    private int status; //enum (pendiente, preparacion, listo, entregado)

    //id_custumer fk
    @ManyToOne
    @JoinColumn(name = "id_custumer")
    @JsonBackReference //evita ciclo de serializacion
    private CustumerEntity custumer;

    //bidirectional fk with order_items
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemsEntity> orderItems;
}
