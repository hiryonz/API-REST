package com.restaurantApi.restaurantApi.service.orderService.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.restaurantApi.restaurantApi.model.custumers.CustumerDto;
import com.restaurantApi.restaurantApi.model.custumers.CustumerEntity;
import com.restaurantApi.restaurantApi.model.order_items.OrderItemsDto;
import com.restaurantApi.restaurantApi.model.order_items.OrderItemsEntity;
import com.restaurantApi.restaurantApi.model.orders.OrdersDto;
import com.restaurantApi.restaurantApi.model.orders.OrdersEntity;
import com.restaurantApi.restaurantApi.model.plates.PlatesDto;
import com.restaurantApi.restaurantApi.model.plates.PlatesEntity;
import com.restaurantApi.restaurantApi.repository.OrdersRepo;
import com.restaurantApi.restaurantApi.service.custumerService.CustumerService;
import com.restaurantApi.restaurantApi.service.orderService.OrderService;
import com.restaurantApi.restaurantApi.service.platesService.PlatesService;

import jakarta.persistence.EntityNotFoundException;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired 
    private PlatesService platesService;

    @Autowired
    private CustumerService custumerService;


    @Override
    public OrdersDto convertToDto(OrdersEntity entity) {
        if(entity == null) { return null; }


        OrdersDto orderDto = OrdersDto.builder()
        .id_orders(entity.getId_orders())
        .order_date(entity.getOrder_date())
        .total(entity.getTotal())
        .status(entity.getStatus())
        .id_custumer(entity.getCustumer().getId_custumer())
        .build();


        //luego creamo la lista de order items dto
        List<OrderItemsDto> orderItemsDto = entity.getOrderItems().stream().map(order_item -> {
            
            return OrderItemsDto.builder()
            .id_orderItem(order_item.getId_orderItem())
            .id_orders(orderDto.getId_orders())
            .plates(platesService.convertToDto(order_item.getPlates()))
            .build();
        }).collect(Collectors.toList());

        orderDto.setOrderItems(orderItemsDto);
        //se crea el orders dto
        return orderDto;
    }


    @Override
    public OrdersEntity convertToEntity(OrdersDto dto) {
        if(dto == null) { return null; }
    
        // Primero obtenemos la entidad OrdersEntity
        OrdersEntity ordersEntity = OrdersEntity.builder()
                .id_orders(dto.getId_orders())
                .order_date(dto.getOrder_date())
                .total(dto.getTotal())
                .status(dto.getStatus())
                .build();
        
        // Luego creamos la lista de OrderItemsEntity, y usamos la entidad OrdersEntity recién creada
        List<OrderItemsEntity> orderItemsEntity = dto.getOrderItems().stream().map(order_item -> {
    
            PlatesDto platesDto = platesService.findById(order_item.getPlates().getId_plates())
                .orElseThrow(() -> new EntityNotFoundException("el plato no existe, con id: " + order_item.getPlates().getId_plates()));
    
            return OrderItemsEntity.builder()
                .orders(ordersEntity) // Usamos ordersEntity aquí, no creamos una nueva
                .plates(platesService.convertToEntity(platesDto))
                .build();
        }).collect(Collectors.toList());
    
        // Añadimos la lista de orderItems a la entidad orders
        ordersEntity.setOrderItems(orderItemsEntity);
    
        return ordersEntity;
    }
    

    @Override
public OrdersDto save(OrdersDto orders) {
    CustumerDto custumerDto = custumerService.findById(orders.getId_custumer())
        .orElseThrow(() -> new EntityNotFoundException("Custumer no encontrado con ID: " + orders.getId_custumer()));

    
    OrdersEntity ordersEntity = convertToEntity(orders);
    ordersEntity.setCustumer(custumerService.convertToEntity(custumerDto));
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
