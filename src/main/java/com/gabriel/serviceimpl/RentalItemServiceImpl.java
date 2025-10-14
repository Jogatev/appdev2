package com.gabriel.serviceimpl;

import com.gabriel.entity.RentalItemData;
import com.gabriel.enums.RentalItemStatus;
import com.gabriel.model.RentalItem;
import com.gabriel.repository.RentalItemDataRepository;
import com.gabriel.service.RentalItemService;
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
public class RentalItemServiceImpl implements RentalItemService {

    @Autowired
    RentalItemDataRepository rentalItemDataRepository;

    Transform<RentalItemData, RentalItem> transformRentalItemData = new Transform<>(RentalItem.class);

    Transform<RentalItem, RentalItemData> transformRentalItem = new Transform<>(RentalItemData.class);

    @Override
    public List<RentalItem> getAll() {
        List<RentalItemData> rentalItemDataRecords = new ArrayList<>();
        List<RentalItem> rentalItems = new ArrayList<>();

        rentalItemDataRepository.findAll().forEach(rentalItemDataRecords::add);
        Iterator<RentalItemData> it = rentalItemDataRecords.iterator();

        while(it.hasNext()) {
            RentalItem rentalItem = new RentalItem();
            RentalItemData rentalItemData = it.next();
            rentalItem = transformRentalItemData.transform(rentalItemData);
            rentalItems.add(rentalItem);
        }
        return rentalItems;
    }

    @Override
    public List<RentalItem> getRentalItems(Integer customerId) {
        List<RentalItemData> rentalItemDataRecords = rentalItemDataRepository.findAllByCustomerId(customerId);
        List<RentalItem> rentalItems = new ArrayList<>();

        for(RentalItemData rentalItemData : rentalItemDataRecords) {
            RentalItem rentalItem = transformRentalItemData.transform(rentalItemData);
            rentalItems.add(rentalItem);
        }
        return rentalItems;
    }

    @Override
    public List<RentalItem> getCartItems(Integer customerId) {
        List<RentalItemData> rentalItemDataRecords = rentalItemDataRepository.findAllByCustomerId(customerId);
        List<RentalItem> rentalItems = new ArrayList<>();

        for(RentalItemData rentalItemData : rentalItemDataRecords) {
            if(rentalItemData.getStatus() == RentalItemStatus.RESERVED) {
                RentalItem rentalItem = transformRentalItemData.transform(rentalItemData);
                rentalItems.add(rentalItem);
            }
        }
        return rentalItems;
    }

    @Override
    public RentalItem create(RentalItem rentalItem) {
        log.info("Creating rental item: {}", rentalItem.toString());
        RentalItemData rentalItemData = transformRentalItem.transform(rentalItem);
        RentalItemData savedRentalItemData = rentalItemDataRepository.save(rentalItemData);
        log.info("Created rental item: {}", savedRentalItemData.toString());
        return transformRentalItemData.transform(savedRentalItemData);
    }

    @Override
    public List<RentalItem> create(List<RentalItem> rentalItems) {
        List<RentalItemData> rentalItemDataList = new ArrayList<>();
        for(RentalItem rentalItem : rentalItems) {
            RentalItemData rentalItemData = transformRentalItem.transform(rentalItem);
            rentalItemDataList.add(rentalItemData);
        }
        List<RentalItemData> savedRentalItemDataList = (List<RentalItemData>) rentalItemDataRepository.saveAll(rentalItemDataList);
        
        List<RentalItem> savedRentalItems = new ArrayList<>();
        for(RentalItemData rentalItemData : savedRentalItemDataList) {
            RentalItem rentalItem = transformRentalItemData.transform(rentalItemData);
            savedRentalItems.add(rentalItem);
        }
        return savedRentalItems;
    }

    @Override
    public RentalItem update(RentalItem rentalItem) {
        log.info("Updating rental item: {}", rentalItem.toString());
        Optional<RentalItemData> optional = rentalItemDataRepository.findById(rentalItem.getId());
        if(optional.isPresent()) {
            RentalItemData rentalItemData = transformRentalItem.transform(rentalItem);
            RentalItemData updatedRentalItemData = rentalItemDataRepository.save(rentalItemData);
            log.info("Updated rental item: {}", updatedRentalItemData.toString());
            return transformRentalItemData.transform(updatedRentalItemData);
        } else {
            log.error("Rental item with id {} not found", rentalItem.getId());
            return null;
        }
    }

    @Override
    public List<RentalItem> update(List<RentalItem> rentalItems) {
        List<RentalItemData> rentalItemDataList = new ArrayList<>();
        for(RentalItem rentalItem : rentalItems) {
            RentalItemData rentalItemData = transformRentalItem.transform(rentalItem);
            rentalItemDataList.add(rentalItemData);
        }
        List<RentalItemData> updatedRentalItemDataList = (List<RentalItemData>) rentalItemDataRepository.saveAll(rentalItemDataList);
        
        List<RentalItem> updatedRentalItems = new ArrayList<>();
        for(RentalItemData rentalItemData : updatedRentalItemDataList) {
            RentalItem rentalItem = transformRentalItemData.transform(rentalItemData);
            updatedRentalItems.add(rentalItem);
        }
        return updatedRentalItems;
    }

    @Override
    public List<RentalItem> updateStatus(List<Integer> ids, RentalItemStatus rentalItemStatus) {
        List<RentalItem> updatedRentalItems = new ArrayList<>();
        for(Integer id : ids) {
            Optional<RentalItemData> optional = rentalItemDataRepository.findById(id);
            if(optional.isPresent()) {
                RentalItemData rentalItemData = optional.get();
                rentalItemData.setStatus(rentalItemStatus);
                RentalItemData updatedRentalItemData = rentalItemDataRepository.save(rentalItemData);
                RentalItem rentalItem = transformRentalItemData.transform(updatedRentalItemData);
                updatedRentalItems.add(rentalItem);
            }
        }
        return updatedRentalItems;
    }

    @Override
    public RentalItem get(Integer id) {
        Optional<RentalItemData> optional = rentalItemDataRepository.findById(id);
        if(optional.isPresent()) {
            return transformRentalItemData.transform(optional.get());
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        log.info("Deleting rental item with id: {}", id);
        rentalItemDataRepository.deleteById(id);
        log.info("Successfully deleted rental item with id: {}", id);
    }

    @Override
    public List<RentalItem> getRentalsByStatus(RentalItemStatus status) {
        List<RentalItemData> rentalItemDataRecords = rentalItemDataRepository.findAllByStatus(status);
        List<RentalItem> rentalItems = new ArrayList<>();

        for(RentalItemData rentalItemData : rentalItemDataRecords) {
            RentalItem rentalItem = transformRentalItemData.transform(rentalItemData);
            rentalItems.add(rentalItem);
        }
        return rentalItems;
    }

    @Override
    public List<RentalItem> getRentalsByMotorcycle(Integer motorcycleId) {
        List<RentalItemData> rentalItemDataRecords = rentalItemDataRepository.findAllByMotorcycleId(motorcycleId);
        List<RentalItem> rentalItems = new ArrayList<>();

        for(RentalItemData rentalItemData : rentalItemDataRecords) {
            RentalItem rentalItem = transformRentalItemData.transform(rentalItemData);
            rentalItems.add(rentalItem);
        }
        return rentalItems;
    }
}
