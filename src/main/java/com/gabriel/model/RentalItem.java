package com.gabriel.model;
import com.gabriel.enums.RentalItemStatus;
import lombok.Data;

import java.util.*;

@Data
public class RentalItem {
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
    Date created;
    Date lastUpdated;
    String notes;
    String pickupLocation;
    String returnLocation;
    double startMileage;
    double endMileage;
}
