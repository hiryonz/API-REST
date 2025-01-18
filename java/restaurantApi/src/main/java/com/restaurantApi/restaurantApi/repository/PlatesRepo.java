package com.restaurantApi.restaurantApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurantApi.restaurantApi.model.plates.PlatesEntity;

@Repository
public interface PlatesRepo extends JpaRepository<PlatesEntity, Long> {
    
}
