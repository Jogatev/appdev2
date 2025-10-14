package com.gabriel.serviceimpl;

import com.gabriel.entity.RentalData;
import com.gabriel.model.Rental;
import com.gabriel.repository.RentalDataRepository;
import com.gabriel.service.RentalService;
import com.gabriel.util.Transform;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RentalServiceImpl implements RentalService {

    @Autowired
    RentalDataRepository rentalDataRepository;

    Transform<RentalData, Rental> transformRentalData = new Transform<>(Rental.class);
    Transform<Rental, RentalData> transformRental = new Transform<>(RentalData.class);

    @Override
    public Rental createRental(Rental rental) {
        log.info("Creating rental: {}", rental.toString());
        RentalData rentalData = transformRental.transform(rental);
        RentalData savedRentalData = rentalDataRepository.save(rentalData);
        log.info("Created rental: {}", savedRentalData.toString());
        return transformRentalData.transform(savedRentalData);
    }

    @Override
    public Rental updateRental(Integer id, Rental rental) {
        log.info("Updating rental with id: {}", id);
        Optional<RentalData> optional = rentalDataRepository.findById(id);
        if(optional.isPresent()) {
            RentalData rentalData = transformRental.transform(rental);
            rentalData.setId(id);
            RentalData updatedRentalData = rentalDataRepository.save(rentalData);
            log.info("Updated rental: {}", updatedRentalData.toString());
            return transformRentalData.transform(updatedRentalData);
        } else {
            log.error("Rental with id {} not found", id);
            return null;
        }
    }

    @Override
    public Rental getRental(Integer id) {
        log.info("Getting rental with id: {}", id);
        Optional<RentalData> optional = rentalDataRepository.findById(id);
        if(optional.isPresent()) {
            return transformRentalData.transform(optional.get());
        } else {
            log.error("Rental with id {} not found", id);
            return null;
        }
    }

    @Override
    public List<Rental> getAllRentals() {
        log.info("Getting all rentals");
        List<RentalData> rentalDataRecords = new ArrayList<>();
        List<Rental> rentals = new ArrayList<>();

        rentalDataRepository.findAll().forEach(rentalDataRecords::add);
        Iterator<RentalData> it = rentalDataRecords.iterator();

        while(it.hasNext()) {
            RentalData rentalData = it.next();
            Rental rental = transformRentalData.transform(rentalData);
            rentals.add(rental);
        }
        return rentals;
    }

    @Override
    public List<Rental> getRentalsByCustomer(String customerId) {
        log.info("Getting rentals for customer: {}", customerId);
        List<RentalData> rentalDataRecords = rentalDataRepository.findAllByCustomerId(customerId);
        List<Rental> rentals = new ArrayList<>();

        for(RentalData rentalData : rentalDataRecords) {
            Rental rental = transformRentalData.transform(rentalData);
            rentals.add(rental);
        }
        return rentals;
    }

    @Override
    public Rental cancelRental(Integer id) {
        log.info("Cancelling rental with id: {}", id);
        Optional<RentalData> optional = rentalDataRepository.findById(id);
        if(optional.isPresent()) {
            RentalData rentalData = optional.get();
            rentalData.setRentalStatus("CANCELLED");
            RentalData updatedRentalData = rentalDataRepository.save(rentalData);
            log.info("Cancelled rental: {}", updatedRentalData.toString());
            return transformRentalData.transform(updatedRentalData);
        } else {
            log.error("Rental with id {} not found", id);
            return null;
        }
    }

    @Override
    public Rental returnMotorcycle(Integer id, Rental rental) {
        log.info("Processing motorcycle return for rental: {}", id);
        Optional<RentalData> optional = rentalDataRepository.findById(id);
        if(optional.isPresent()) {
            RentalData rentalData = optional.get();
            rentalData.setRentalStatus("COMPLETED");
            rentalData.setRentalEndDate(rental.getRentalEndDate());
            RentalData updatedRentalData = rentalDataRepository.save(rentalData);
            log.info("Processed motorcycle return: {}", updatedRentalData.toString());
            return transformRentalData.transform(updatedRentalData);
        } else {
            log.error("Rental with id {} not found", id);
            return null;
        }
    }

    @Override
    public Rental completeRental(Integer id) {
        log.info("Completing rental with id: {}", id);
        Optional<RentalData> optional = rentalDataRepository.findById(id);
        if(optional.isPresent()) {
            RentalData rentalData = optional.get();
            rentalData.setRentalStatus("COMPLETED");
            RentalData updatedRentalData = rentalDataRepository.save(rentalData);
            log.info("Completed rental: {}", updatedRentalData.toString());
            return transformRentalData.transform(updatedRentalData);
        } else {
            log.error("Rental with id {} not found", id);
            return null;
        }
    }
}
