package com.gabriel.controller;

import com.gabriel.model.Motorcycle;
import com.gabriel.model.MotorcycleCategory;
import com.gabriel.service.MotorcycleService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class MotorcycleController {

    @Autowired
    private MotorcycleService motorcycleService;

    @RequestMapping("/api/motorcycle")
    public ResponseEntity<?> getMotorcycleCategories()
    {
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<?> response;
        try {
            List<MotorcycleCategory> mappedMotorcycles = motorcycleService.listMotorcycleCategories();
            log.warn("Motorcycle Categories Count:::::::" + mappedMotorcycles.size());
            response = ResponseEntity.ok(mappedMotorcycles);
        }
        catch( Exception ex)
        {
            log.error("Failed to retrieve motorcycle with id : {}", ex.getMessage(), ex);
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
        return response;
    }

    @PutMapping("/api/motorcycle")
    public ResponseEntity<?> add(@RequestBody Motorcycle motorcycle){
        log.info("Input >> " + motorcycle.toString() );
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<?> response;
        try {
            Motorcycle newMotorcycle = motorcycleService.create(motorcycle);
            log.info("created motorcycle >> " + newMotorcycle.toString() );
            response = ResponseEntity.ok(newMotorcycle);
        }
        catch( Exception ex)
        {
            log.error("Failed to create motorcycle : {}", ex.getMessage(), ex);
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
        return response;
    }
    
    @PostMapping("/api/motorcycle")
    public ResponseEntity<?> update(@RequestBody Motorcycle motorcycle){
        log.info("Update Input >> motorcycle.toString() ");
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<?> response;
        try {
            Motorcycle updatedMotorcycle = motorcycleService.update(motorcycle);
            response = ResponseEntity.ok(motorcycle);
        }
        catch( Exception ex)
        {
            log.error("Failed to update motorcycle : {}", ex.getMessage(), ex);
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
        return response;
    }

    @GetMapping("api/motorcycle/{id}")
    public ResponseEntity<?> get(@PathVariable final Integer id){
        log.info("Input motorcycle id >> " + Integer.toString(id));
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<?> response;
        try {
            Motorcycle motorcycle = motorcycleService.get(id);
            response = ResponseEntity.ok(motorcycle);
        }
        catch( Exception ex)
        {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
        return response;
    }
    
    @DeleteMapping("/api/motorcycle/{id}")
    public ResponseEntity<?> delete(@PathVariable final Integer id){
        log.info("Input >> " + Integer.toString(id));
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<?> response;
        try {
            motorcycleService.delete(id);
            response = ResponseEntity.ok(null);
        }
        catch( Exception ex)
        {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
        return response;
    }

    @GetMapping("/api/motorcycle/available")
    public ResponseEntity<?> getAvailableMotorcycles(){
        log.info("Getting available motorcycles");
        ResponseEntity<?> response;
        try {
            List<Motorcycle> availableMotorcycles = motorcycleService.getAvailableMotorcycles();
            response = ResponseEntity.ok(availableMotorcycles);
        }
        catch( Exception ex)
        {
            log.error("Failed to retrieve available motorcycles : {}", ex.getMessage(), ex);
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
        return response;
    }
}
