package com.restaurantApi.restaurantApi.service.custumerService.impl;

import java.util.Date;
import java.util.List;
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

import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

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








        private Long id_orders;
    private Date order_date;
    private double total;
    private int status; //enum (pendiente, preparacion, listo, entregado)

    //id_custumer fk
    @OneToMany
    @JoinColumn(name = "id_custumers")
    private CustumerEntity custumerEntity;

  

    private CustumerEntity convertToEntity(CustumerDto dto) {
        // Convertimos las OrderItemsEntity
        List<OrderItemsEntity> orderItemsEntities = dto.getOrders().stream()
        .flatMap(orderDto -> orderDto.getOrder_items().stream()
            .map(orderItemDto -> {
                OrderItemsEntity orderItemEntity = new OrderItemsEntity();
                orderItemEntity.setId_orderItem(orderItemDto.getId_orderItem());
                orderItemEntity.setPlatesEntity(platesService.convertToEntity(orderItemDto.getPlatesDto()));  // Convertir de DTO a entidad PlatesEntity
                return orderItemEntity;
            }))
        .collect(Collectors.toList());

        // Convertimos las OrdersEntity y asociamos los OrderItemsEntity
        List<OrdersEntity> ordersEntities = dto.getOrders().stream()
        .map(orderDto -> {
            OrdersEntity orderEntity = new OrdersEntity();
            orderEntity.setId_orders(orderDto.getId_orders());
            orderEntity.setOrder_date(orderDto.getOrder_date());
            orderEntity.setTotal(orderDto.getTotal());
            orderEntity.setStatus(orderDto.getStatus());

            // Asignamos la lista de OrderItemsEntity a la orden
            orderEntity.setOrderItems(orderItemsEntities.stream()
                .filter(orderItem -> orderItem.getOrdersEntity().getId_orders().equals(orderEntity.getId_orders()))
                .collect(Collectors.toList()));

            return orderEntity;
        })
        .collect(Collectors.toList());

        // Finalmente, creamos la entidad CustumerEntity y asignamos las órdenes
        CustumerEntity custumerEntity = new CustumerEntity();
        custumerEntity.setId_custumer(dto.getId_custumer());
        custumerEntity.setName(dto.getName());
        custumerEntity.setEmail(dto.getEmail());
        custumerEntity.setPhone(dto.getPhone());
        custumerEntity.setAddress(dto.getAddress());
        custumerEntity.setOrders(ordersEntities);  // Asignamos las órdenes convertidas

        return custumerEntity;

    }
}
    
