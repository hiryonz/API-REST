package com.restaurantApi.restaurantApi.service.custumerService.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.restaurantApi.restaurantApi.model.custumers.CustumerDto;
import com.restaurantApi.restaurantApi.model.custumers.CustumerEntity;
import com.restaurantApi.restaurantApi.model.order_items.OrderItemsDto;
import com.restaurantApi.restaurantApi.model.order_items.OrderItemsEntity;
import com.restaurantApi.restaurantApi.model.orders.OrdersDto;
import com.restaurantApi.restaurantApi.model.orders.OrdersEntity;
import com.restaurantApi.restaurantApi.repository.CustumerRepo;
import com.restaurantApi.restaurantApi.service.custumerService.CustumerService;
import com.restaurantApi.restaurantApi.service.platesService.PlatesService;


public class CustumerServiceImpl implements CustumerService {
    
    @Autowired
    private CustumerRepo custumerRepo;

    @Autowired
    private PlatesService platesService;


    private CustumerDto convertToDto(CustumerEntity entity) {
    
        // Ahora, convertimos las OrdersDto y las agregamos con el OrderItemsDto respectivo
        List<OrdersDto> ordersDto = entity.getOrders().stream()
            .map(order -> OrdersDto.builder()
                .id_orders(order.getId_orders())
                .order_date(order.getOrder_date())
                .total(order.getTotal())
                .status(order.getStatus())
                .order_items(order.getOrderItems().stream()
                    .map(order_item -> OrderItemsDto.builder()
                        .id_orderItem(order_item.getId_orderItem())
                        .platesDto(platesService.convertToDto(order_item.getPlatesEntity()))
                        .build())
                    .collect(Collectors.toList()))  // Convertimos todos los OrderItems a OrderItemsDto
                .build())
            .collect(Collectors.toList());
    
        // Finalmente, creamos el CustumerDto y asignamos la lista de ordersDto
        return CustumerDto.builder()
            .id_custumer(entity.getId_custumer())
            .name(entity.getName())
            .email(entity.getEmail())
            .phone(entity.getPhone())
            .address(entity.getAddress())
            .orders(ordersDto)  // Aquí es donde agregamos las órdenes al DTO
            .build();
    }

    private CustumerEntity convertToEntity(CustumerDto dto) {
    
        // Ahora, convertimos las OrdersDto y las agregamos con el OrderItemsDto respectivo
        List<OrdersEntity> ordersEntity = dto.getOrders().stream()
            .map(order -> OrdersEntity.builder()
                .id_orders(order.getId_orders())
                .order_date(order.getOrder_date())
                .total(order.getTotal())
                .status(order.getStatus())
                .orderItems(order.getOrder_items().stream().map(order_item -> OrderItemsEntity.builder()
                .id_orderItem(order_item.getId_orderItem())
                .platesEntity(platesService.convertToEntity(order_item.getPlatesDto()))
                .build()).collect(Collectors.toList()))
                .build())
            .collect(Collectors.toList());
    
        // Finalmente, creamos el CustumerDto y asignamos la lista de ordersDto
        return CustumerEntity.builder()
            .id_custumer(dto.getId_custumer())
            .name(dto.getName())
            .email(dto.getEmail())
            .phone(dto.getPhone())
            .address(dto.getAddress())
            .orders(ordersEntity)  // Aquí es donde agregamos las órdenes al DTO
            .build();
    }

    @Override
    public CustumerDto save(CustumerDto custumer) {
        CustumerEntity custumerEntity = convertToEntity(custumer);
        CustumerEntity savedCustumer = custumerRepo.save(custumerEntity);

        return convertToDto(savedCustumer);
    }

    @Override
    public boolean deleteById(Long id) {

        if(!this.isExistsCustumer(id)) { return false; }

        custumerRepo.deleteById(id);
        return true;
    }

    @Override
    public Optional<CustumerDto> findById(Long id) {
        Optional<CustumerEntity> custumerEntity = custumerRepo.findById(id);
        Optional<CustumerDto> custumerDto = custumerEntity.map(custumer -> convertToDto(custumer));

        return custumerDto;
    }

    @Override
    public List<CustumerDto> findAll() {
        List<CustumerEntity> custumerEntity = custumerRepo.findAll();
        List<CustumerDto> custumerDtos = custumerEntity.stream().map(custumer -> convertToDto(custumer)).collect(Collectors.toList());

        return custumerDtos;    
    }

    @Override
    public boolean isExistsCustumer(Long id) {
        CustumerEntity custumerEntity = custumerRepo.findById(id).orElse(null);

        if (custumerEntity == null) {
            return false;
        }

        return true;
    }
    
}
    
