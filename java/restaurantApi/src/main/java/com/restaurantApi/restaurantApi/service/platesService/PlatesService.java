package com.restaurantApi.restaurantApi.service.platesService;

import java.util.List;
import java.util.Optional;

import com.restaurantApi.restaurantApi.model.plates.PlatesDto;

public interface PlatesService {
    PlatesDto save(PlatesDto orders);

    boolean deleteById(String id);

    Optional<PlatesDto> findById(String id);

    List<PlatesDto> findAll();

    boolean isExistsPlates(String id);
}
