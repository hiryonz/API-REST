package com.restaurantApi.restaurantApi.service.categoriesService.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.restaurantApi.restaurantApi.model.categories.CategoriesDto;
import com.restaurantApi.restaurantApi.model.categories.CategoriesEntity;
import com.restaurantApi.restaurantApi.repository.CategoriesRepo;
import com.restaurantApi.restaurantApi.service.categoriesService.CategoriesService;

public class CategoriesServiceImpl implements CategoriesService {
    
    @Autowired
    private CategoriesRepo categoriesRepo;

    @Override
    public CategoriesDto convertToDto(CategoriesEntity categoriesEntity) {
        return CategoriesDto.builder()
        .id_category(categoriesEntity.getId_category())
        .name(categoriesEntity.getName())
        .description(categoriesEntity.getDescription())
        .build();
    }

    @Override
    public CategoriesEntity convertToEntity(CategoriesDto categoriesDto) {
        return CategoriesEntity.builder()
        .id_category(categoriesDto.getId_category())
        .name(categoriesDto.getName())
        .description(categoriesDto.getDescription())
        .build();
    }

    @Override
    public CategoriesDto save(CategoriesDto categories) {
        final CategoriesEntity categoriesEntity = convertToEntity(categories);
        final CategoriesEntity savedCategories = categoriesRepo.save(categoriesEntity);

        return convertToDto(savedCategories);
    }

    @Override
    public boolean deleteById(Long id) {
        final Optional<CategoriesEntity> categoriesEntity = categoriesRepo.findById(id);

        if(categoriesEntity.isEmpty()) {
            return false;
        }
        
        categoriesRepo.deleteById(id);
        return true;


    }

    @Override
    public Optional<CategoriesDto> findById(Long id) {
        final Optional<CategoriesEntity> categoriesEntity = categoriesRepo.findById(id);

        final Optional<CategoriesDto> categoriesDto = categoriesEntity.map(category -> convertToDto(category));
        return categoriesDto;
    }

    @Override
    public List<CategoriesDto> findAll() {
        final List<CategoriesEntity> categoriesEntity = categoriesRepo.findAll();
        final List<CategoriesDto> categoriesDtos = categoriesEntity
        .stream()
        .map(category -> convertToDto(category))
        .collect(Collectors.toList());

        return categoriesDtos;
    }



}
