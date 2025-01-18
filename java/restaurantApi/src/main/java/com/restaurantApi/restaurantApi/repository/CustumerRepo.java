package com.restaurantApi.restaurantApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurantApi.restaurantApi.model.custumers.CustumerEntity;

@Repository
public interface CustumerRepo extends JpaRepository<CustumerEntity, Long> {
    
}
