package com.gabriel.repository;

import com.gabriel.entity.RentalData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RentalDataRepository extends CrudRepository<RentalData, Integer> {
    List<RentalData> findAllByCustomerId(String customerId);
    List<RentalData> findAllByRentalStatus(String rentalStatus);
}
