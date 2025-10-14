package com.gabriel.service;

import com.gabriel.model.Rental;

import java.util.List;

public interface RentalService {
    Rental createRental(Rental rental);
    Rental updateRental(Integer id, Rental rental);
    Rental getRental(Integer id);
    List<Rental> getAllRentals();
    List<Rental> getRentalsByCustomer(String customerId);
    Rental cancelRental(Integer id);
    Rental returnMotorcycle(Integer id, Rental rental);
    Rental completeRental(Integer id);
}
