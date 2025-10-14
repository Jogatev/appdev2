package com.gabriel.serviceimpl;

import com.gabriel.entity.MotorcycleData;
import com.gabriel.model.Motorcycle;
import com.gabriel.model.MotorcycleCategory;
import com.gabriel.repository.MotorcycleDataRepository;
import com.gabriel.service.MotorcycleService;
import com.gabriel.util.Transform;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class MotorcycleServiceImpl implements MotorcycleService {

    @Autowired
    MotorcycleDataRepository motorcycleDataRepository;

    Transform< MotorcycleData, Motorcycle> transformMotorcycleData = new Transform<>(Motorcycle.class);

    Transform<Motorcycle, MotorcycleData> transformMotorcycle = new Transform<>(MotorcycleData.class);


    public List<Motorcycle> getAllMotorcycles() {
        List<MotorcycleData>motorcycleDataRecords = new ArrayList<>();
        List<Motorcycle> motorcycles =  new ArrayList<>();

        motorcycleDataRepository.findAll().forEach(motorcycleDataRecords::add);
        Iterator<MotorcycleData> it = motorcycleDataRecords.iterator();

        while(it.hasNext()) {
            Motorcycle motorcycle = new Motorcycle();
            MotorcycleData motorcycleData = it.next();
            motorcycle = transformMotorcycleData.transform(motorcycleData);
            motorcycles.add(motorcycle);
        }
        return motorcycles;
    }
    @Override
    public List<MotorcycleCategory> listMotorcycleCategories()
    {
        Map<String,List<Motorcycle>> mappedMotorcycle = getCategoryMappedMotorcycles();
        List<MotorcycleCategory> motorcycleCategories = new ArrayList<>();
        for(String categoryName: mappedMotorcycle.keySet()){
            MotorcycleCategory motorcycleCategory =  new MotorcycleCategory();
            motorcycleCategory.setCategoryName(categoryName);
            motorcycleCategory.setMotorcycles(mappedMotorcycle.get(categoryName));
            motorcycleCategories.add(motorcycleCategory);
        }
        return motorcycleCategories;
    }

    @Override
    public Map<String,List<Motorcycle>> getCategoryMappedMotorcycles()
    {
        Map<String,List<Motorcycle>> mapMotorcycles = new HashMap<String,List<Motorcycle>>();

        List<MotorcycleData>motorcycleDataRecords = new ArrayList<>();
        List<Motorcycle> motorcycles;

        motorcycleDataRepository.findAll().forEach(motorcycleDataRecords::add);
        Iterator<MotorcycleData> it = motorcycleDataRecords.iterator();

        while(it.hasNext()) {
            Motorcycle motorcycle = new Motorcycle();
            MotorcycleData motorcycleData = it.next();

            if(mapMotorcycles.containsKey(motorcycleData.getBikeType())){
                motorcycles = mapMotorcycles.get(motorcycleData.getBikeType());
            }
            else {
                motorcycles = new ArrayList<Motorcycle>();
                mapMotorcycles.put(motorcycleData.getBikeType(), motorcycles);
            }
            motorcycle = transformMotorcycleData.transform(motorcycleData);
            motorcycles.add(motorcycle);
        }
        return mapMotorcycles;
    }

    @Override
    public Motorcycle[] getAll() {
            List<MotorcycleData> motorcyclesData = new ArrayList<>();
            List<Motorcycle> motorcycles = new ArrayList<>();
            motorcycleDataRepository.findAll().forEach(motorcyclesData::add);
            Iterator<MotorcycleData> it = motorcyclesData.iterator();
            while(it.hasNext()) {
                MotorcycleData motorcycleData = it.next();
                Motorcycle motorcycle =  transformMotorcycleData.transform(motorcycleData);
                motorcycles.add(motorcycle);
            }
            Motorcycle[] array = new Motorcycle[motorcycles.size()];
            for  (int i=0; i<motorcycles.size(); i++){
                array[i] = motorcycles.get(i);
            }
            return array;
        }
    @Override
    public Motorcycle get(Integer id) {
        log.info(" Input id >> "+  Integer.toString(id) );
        Motorcycle motorcycle = null;
        Optional<MotorcycleData> optional = motorcycleDataRepository.findById(id);
        if(optional.isPresent()) {
            log.info(" Is present >> ");
            motorcycle = new Motorcycle();
            motorcycle.setId(optional.get().getId());
            motorcycle.setName(optional.get().getName());
        }
        else {
            log.info(" Failed >> unable to locate id: " +  Integer.toString(id)  );
        }
        return motorcycle;
    }
        @Override
        public Motorcycle create(Motorcycle motorcycle) {
            log.info(" add:Input " + motorcycle.toString());
            MotorcycleData motorcycleData = transformMotorcycle.transform(motorcycle);
            MotorcycleData updatedMotorcycleData = motorcycleDataRepository.save(motorcycleData);
            log.info(" add:Input {}", motorcycleData.toString());
            return  transformMotorcycleData.transform(updatedMotorcycleData);
        }

        @Override
        public Motorcycle update(Motorcycle motorcycle) {
            Optional<MotorcycleData> optional  = motorcycleDataRepository.findById(motorcycle.getId());
            if(optional.isPresent()){
                MotorcycleData motorcycleData = transformMotorcycle.transform(motorcycle);
                MotorcycleData updatedMotorcycleData = motorcycleDataRepository.save( motorcycleData);
                return transformMotorcycleData.transform(updatedMotorcycleData);
            }
            else {
                log.error("Motorcycle record with id: {} do not exist",motorcycle.getId());
            }
            return null;
        }
    @Override
    public void delete(Integer id) {
        log.info(" Input >> {}",id);
        Optional<MotorcycleData> optional = motorcycleDataRepository.findById(id);
        if( optional.isPresent()) {
            MotorcycleData motorcycleDatum = optional.get();
            motorcycleDataRepository.delete(optional.get());
            log.info(" Successfully deleted Motorcycle record with id: {}",id);
        }
        else {
            log.error(" Unable to locate motorcycle with id: {}", id);
        }
    }

    @Override
    public List<Motorcycle> getAvailableMotorcycles() {
        List<Motorcycle> allMotorcycles = getAllMotorcycles();
        List<Motorcycle> availableMotorcycles = new ArrayList<>();
        for(Motorcycle motorcycle : allMotorcycles) {
            if(motorcycle.isAvailable()) {
                availableMotorcycles.add(motorcycle);
            }
        }
        return availableMotorcycles;
    }
}

