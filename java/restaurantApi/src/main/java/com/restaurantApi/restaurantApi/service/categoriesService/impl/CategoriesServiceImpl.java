package com.restaurantApi.restaurantApi.service.categoriesService.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurantApi.restaurantApi.model.categories.CategoriesDto;
import com.restaurantApi.restaurantApi.model.categories.CategoriesEntity;
import com.restaurantApi.restaurantApi.repository.CategoriesRepo;
import com.restaurantApi.restaurantApi.service.categoriesService.CategoriesService;

@Service
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

        if(!this.isExistsCategories(id)) {
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

    @Override
    public boolean isExistsCategories(Long id) {
        CategoriesEntity categoriesEntity = categoriesRepo.findById(id).orElse(null);

        if (categoriesEntity == null) {
            return false;
        }

        return true;
    }


}
