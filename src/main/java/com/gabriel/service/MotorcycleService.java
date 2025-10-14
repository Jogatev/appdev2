package com.gabriel.service;

import com.gabriel.model.Motorcycle;
import com.gabriel.model.MotorcycleCategory;

import java.util.*;

public interface MotorcycleService {

    List<Motorcycle> getAllMotorcycles();
    Motorcycle[] getAll();
    Motorcycle get(Integer id);
    Motorcycle create(Motorcycle motorcycle);
    Motorcycle update(Motorcycle motorcycle);
    void delete(Integer id);
    Map<String, List<Motorcycle>> getCategoryMappedMotorcycles();
    List<MotorcycleCategory> listMotorcycleCategories();
    List<Motorcycle> getAvailableMotorcycles();
}
