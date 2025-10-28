import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

interface Motorcycle {
  id: number;
  name: string;
  description: string;
  bikeType: string;
  brand: string;
  imageFile: string;
  engineSize: string;
  fuelType: string;
  transmission: string;
  hourlyRate: string;
  dailyRate: string;
  weeklyRate: string;
  bikeCondition: string;
  features: string;
  color: string;
  year: number;
  licensePlate: string;
  mileage: number;
  available: boolean;
}

interface MotorcycleCategory {
  categoryName: string;
  motorcycles: Motorcycle[];
}

interface BookingData {
  customerName: string;
  customerEmail: string;
  customerPhone: string;
  rentalDuration: string;
  rentalStartDate: string;
  rentalEndDate: string;
  totalAmount: number;
  motorcycleName: string;
}

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class AppComponent implements OnInit {
  title = 'Bon Rental - Motorcycle Rental Service';
  
  // Component state
  isLoading = false;
  error: string | null = null;
  selectedCategory = 'all';
  searchTerm = '';
  filteredMotorcycles: Motorcycle[] = [];
  motorcycleCategories: MotorcycleCategory[] = [];
  
  // Modal states
  showBookingModal = false;
  showConfirmationModal = false;
  selectedMotorcycle: Motorcycle | null = null;
  
  // Booking data
  bookingData: BookingData = {
    customerName: '',
    customerEmail: '',
    customerPhone: '',
    rentalDuration: 'hourly',
    rentalStartDate: '',
    rentalEndDate: '',
    totalAmount: 0,
    motorcycleName: ''
  };

  ngOnInit() {
    this.loadMotorcycles();
  }

  loadMotorcycles() {
    this.isLoading = true;
    this.error = null;
    
    fetch('http://localhost:8080/api/motorcycle')
      .then(response => response.json())
      .then(data => {
        this.motorcycleCategories = data;
        this.filterMotorcycles();
        this.isLoading = false;
      })
      .catch(error => {
        this.error = 'Failed to load motorcycles. Please try again.';
        this.isLoading = false;
        console.error('Error loading motorcycles:', error);
      });
  }

  onCategoryChange(event: Event) {
    const target = event.target as HTMLSelectElement;
    this.selectedCategory = target.value;
    this.filterMotorcycles();
  }

  onSearchChange(event: Event) {
    const target = event.target as HTMLInputElement;
    this.searchTerm = target.value;
    this.filterMotorcycles();
  }

  filterMotorcycles() {
    let allMotorcycles: Motorcycle[] = [];
    
    // Flatten all motorcycles from categories
    this.motorcycleCategories.forEach(category => {
      allMotorcycles = allMotorcycles.concat(category.motorcycles);
    });

    // Filter by category
    if (this.selectedCategory !== 'all') {
      allMotorcycles = allMotorcycles.filter(motorcycle => 
        motorcycle.bikeType === this.selectedCategory
      );
    }

    // Filter by search term
    if (this.searchTerm.trim()) {
      const searchLower = this.searchTerm.toLowerCase();
      allMotorcycles = allMotorcycles.filter(motorcycle =>
        motorcycle.name.toLowerCase().includes(searchLower) ||
        motorcycle.brand.toLowerCase().includes(searchLower) ||
        motorcycle.bikeType.toLowerCase().includes(searchLower)
      );
    }

    this.filteredMotorcycles = allMotorcycles;
  }

  rentMotorcycle(motorcycle: Motorcycle) {
    this.selectedMotorcycle = motorcycle;
    this.bookingData.motorcycleName = `${motorcycle.brand} ${motorcycle.name}`;
    this.showBookingModal = true;
  }

  closeBookingModal() {
    this.showBookingModal = false;
    this.selectedMotorcycle = null;
    this.resetBookingData();
  }

  onDurationChange() {
    this.calculateTotalAmount();
  }

  onDateChange() {
    this.calculateTotalAmount();
  }

  calculateTotalAmount() {
    if (!this.selectedMotorcycle || !this.bookingData.rentalStartDate || !this.bookingData.rentalEndDate) {
      this.bookingData.totalAmount = 0;
      return;
    }

    const startDate = new Date(this.bookingData.rentalStartDate);
    const endDate = new Date(this.bookingData.rentalEndDate);
    const diffTime = Math.abs(endDate.getTime() - startDate.getTime());
    const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

    let rate: number;
    switch (this.bookingData.rentalDuration) {
      case 'hourly':
        rate = parseFloat(this.selectedMotorcycle.hourlyRate);
        this.bookingData.totalAmount = rate * 24 * diffDays; // Approximate hourly calculation
        break;
      case 'daily':
        rate = parseFloat(this.selectedMotorcycle.dailyRate);
        this.bookingData.totalAmount = rate * diffDays;
        break;
      case 'weekly':
        rate = parseFloat(this.selectedMotorcycle.weeklyRate);
        this.bookingData.totalAmount = rate * Math.ceil(diffDays / 7);
        break;
      default:
        this.bookingData.totalAmount = 0;
    }
  }

  confirmBooking() {
    if (!this.validateBookingData()) {
      return;
    }

    // Here you would typically send the booking data to your backend
    console.log('Booking confirmed:', this.bookingData);
    
    this.showBookingModal = false;
    this.showConfirmationModal = true;
  }

  validateBookingData(): boolean {
    if (!this.bookingData.customerName.trim()) {
      alert('Please enter your name');
      return false;
    }
    if (!this.bookingData.customerEmail.trim()) {
      alert('Please enter your email');
      return false;
    }
    if (!this.bookingData.customerPhone.trim()) {
      alert('Please enter your phone number');
      return false;
    }
    if (!this.bookingData.rentalStartDate) {
      alert('Please select a start date');
      return false;
    }
    if (!this.bookingData.rentalEndDate) {
      alert('Please select an end date');
      return false;
    }
    return true;
  }

  closeConfirmationModal() {
    this.showConfirmationModal = false;
    this.resetBookingData();
  }

  printReceipt() {
    window.print();
  }

  getMinDate(): string {
    const today = new Date();
    return today.toISOString().split('T')[0];
  }

  getMaxDate(): string {
    const maxDate = new Date();
    maxDate.setMonth(maxDate.getMonth() + 6); // 6 months from now
    return maxDate.toISOString().split('T')[0];
  }

  trackByMotorcycleId(index: number, motorcycle: Motorcycle): number {
    return motorcycle.id;
  }

  private resetBookingData() {
    this.bookingData = {
      customerName: '',
      customerEmail: '',
      customerPhone: '',
      rentalDuration: 'hourly',
      rentalStartDate: '',
      rentalEndDate: '',
      totalAmount: 0,
      motorcycleName: ''
    };
  }
}