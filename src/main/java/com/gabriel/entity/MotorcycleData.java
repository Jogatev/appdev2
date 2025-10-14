package com.gabriel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "motorcycle_data")
public class MotorcycleData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String name;
    String description;
    String bikeType; // Sport, Cruiser, Touring, Adventure, Scooter, etc.
    String brand; // Honda, Yamaha, Kawasaki, Suzuki, Ducati, etc.
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
    String imageFile;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date lastUpdated;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date created;
}
