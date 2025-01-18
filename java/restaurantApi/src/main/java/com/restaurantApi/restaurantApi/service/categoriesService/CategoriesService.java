package com.restaurantApi.restaurantApi.service.categoriesService;

import java.util.List;
import java.util.Optional;

import com.restaurantApi.restaurantApi.model.categories.CategoriesDto;

public interface CategoriesService {
    CategoriesDto save(CategoriesDto categories);

    boolean deleteById(Long id);

    Optional<CategoriesDto> findById(Long id);

    List<CategoriesDto> findAll();
}
