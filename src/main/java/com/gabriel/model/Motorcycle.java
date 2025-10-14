package com.gabriel.model;

import lombok.Data;

@Data
public class Motorcycle {
    int id;
    String name;
    String description;
    String bikeType; // Sport, Cruiser, Touring, Adventure, Scooter, etc.
    String brand; // Honda, Yamaha, Kawasaki, Suzuki, Ducati, etc.
    String imageFile;
    String engineSize; // 125cc, 250cc, 500cc, 1000cc, etc.
    String fuelType; // Gasoline, Electric, Hybrid
    String transmission; // Manual, Automatic, CVT
    String hourlyRate; // Price per hour
    String dailyRate; // Price per day
    String weeklyRate; // Price per week
    boolean isAvailable;
    String bikeCondition; // Excellent, Good, Fair
    String features; // ABS, GPS, Bluetooth, etc.
    String color;
    int year;
    String licensePlate;
    double mileage;
}
