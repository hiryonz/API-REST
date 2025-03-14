package com.restaurantApi.restaurantApi.service.platesService;

import java.util.List;
import java.util.Optional;

import com.restaurantApi.restaurantApi.model.plates.PlatesDto;
import com.restaurantApi.restaurantApi.model.plates.PlatesEntity;

public interface PlatesService {

    PlatesDto convertToDto(PlatesEntity entity);
    
    PlatesEntity convertToEntity(PlatesDto dto);

    PlatesDto save(PlatesDto orders);

    boolean deleteById(Long id);

    Optional<PlatesDto> findById(Long id);

    List<PlatesDto> findAll();

    boolean isExistsPlates(Long id);
}
