package com.restaurantApi.restaurantApi.service.custumerService;

import java.util.List;
import java.util.Optional;

import com.restaurantApi.restaurantApi.model.custumers.CustumerDto;
import com.restaurantApi.restaurantApi.model.custumers.CustumerEntity;

public interface CustumerService {

    CustumerDto convertToDto(CustumerEntity entity);

    CustumerEntity convertToEntity(CustumerDto dto);

    CustumerDto save(CustumerDto custumer);

    boolean deleteById(Long id);

    Optional<CustumerDto> findById(Long id);

    List<CustumerDto> findAll();

    boolean isExistsCustumer(Long id);

}
