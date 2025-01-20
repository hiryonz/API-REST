package com.restaurantApi.restaurantApi.controller.orders;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.restaurantApi.restaurantApi.model.orders.OrdersDto;
import com.restaurantApi.restaurantApi.service.orderService.OrderService;

@RestController
@RequestMapping("orders")
public class OrdersController {

    @Autowired
    private OrderService ordersService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrdersDto ordersDto) {
        if (ordersDto.getId_orders() != null) {
            return new ResponseEntity<String>("el id de orders existe",HttpStatus.BAD_REQUEST); // No debe incluir un ID al crear una orden.
        }

        if (ordersDto.getOrderItems() == null || ordersDto.getOrderItems().isEmpty()) {
            return new ResponseEntity<String>("no tiene lista de platos",HttpStatus.BAD_REQUEST); // Validación de orderItems.
        }

        if(ordersDto.getId_custumer() == null) {
            return new ResponseEntity<String>("no esta asociado a ningun cliente", HttpStatus.BAD_REQUEST); //validacion de clientes
        }

        final OrdersDto order = ordersService.save(ordersDto);
        return new ResponseEntity<OrdersDto>(order, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody OrdersDto ordersDto) {
        if (!ordersService.isExistsOrders(id)) {
            return new ResponseEntity<String>("no se encontro la orden",HttpStatus.NOT_FOUND);
        }

        if (ordersDto.getOrderItems() == null || ordersDto.getOrderItems().isEmpty()) {
            return new ResponseEntity<String>("no tiene una lista de platos", HttpStatus.BAD_REQUEST); // Validación de orderItems.
        }

        if(ordersDto.getId_custumer() == null) {
            return new ResponseEntity<String>("no tiene asociado un cliente", HttpStatus.BAD_REQUEST);
        }

        ordersDto.setId_orders(id);
        final OrdersDto order = ordersService.save(ordersDto);
        return new ResponseEntity<OrdersDto>(order, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if (!ordersService.isExistsOrders(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ordersService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrdersDto>> getAllOrders() {
        final List<OrdersDto> orders = ordersService.findAll();
        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<OrdersDto>> getOrder(@PathVariable Long id) {
        Optional<OrdersDto> order = ordersService.findById(id);

        if (order.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @RestControllerAdvice
    public class GlobalExceptionHandler {
        @ExceptionHandler(value = Exception.class)
        public ResponseEntity<String> handleException(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
