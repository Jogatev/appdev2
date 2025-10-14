package com.gabriel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gabriel.enums.RentalItemStatus;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "rental_item_data")
public class RentalItemData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    int rentalId;
    int customerId;
    String customerName;
    int motorcycleId;
    String motorcycleName;
    String motorcycleDescription;
    String motorcycleType;
    String motorcycleBrand;
    String engineSize;
    String fuelType;
    String transmission;
    String motorcycleImageFile;
    String licensePlate;
    double rentalDuration; // Hours, Days, or Weeks
    double hourlyRate;
    double dailyRate;
    double weeklyRate;
    double totalAmount;
    RentalItemStatus status;
    Date rentalStartDate;
    Date rentalEndDate;
    String pickupLocation;
    String returnLocation;
    double startMileage;
    double endMileage;
    String notes;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date lastUpdated;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date created;
}
