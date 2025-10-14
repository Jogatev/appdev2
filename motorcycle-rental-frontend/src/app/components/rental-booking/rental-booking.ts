import { Component, Input, Output, EventEmitter, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Motorcycle } from '../../models/motorcycle.models';

export interface RentalBooking {
  motorcycleId: number;
  customerName: string;
  customerEmail: string;
  customerPhone: string;
  rentalStartDate: string;
  rentalEndDate: string;
  rentalDuration: string;
  pickupLocation: string;
  returnLocation: string;
  totalAmount: number;
  paymentMethod: string;
  specialRequests: string;
}

@Component({
  selector: 'app-rental-booking',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './rental-booking.html',
  styleUrls: ['./rental-booking.css']
})
export class RentalBookingComponent implements OnInit {
  @Input() motorcycle: Motorcycle | null = null;
  @Output() bookingSubmitted = new EventEmitter<RentalBooking>();
  @Output() bookingCancelled = new EventEmitter<void>();

  booking: RentalBooking = {
    motorcycleId: 0,
    customerName: '',
    customerEmail: '',
    customerPhone: '',
    rentalStartDate: '',
    rentalEndDate: '',
    rentalDuration: 'daily',
    pickupLocation: '',
    returnLocation: '',
    totalAmount: 0,
    paymentMethod: 'cash',
    specialRequests: ''
  };

  rentalDurations = [
    { value: 'hourly', label: 'Hourly', multiplier: 1 },
    { value: 'daily', label: 'Daily', multiplier: 8 },
    { value: 'weekly', label: 'Weekly', multiplier: 56 }
  ];

  paymentMethods = [
    { value: 'cash', label: 'Cash Payment' },
    { value: 'card', label: 'Credit/Debit Card' },
    { value: 'gcash', label: 'GCash' },
    { value: 'paymaya', label: 'PayMaya' }
  ];

  locations = [
    'Bon Rental Main Office - Makati',
    'Bon Rental Branch - BGC',
    'Bon Rental Branch - Ortigas',
    'Bon Rental Branch - Alabang',
    'Airport Pickup - NAIA Terminal 1',
    'Airport Pickup - NAIA Terminal 2',
    'Airport Pickup - NAIA Terminal 3'
  ];

  ngOnInit() {
    if (this.motorcycle) {
      this.booking.motorcycleId = this.motorcycle.id;
      this.booking.pickupLocation = this.locations[0];
      this.booking.returnLocation = this.locations[0];
      this.updateTotalAmount();
    }
  }

  onDurationChange() {
    this.updateTotalAmount();
  }

  onDateChange() {
    this.updateTotalAmount();
  }

  updateTotalAmount() {
    if (!this.motorcycle || !this.booking.rentalStartDate || !this.booking.rentalEndDate) {
      this.booking.totalAmount = 0;
      return;
    }

    const startDate = new Date(this.booking.rentalStartDate);
    const endDate = new Date(this.booking.rentalEndDate);
    const diffTime = Math.abs(endDate.getTime() - startDate.getTime());
    const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

    const duration = this.rentalDurations.find(d => d.value === this.booking.rentalDuration);
    if (!duration) return;

    let baseRate = 0;
    switch (this.booking.rentalDuration) {
      case 'hourly':
        baseRate = parseFloat(this.motorcycle.hourlyRate);
        this.booking.totalAmount = baseRate * Math.max(1, diffDays * 8);
        break;
      case 'daily':
        baseRate = parseFloat(this.motorcycle.dailyRate);
        this.booking.totalAmount = baseRate * Math.max(1, diffDays);
        break;
      case 'weekly':
        baseRate = parseFloat(this.motorcycle.weeklyRate);
        this.booking.totalAmount = baseRate * Math.max(1, Math.ceil(diffDays / 7));
        break;
    }
  }

  onSubmit() {
    if (this.isFormValid()) {
      this.bookingSubmitted.emit(this.booking);
    }
  }

  onCancel() {
    this.bookingCancelled.emit();
  }

  isFormValid(): boolean {
    return !!(
      this.booking.customerName &&
      this.booking.customerEmail &&
      this.booking.customerPhone &&
      this.booking.rentalStartDate &&
      this.booking.rentalEndDate &&
      this.booking.pickupLocation &&
      this.booking.returnLocation
    );
  }

  getMinDate(): string {
    const today = new Date();
    return today.toISOString().split('T')[0];
  }

  getDurationLabel(): string {
    const duration = this.rentalDurations.find(d => d.value === this.booking.rentalDuration);
    return duration ? duration.label : '';
  }

  getMaxDate(): string {
    const maxDate = new Date();
    maxDate.setMonth(maxDate.getMonth() + 3); // 3 months in advance
    return maxDate.toISOString().split('T')[0];
  }
}
