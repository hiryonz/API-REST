package com.restaurantApi.restaurantApi.controller.categories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.restaurantApi.restaurantApi.model.categories.CategoriesDto;
import com.restaurantApi.restaurantApi.service.categoriesService.CategoriesService;

@RestController
public class CategoriesController {
    
    @Autowired
    private CategoriesService categoriesService;

    @PostMapping(path = "categories")
    public ResponseEntity<CategoriesDto> createCategory(@RequestBody final CategoriesDto categoriesDto) {
        if (categoriesDto.getId_category() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // No debe tener ID al crearlo.
        }
        final CategoriesDto savedCategoriesDto = categoriesService.save(categoriesDto);
        return new ResponseEntity<>(savedCategoriesDto, HttpStatus.CREATED);
    }

    @PutMapping(path = "categories/{id}")
    public ResponseEntity<CategoriesDto> updateCategory(@PathVariable final Long id, @RequestBody final CategoriesDto categoriesDto) {
        if (!categoriesService.isExistsCategories(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoriesDto.setId_category(id); // Aseguramos que el ID sea consistente.
        final CategoriesDto updatedCategoriesDto = categoriesService.save(categoriesDto);
        return new ResponseEntity<>(updatedCategoriesDto, HttpStatus.OK);
    }

    @GetMapping(path = "categories")
    public ResponseEntity<List<CategoriesDto>> getCategories() {
        final List<CategoriesDto> categoriesDtos = categoriesService.findAll();
        
        if (categoriesDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(categoriesDtos, HttpStatus.OK);
    }

    @GetMapping(path = "categories/{id}")
    public ResponseEntity<Optional<CategoriesDto>> getCategoriesById(@PathVariable final Long id) {
        final Optional<CategoriesDto> categoriesDto = categoriesService.findById(id);

        if(categoriesDto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(categoriesDto, HttpStatus.OK);
    }

    @DeleteMapping(path = "categories/{id}")
    public ResponseEntity<HttpStatus> deleteCategories(@PathVariable final Long id) {
        if(categoriesService.isExistsCategories(id)) {
            categoriesService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RestControllerAdvice
    public class GlobalExceptionHandler {
        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleException(Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
