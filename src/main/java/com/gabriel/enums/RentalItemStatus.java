package com.gabriel.enums;

public enum RentalItemStatus {
    RESERVED,      // Motorcycle is reserved but not yet picked up
    ACTIVE,        // Motorcycle is currently rented out
    RETURNED,      // Motorcycle has been returned
    OVERDUE,       // Motorcycle is overdue for return
    DAMAGED,       // Motorcycle returned with damage
    MAINTENANCE,   // Motorcycle is under maintenance/service
    FUEL_LOW,      // Motorcycle needs fuel
    ACCIDENT,      // Motorcycle involved in accident
    CANCELLED      // Rental was cancelled
}
