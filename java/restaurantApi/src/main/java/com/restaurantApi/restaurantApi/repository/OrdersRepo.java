package com.restaurantApi.restaurantApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurantApi.restaurantApi.model.orders.OrdersEntity;

@Repository
public interface OrdersRepo extends JpaRepository<OrdersEntity, Long>{
    
}
