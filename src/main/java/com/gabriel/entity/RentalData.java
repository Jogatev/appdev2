package com.gabriel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "rental_data")
public class RentalData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String customerId;
    String customerName;
    String customerPhone;
    String customerEmail;
    Date rentalStartDate;
    Date rentalEndDate;
    String rentalDuration; // Hours, Days, Weeks
    double totalAmount;
    String paymentStatus; // Pending, Paid, Refunded
    String rentalStatus; // Active, Completed, Cancelled
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
