package com.restaurantApi.restaurantApi.service.categoriesService;

import java.util.List;
import java.util.Optional;

import com.restaurantApi.restaurantApi.model.categories.CategoriesDto;
import com.restaurantApi.restaurantApi.model.categories.CategoriesEntity;

public interface CategoriesService {

    CategoriesDto convertToDto(CategoriesEntity entity);
    CategoriesEntity convertToEntity(CategoriesDto dto);

    CategoriesDto save(CategoriesDto categories);

    boolean deleteById(Long id);

    Optional<CategoriesDto> findById(Long id);

    List<CategoriesDto> findAll();
}
