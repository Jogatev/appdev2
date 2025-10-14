import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MotorcycleRentalService } from '../../services/motorcycle-rental.service';
import { Motorcycle, MotorcycleCategory } from '../../models/motorcycle.models';
import { RentalBookingComponent, RentalBooking } from '../rental-booking/rental-booking';
import { RentalConfirmationComponent } from '../rental-confirmation/rental-confirmation';

@Component({
  selector: 'app-motorcycle-list',
  standalone: true,
  imports: [CommonModule, FormsModule, RentalBookingComponent, RentalConfirmationComponent],
  templateUrl: './motorcycle-list.html',
  styleUrls: ['./motorcycle-list.css']
})
export class MotorcycleListComponent implements OnInit {
  motorcycleCategories: MotorcycleCategory[] = [];
  availableMotorcycles: Motorcycle[] = [];
  selectedCategory: string = 'all';
  searchTerm: string = '';
  isLoading = true;
  error: string | null = null;

  // Booking state
  showBookingModal = false;
  showConfirmationModal = false;
  selectedMotorcycle: Motorcycle | null = null;
  currentBooking: RentalBooking | null = null;

  constructor(private motorcycleRentalService: MotorcycleRentalService) {}

  ngOnInit() {
    this.loadMotorcycles();
  }

  loadMotorcycles() {
    this.isLoading = true;
    this.error = null;

    // Load motorcycle categories
    this.motorcycleRentalService.getMotorcycleCategories().subscribe({
      next: (categories: MotorcycleCategory[]) => {
        this.motorcycleCategories = categories;
        this.isLoading = false;
      },
      error: (error: any) => {
        console.error('Error loading motorcycle categories:', error);
        this.error = 'Failed to load motorcycles. Please try again later.';
        this.isLoading = false;
        // Load available motorcycles as fallback
        this.loadAvailableMotorcycles();
      }
    });

    // Load available motorcycles
    this.loadAvailableMotorcycles();
  }

  loadAvailableMotorcycles() {
    this.motorcycleRentalService.getAvailableMotorcycles().subscribe({
      next: (motorcycles: Motorcycle[]) => {
        this.availableMotorcycles = motorcycles;
      },
      error: (error: any) => {
        console.error('Error loading available motorcycles:', error);
      }
    });
  }

  getFilteredMotorcycles(): Motorcycle[] {
    let motorcycles: Motorcycle[] = [];

    if (this.selectedCategory === 'all') {
      // Get all motorcycles from all categories
      this.motorcycleCategories.forEach(category => {
        motorcycles = motorcycles.concat(category.motorcycles);
      });
    } else {
      // Get motorcycles from selected category
      const category = this.motorcycleCategories.find(cat => cat.categoryName === this.selectedCategory);
      if (category) {
        motorcycles = category.motorcycles;
      }
    }

    // Filter by search term
    if (this.searchTerm) {
      motorcycles = motorcycles.filter(motorcycle =>
        motorcycle.name.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
        motorcycle.brand.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
        motorcycle.bikeType.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
        motorcycle.engineSize.toLowerCase().includes(this.searchTerm.toLowerCase())
      );
    }

    return motorcycles;
  }

  getCategories(): string[] {
    return this.motorcycleCategories.map(category => category.categoryName);
  }

  onCategoryChange(event: Event) {
    const target = event.target as HTMLSelectElement;
    this.selectedCategory = target.value;
  }

  onSearchChange(event: Event) {
    const target = event.target as HTMLInputElement;
    this.searchTerm = target.value;
  }

  rentMotorcycle(motorcycle: Motorcycle) {
    this.selectedMotorcycle = motorcycle;
    this.showBookingModal = true;
  }

  onBookingSubmitted(booking: RentalBooking) {
    this.currentBooking = booking;
    this.showBookingModal = false;
    this.showConfirmationModal = true;
  }

  onBookingCancelled() {
    this.showBookingModal = false;
    this.selectedMotorcycle = null;
  }

  onConfirmationClosed() {
    this.showConfirmationModal = false;
    this.currentBooking = null;
    this.selectedMotorcycle = null;
  }

  onPrintReceipt() {
    console.log('Printing receipt for booking:', this.currentBooking);
  }

  getAvailabilityClass(motorcycle: Motorcycle): string {
    return motorcycle.available ? 'available' : 'unavailable';
  }

  getAvailabilityText(motorcycle: Motorcycle): string {
    return motorcycle.available ? 'Available' : 'Not Available';
  }

  formatPrice(price: string): string {
    return `₱${price}`;
  }
}