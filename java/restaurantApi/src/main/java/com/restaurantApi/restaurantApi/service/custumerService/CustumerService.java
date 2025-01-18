package com.restaurantApi.restaurantApi.service.custumerService;

import java.util.List;
import java.util.Optional;

import com.restaurantApi.restaurantApi.model.custumers.CustumerDto;

public interface CustumerService {

    CustumerDto save(CustumerDto custumer);

    boolean deleteById(Long id);

    Optional<CustumerDto> findById(Long id);

    List<CustumerDto> findAll();

    boolean isExistsCustumer(Long id);

}
