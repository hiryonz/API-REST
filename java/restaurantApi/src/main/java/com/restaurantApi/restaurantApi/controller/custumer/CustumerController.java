package com.restaurantApi.restaurantApi.controller.custumer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.restaurantApi.restaurantApi.model.custumers.CustumerDto;
import com.restaurantApi.restaurantApi.service.custumerService.CustumerService;

@RestController
public class CustumerController {
    
    @Autowired
    private CustumerService custumerService;

    @PostMapping(path = "custumer")
    public ResponseEntity<CustumerDto> createCustumer(@RequestBody CustumerDto custumerDto) {
        if (custumerDto.getId_custumer() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //se supone que cuando creas no debes poner el id
        }

        final CustumerDto custumer = custumerService.save(custumerDto);

        return new ResponseEntity<>(custumer, HttpStatus.CREATED);
    }

    @PutMapping(path = "custumer/{id}")
    public ResponseEntity<CustumerDto> updateCustumer(@PathVariable Long id, @RequestBody CustumerDto custumerDto) {
        if (!custumerService.isExistsCustumer(id)) { return new ResponseEntity<>(HttpStatus.NOT_FOUND);  }
        custumerDto.setId_custumer(id);
        final CustumerDto custumer = custumerService.save(custumerDto);
        return new ResponseEntity<>(custumer, HttpStatus.OK);
    }

    @DeleteMapping(path = "custumer/{id}")
    public ResponseEntity<Void> deleteCustumer(@PathVariable Long id) {
        if(!custumerService.isExistsCustumer(id)) { return new ResponseEntity<>(HttpStatus.NOT_FOUND);  }

        custumerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "custumer")
    public ResponseEntity<List<CustumerDto>> getAllCustumer() {
        final List<CustumerDto> custumer = custumerService.findAll();
        if(custumer.isEmpty()) { return new ResponseEntity<>(HttpStatus.NO_CONTENT); }

        return new ResponseEntity<>(custumer, HttpStatus.OK);
    }

    @GetMapping(path = "custumer/{id}")
    public ResponseEntity<Optional<CustumerDto>> getCustumer(@PathVariable Long id) {
        Optional<CustumerDto> custumer = custumerService.findById(id);

        if(custumer.isEmpty()) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }

        return new ResponseEntity<>(custumer, HttpStatus.OK);
    }


    @RestControllerAdvice
    public class GlobalExceptionHandler {
        @ExceptionHandler(value = Exception.class)
        public ResponseEntity<String> handleException(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
