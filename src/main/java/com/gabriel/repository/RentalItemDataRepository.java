package com.gabriel.repository;

import com.gabriel.entity.RentalItemData;
import com.gabriel.enums.RentalItemStatus;
import com.gabriel.model.RentalItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RentalItemDataRepository extends CrudRepository<RentalItemData, Integer> {
    List<RentalItemData> findAllByCustomerId(Integer customerId);
    List<RentalItemData> findAllByRentalId(Integer rentalId);
    List<RentalItemData> findAllByStatus(RentalItemStatus status);
    List<RentalItemData> findAllByMotorcycleId(Integer motorcycleId);
}
