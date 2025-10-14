package com.gabriel.service;
import com.gabriel.enums.RentalItemStatus;
import com.gabriel.model.RentalItem;

import java.util.List;

public interface RentalItemService {
    List<RentalItem> getAll();
    List<RentalItem> getRentalItems(Integer customerId);
    List<RentalItem> getCartItems(Integer customerId);
    RentalItem create(RentalItem rentalItem);
    List<RentalItem> create(List<RentalItem> rentalItems);
    RentalItem update(RentalItem rentalItem);
    List<RentalItem> update(List<RentalItem> rentalItems);
    List<RentalItem> updateStatus(List<Integer> id, RentalItemStatus rentalItemStatus);
    RentalItem get(Integer id);
    void delete (Integer id);
    List<RentalItem> getRentalsByStatus(RentalItemStatus status);
    List<RentalItem> getRentalsByMotorcycle(Integer motorcycleId);
}
