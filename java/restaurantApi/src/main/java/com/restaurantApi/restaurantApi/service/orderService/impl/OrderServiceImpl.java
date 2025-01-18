package com.restaurantApi.restaurantApi.service.orderService.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurantApi.restaurantApi.model.custumers.CustumerDto;
import com.restaurantApi.restaurantApi.model.custumers.CustumerEntity;
import com.restaurantApi.restaurantApi.model.order_items.OrderItemsDto;
import com.restaurantApi.restaurantApi.model.order_items.OrderItemsEntity;
import com.restaurantApi.restaurantApi.model.orders.OrdersDto;
import com.restaurantApi.restaurantApi.model.orders.OrdersEntity;
import com.restaurantApi.restaurantApi.repository.OrdersRepo;
import com.restaurantApi.restaurantApi.service.orderService.OrderService;
import com.restaurantApi.restaurantApi.service.platesService.PlatesService;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired 
    private PlatesService platesService;


    @Override
    public OrdersDto convertToDto(OrdersEntity entity) {
        //primero creamos el custumer dto 
        CustumerDto custumerDto = CustumerDto.builder()
        .id_custumer(entity.getCustumer().getId_custumer())
        .name(entity.getCustumer().getName())
        .email(entity.getCustumer().getEmail())
        .phone(entity.getCustumer().getPhone())
        .address(entity.getCustumer().getAddress())
        .build();

        //luego creamo la lista de order items dto
        List<OrderItemsDto> orderItemsDto = entity.getOrderItems().stream().map(order_item -> {
            return OrderItemsDto.builder()
            .id_orderItem(order_item.getId_orderItem())
            .platesDto(platesService.convertToDto(order_item.getPlatesEntity()))
            .build();
        }).collect(Collectors.toList());

        //se crea el orders dto
        return OrdersDto.builder()
        .id_orders(entity.getId_orders())
        .order_date(entity.getOrder_date())
        .total(entity.getTotal())
        .status(entity.getStatus())
        .custumerDto(custumerDto)
        .order_items(orderItemsDto)
        .build();
    }


    @Override
    public OrdersEntity convertToEntity(OrdersDto dto) {
        //primero creamos el custumer dto 
        CustumerEntity custumerEntity = CustumerEntity.builder()
        .id_custumer(dto.getCustumerDto().getId_custumer())
        .name(dto.getCustumerDto().getName())
        .email(dto.getCustumerDto().getEmail())
        .phone(dto.getCustumerDto().getPhone())
        .address(dto.getCustumerDto().getAddress())
        .build();

        //luego creamo la lista de order items dto
        List<OrderItemsEntity> orderItemsEntity = dto.getOrder_items().stream().map(order_item -> {
            return OrderItemsEntity.builder()
            .id_orderItem(order_item.getId_orderItem())
            .platesEntity(platesService.convertToEntity(order_item.getPlatesDto()))
            .build();
        }).collect(Collectors.toList());

        //se crea el orders dto
        return OrdersEntity.builder()
        .id_orders(dto.getId_orders())
        .order_date(dto.getOrder_date())
        .total(dto.getTotal())
        .status(dto.getStatus())
        .custumer(custumerEntity)
        .orderItems(orderItemsEntity)
        .build();
    }

    @Override
public OrdersDto save(OrdersDto orders) {
    OrdersEntity ordersEntity = convertToEntity(orders);
    OrdersEntity savedOrder = ordersRepo.save(ordersEntity);

    return convertToDto(savedOrder);
}

@Override
public boolean deleteById(Long id) {
    if (!this.isExistsOrders(id)) {
        return false;
    }

    ordersRepo.deleteById(id);
    return true;
}

@Override
public Optional<OrdersDto> findById(Long id) {
    Optional<OrdersEntity> ordersEntity = ordersRepo.findById(id);
    Optional<OrdersDto> ordersDto = ordersEntity.map(order -> convertToDto(order));

    return ordersDto;
}

@Override
public List<OrdersDto> findAll() {
    List<OrdersEntity> ordersEntities = ordersRepo.findAll();
    List<OrdersDto> ordersDtos = ordersEntities.stream()
        .map(order -> convertToDto(order))
        .collect(Collectors.toList());

    return ordersDtos;
}

@Override
public boolean isExistsOrders(Long id) {
    OrdersEntity ordersEntity = ordersRepo.findById(id).orElse(null);

    if (ordersEntity == null) {
        return false;
    }

    return true;
}


    
}
