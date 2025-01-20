package com.restaurantApi.restaurantApi.service.platesService.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurantApi.restaurantApi.model.categories.CategoriesDto;
import com.restaurantApi.restaurantApi.model.categories.CategoriesEntity;
import com.restaurantApi.restaurantApi.model.plates.PlatesDto;
import com.restaurantApi.restaurantApi.model.plates.PlatesEntity;
import com.restaurantApi.restaurantApi.repository.PlatesRepo;
import com.restaurantApi.restaurantApi.service.categoriesService.CategoriesService;
import com.restaurantApi.restaurantApi.service.platesService.PlatesService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PlatesServiceImpl implements PlatesService {
    
    @Autowired
    private PlatesRepo platesRepo;

    @Autowired
    private CategoriesService categoriesService;

    @Override
    public PlatesDto convertToDto(PlatesEntity entity) {

        if (entity == null) { return null; }
        Long id_categories = null;
        if(entity.getCategories() != null) {
             id_categories = entity.getCategories().getId_category();
        }else {

        }
        return PlatesDto.builder()
        .id_plates(entity.getId_plates())
        .name(entity.getName())
        .description(entity.getDescription())
        .price(entity.getPrice())
        .image(entity.getImage())
        .availability(entity.getAvailability())
        .id_categories(id_categories)
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
        .build();
    }

    @Override
    public PlatesDto save(PlatesDto plates) {

            // Validar que el DTO no sea null
            if (plates == null) {
                throw new IllegalArgumentException("El plato no puede ser null");
            }

            // Validar que el categories_id no sea null
            if (plates.getId_categories() == null) {
                throw new IllegalArgumentException("El ID de categoría es requerido");
            }

        CategoriesDto categoryDto = categoriesService.findById(plates.getId_categories())
        .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada con ID: " + plates.getId_categories()));
    
        PlatesEntity platesEntity = convertToEntity(plates);
        platesEntity.setCategories(categoriesService.convertToEntity(categoryDto));
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

