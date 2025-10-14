package com.gabriel.model;

import lombok.Data;

import java.util.List;
@Data
public class MotorcycleCategory {
    String categoryName;
    List<Motorcycle> motorcycles;
}