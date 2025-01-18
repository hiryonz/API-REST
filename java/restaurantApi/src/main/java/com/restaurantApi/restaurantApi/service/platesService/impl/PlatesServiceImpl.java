package com.restaurantApi.restaurantApi.service.platesService.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurantApi.restaurantApi.model.plates.PlatesDto;
import com.restaurantApi.restaurantApi.model.plates.PlatesEntity;
import com.restaurantApi.restaurantApi.repository.PlatesRepo;
import com.restaurantApi.restaurantApi.service.categoriesService.CategoriesService;
import com.restaurantApi.restaurantApi.service.platesService.PlatesService;

@Service
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
    public PlatesDto save(PlatesDto plates) {
        PlatesEntity platesEntity = convertToEntity(plates);
        PlatesEntity savedPlate = platesRepo.save(platesEntity);

        return convertToDto(savedPlate);
    }

    @Override
    public boolean deleteById(Long id) {
        if (!this.isExistsPlates(id)) {
            return false;
        }

        platesRepo.deleteById(id);
        return true;
    }

    @Override
    public Optional<PlatesDto> findById(Long id) {
        Optional<PlatesEntity> platesEntity = platesRepo.findById(id);
        Optional<PlatesDto> platesDto = platesEntity.map(plate -> convertToDto(plate));

        return platesDto;
    }

    @Override
    public List<PlatesDto> findAll() {
        List<PlatesEntity> platesEntities = platesRepo.findAll();
        List<PlatesDto> platesDtos = platesEntities.stream()
            .map(plate -> convertToDto(plate))
            .collect(Collectors.toList());

        return platesDtos;
    }

    @Override
    public boolean isExistsPlates(Long id) {
        PlatesEntity platesEntity = platesRepo.findById(id).orElse(null);

        if (platesEntity == null) {
            return false;
        }

        return true;
    }



}

