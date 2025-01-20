package com.restaurantApi.restaurantApi.controller.plates;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.restaurantApi.restaurantApi.model.plates.PlatesDto;
import com.restaurantApi.restaurantApi.service.platesService.PlatesService;

@RestController
@RequestMapping("plates")
public class PlatesController {

    @Autowired
    private PlatesService platesService;

    @PostMapping
    public ResponseEntity<PlatesDto> createPlate(@RequestBody PlatesDto PlatesDto) {
        if (PlatesDto.getId_plates() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // No debe incluir un ID al crear un plato.
        }

        if (PlatesDto.getName() == null || PlatesDto.getName().isEmpty() ||
            PlatesDto.getDescription() == null || PlatesDto.getDescription().isEmpty() ||
            PlatesDto.getPrice() == null || PlatesDto.getPrice() <= 0 ||
            PlatesDto.getAvailability() < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Validación de campos obligatorios.
        }

        final PlatesDto plate = platesService.save(PlatesDto);
        return new ResponseEntity<>(plate, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PlatesDto> updatePlate(@PathVariable Long id, @RequestBody PlatesDto PlatesDto) {
        if (!platesService.isExistsPlates(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (PlatesDto.getName() == null || PlatesDto.getName().isEmpty() ||
            PlatesDto.getDescription() == null || PlatesDto.getDescription().isEmpty() ||
            PlatesDto.getPrice() == null || PlatesDto.getPrice() <= 0 ||
            PlatesDto.getAvailability() < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Validación de campos obligatorios.
        }

        PlatesDto.setId_plates(id);
        final PlatesDto plate = platesService.save(PlatesDto);
        return new ResponseEntity<>(plate, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletePlate(@PathVariable Long id) {
        if (!platesService.isExistsPlates(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        platesService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PlatesDto>> getAllPlates() {
        final List<PlatesDto> plates = platesService.findAll();
        if (plates.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(plates, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<PlatesDto>> getPlate(@PathVariable Long id) {
        Optional<PlatesDto> plate = platesService.findById(id);

        if (plate.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(plate, HttpStatus.OK);
    }

    @RestControllerAdvice
    public class GlobalExceptionHandler {
        @ExceptionHandler(value = Exception.class)
        public ResponseEntity<String> handleException(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
