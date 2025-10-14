package com.gabriel.controller;

import com.gabriel.model.RentalItem;
import com.gabriel.service.RentalItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class RentalItemController {

    @Autowired
    private RentalItemService rentalItemService;

    @GetMapping("/api/rentalitem")
    public ResponseEntity<?> getAll()
    {
        log.info("Getting all rental items");
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<?> response;
        try {
            List<RentalItem> rentalItems = rentalItemService.getAll();
            response = ResponseEntity.ok( rentalItems);
        }
        catch( Exception ex)
        {
            log.error("Failed to retrieve rental items : {}", ex.getMessage(), ex);
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
        return response;
    }

    @RequestMapping("/api/rentalitem/{customerId}")
    public ResponseEntity<?> getRentalItems(@PathVariable final Integer customerId, @RequestParam (name="status") Integer status)
    {
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<?> response;
        try {
            if(status == 0) {
                List<RentalItem> rentalItems = rentalItemService.getCartItems(customerId);
                response = ResponseEntity.ok( rentalItems);
            }
            else {
                List<RentalItem> rentalItems = rentalItemService.getRentalItems(customerId);
                response = ResponseEntity.ok( rentalItems);
            }
        }
        catch( Exception ex)
        {
            log.error("Failed to retrieve rental items : {}", ex.getMessage(), ex);
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
        return response;
    }

    @PutMapping("/api/rentalitem")
    public ResponseEntity<?> add(@RequestBody RentalItem rentalItem){
        log.info("Input >> " + rentalItem.toString() );
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<?> response;
        try {
            RentalItem newRentalItem = rentalItemService.create(rentalItem);
            log.info("created rental item >> " + newRentalItem.toString() );
            response = ResponseEntity.ok(newRentalItem);
        }
        catch( Exception ex)
        {
            log.error("Failed to create rental item : {}", ex.getMessage(), ex);
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
        return response;
    }

    @PutMapping("/api/rentalitems")
    public ResponseEntity<?> add(@RequestBody List<RentalItem> rentalItems){
        log.info("Input >> " + rentalItems.toString() );
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<?> response;
        try {
            List<RentalItem> newRentalItems = rentalItemService.create(rentalItems);
            log.info("created rental items >> " + newRentalItems.toString() );
            response = ResponseEntity.ok(newRentalItems);
        }
        catch( Exception ex)
        {
            log.error("Failed to create rental items : {}", ex.getMessage(), ex);
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
        return response;
    }

    @PostMapping("/api/rentalitem")
    public ResponseEntity<?> update(@RequestBody RentalItem rentalItem){
        log.info("Update Input >> rentalItem.toString() ");
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<?> response;
        try {
            RentalItem newRentalItem = rentalItemService.update(rentalItem);
            response = ResponseEntity.ok(newRentalItem);
        }
        catch( Exception ex)
        {
            log.error("Failed to update rental item : {}", ex.getMessage(), ex);
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
        return response;
    }

    @PostMapping("/api/rentalitem/{id}/return")
    public ResponseEntity<?> returnMotorcycle(@PathVariable Integer id, @RequestBody RentalItem rentalItem){
        log.info("Processing motorcycle return for rental item: {}", id);
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<?> response;
        try {
            RentalItem updatedRentalItem = rentalItemService.update(rentalItem);
            response = ResponseEntity.ok(updatedRentalItem);
        }
        catch( Exception ex)
        {
            log.error("Failed to process motorcycle return : {}", ex.getMessage(), ex);
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
        return response;
    }
}
