package com.restaurantApi.restaurantApi.service.orderService.impl;

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
import com.restaurantApi.restaurantApi.repository.OrdersRepo;
import com.restaurantApi.restaurantApi.service.custumerService.CustumerService;
import com.restaurantApi.restaurantApi.service.orderService.OrderService;
import com.restaurantApi.restaurantApi.service.platesService.PlatesService;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired 
    private PlatesService platesService;


    @Override
    public OrdersDto convertToDto(OrdersEntity entity) {
        //primero creamos el custumer dto 
        CustumerDto custumerDto = CustumerDto.builder()
        .id_custumer(entity.getCustumerEntity().getId_custumer())
        .name(entity.getCustumerEntity().getName())
        .email(entity.getCustumerEntity().getEmail())
        .phone(entity.getCustumerEntity().getPhone())
        .address(entity.getCustumerEntity().getAddress())
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
        .custumerEntity(custumerEntity)
        .orderItems(orderItemsEntity)
        .build();
    }

    @Override
    public OrdersDto save(OrdersDto orders) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public boolean deleteById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public Optional<OrdersDto> findById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<OrdersDto> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public boolean isExistsOrders(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isExistsOrders'");
    }

    
}
