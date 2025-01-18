package com.restaurantApi.restaurantApi.service.platesService.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.restaurantApi.restaurantApi.model.plates.PlatesDto;
import com.restaurantApi.restaurantApi.model.plates.PlatesEntity;
import com.restaurantApi.restaurantApi.repository.PlatesRepo;
import com.restaurantApi.restaurantApi.service.categoriesService.CategoriesService;
import com.restaurantApi.restaurantApi.service.platesService.PlatesService;

public class PlatesServiceImpl implements PlatesService {
    
    @Autowired
    private PlatesRepo platesRepo;

    @Autowired
    private CategoriesService categoriesService;

    @Override
    public PlatesDto convertToDto(PlatesEntity entity) {

        if (entity == null) { return null; }


        return PlatesDto.builder()
        .id_plates(entity.getId_plates())
        .name(entity.getName())
        .description(entity.getDescription())
        .price(entity.getPrice())
        .image(entity.getImage())
        .availability(entity.getAvailability())
        .categoriesDto(categoriesService.convertToDto(entity.getCategoriesEntity()))
        .build();
    }

    @Override
    public PlatesEntity convertToEntity(PlatesDto dto) {

        if (dto == null) { return null; }

        return PlatesEntity.builder()
        .id_plates(dto.getId_plates())
        .name(dto.getName())
        .description(dto.getDescription())
        .price(dto.getPrice())
        .image(dto.getImage())
        .availability(dto.getAvailability())
        .categoriesEntity(categoriesService.convertToEntity(dto.getCategoriesDto()))
        .build();
    }

    @Override
    public PlatesDto save(PlatesDto orders) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public boolean deleteById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public Optional<PlatesDto> findById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<PlatesDto> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public boolean isExistsPlates(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isExistsPlates'");
    }
}

