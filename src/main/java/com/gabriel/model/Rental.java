package com.gabriel.model;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class Rental {
    int id;
    String customerId;
    String customerName;
    String customerPhone;
    String customerEmail;
    List<RentalItem> items;
    Date rentalStartDate;
    Date rentalEndDate;
    String rentalDuration; // Hours, Days, Weeks
    double totalAmount;
    String paymentStatus; // Pending, Paid, Refunded
    String rentalStatus; // Active, Completed, Cancelled
    String notes;
}
