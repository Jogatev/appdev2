package com.gabriel.controller;

import com.gabriel.model.Rental;
import com.gabriel.service.RentalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @GetMapping("/api/rental")
    public ResponseEntity<?> getAllRentals() {
        try {
            List<Rental> rentals = rentalService.getAllRentals();
            return ResponseEntity.ok(rentals);
        } catch (Exception ex) {
            log.error("Failed to retrieve rentals: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PostMapping("/api/rental")
    public ResponseEntity<?> createRental(@RequestBody Rental rental) {
        log.info("Creating rental: {}", rental.toString());
        try {
            Rental newRental = rentalService.createRental(rental);
            log.info("Created rental: {}", newRental.toString());
            return ResponseEntity.ok(newRental);
        } catch (Exception ex) {
            log.error("Failed to create rental: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping("/api/rental/{id}")
    public ResponseEntity<?> getRental(@PathVariable Integer id) {
        log.info("Getting rental with id: {}", id);
        try {
            Rental rental = rentalService.getRental(id);
            return ResponseEntity.ok(rental);
        } catch (Exception ex) {
            log.error("Failed to retrieve rental with id {}: {}", id, ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PutMapping("/api/rental/{id}")
    public ResponseEntity<?> updateRental(@PathVariable Integer id, @RequestBody Rental rental) {
        log.info("Updating rental with id: {}", id);
        try {
            Rental updatedRental = rentalService.updateRental(id, rental);
            return ResponseEntity.ok(updatedRental);
        } catch (Exception ex) {
            log.error("Failed to update rental with id {}: {}", id, ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @DeleteMapping("/api/rental/{id}")
    public ResponseEntity<?> cancelRental(@PathVariable Integer id) {
        log.info("Cancelling rental with id: {}", id);
        try {
            Rental cancelledRental = rentalService.cancelRental(id);
            return ResponseEntity.ok(cancelledRental);
        } catch (Exception ex) {
            log.error("Failed to cancel rental with id {}: {}", id, ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping("/api/rental/customer/{customerId}")
    public ResponseEntity<?> getRentalsByCustomer(@PathVariable String customerId) {
        log.info("Getting rentals for customer: {}", customerId);
        try {
            List<Rental> rentals = rentalService.getRentalsByCustomer(customerId);
            return ResponseEntity.ok(rentals);
        } catch (Exception ex) {
            log.error("Failed to retrieve rentals for customer {}: {}", customerId, ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PostMapping("/api/rental/{id}/return")
    public ResponseEntity<?> returnMotorcycle(@PathVariable Integer id, @RequestBody Rental rental) {
        log.info("Processing motorcycle return for rental: {}", id);
        try {
            Rental updatedRental = rentalService.returnMotorcycle(id, rental);
            return ResponseEntity.ok(updatedRental);
        } catch (Exception ex) {
            log.error("Failed to process motorcycle return for rental {}: {}", id, ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
}
