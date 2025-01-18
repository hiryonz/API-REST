package com.restaurantApi.restaurantApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurantApi.restaurantApi.model.order_items.OrderItemsEntity;

@Repository
public interface OrderItemsRepo extends JpaRepository<OrderItemsEntity, Long>{
    
}
