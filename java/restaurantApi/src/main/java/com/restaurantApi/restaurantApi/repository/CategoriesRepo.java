package com.restaurantApi.restaurantApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurantApi.restaurantApi.model.categories.CategoriesEntity;

@Repository
public interface CategoriesRepo extends JpaRepository<CategoriesEntity, Long> {
    
}
