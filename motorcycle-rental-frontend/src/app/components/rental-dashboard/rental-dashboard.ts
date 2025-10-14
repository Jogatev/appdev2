import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MotorcycleRentalService } from '../../services/motorcycle-rental.service';
import { Motorcycle } from '../../models/motorcycle.models';

export interface RentalRecord {
  id: string;
  motorcycleId: number;
  motorcycleName: string;
  customerName: string;
  customerEmail: string;
  customerPhone: string;
  startDate: Date;
  endDate: Date;
  duration: string;
  totalAmount: number;
  status: 'active' | 'completed' | 'cancelled' | 'overdue';
  pickupLocation: string;
  returnLocation: string;
  paymentMethod: string;
  createdAt: Date;
}

@Component({
  selector: 'app-rental-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './rental-dashboard.html',
  styleUrls: ['./rental-dashboard.css']
})
export class RentalDashboardComponent implements OnInit {
  rentals: RentalRecord[] = [];
  motorcycles: Motorcycle[] = [];
  filteredRentals: RentalRecord[] = [];
  selectedStatus: string = 'all';
  searchTerm: string = '';
  isLoading = true;

  statusOptions = [
    { value: 'all', label: 'All Rentals' },
    { value: 'active', label: 'Active' },
    { value: 'completed', label: 'Completed' },
    { value: 'cancelled', label: 'Cancelled' },
    { value: 'overdue', label: 'Overdue' }
  ];

  constructor(private motorcycleRentalService: MotorcycleRentalService) {}

  ngOnInit() {
    this.loadData();
  }

  loadData() {
    this.isLoading = true;
    
    // Load motorcycles
    this.motorcycleRentalService.getAvailableMotorcycles().subscribe({
      next: (motorcycles: Motorcycle[]) => {
        this.motorcycles = motorcycles;
        this.loadRentals();
      },
      error: (error: any) => {
        console.error('Error loading motorcycles:', error);
        this.loadRentals();
      }
    });
  }

  loadRentals() {
    // Mock data for demonstration
    this.rentals = [
      {
        id: 'BR001',
        motorcycleId: 39,
        motorcycleName: 'Honda CBR600RR',
        customerName: 'Juan Dela Cruz',
        customerEmail: 'juan@example.com',
        customerPhone: '+63 912 345 6789',
        startDate: new Date('2024-01-15'),
        endDate: new Date('2024-01-17'),
        duration: 'daily',
        totalAmount: 300,
        status: 'active',
        pickupLocation: 'Bon Rental Main Office - Makati',
        returnLocation: 'Bon Rental Main Office - Makati',
        paymentMethod: 'cash',
        createdAt: new Date('2024-01-14')
      },
      {
        id: 'BR002',
        motorcycleId: 40,
        motorcycleName: 'Yamaha R1',
        customerName: 'Maria Santos',
        customerEmail: 'maria@example.com',
        customerPhone: '+63 917 654 3210',
        startDate: new Date('2024-01-10'),
        endDate: new Date('2024-01-12'),
        duration: 'daily',
        totalAmount: 400,
        status: 'completed',
        pickupLocation: 'Bon Rental Branch - BGC',
        returnLocation: 'Bon Rental Branch - BGC',
        paymentMethod: 'card',
        createdAt: new Date('2024-01-09')
      },
      {
        id: 'BR003',
        motorcycleId: 42,
        motorcycleName: 'Harley-Davidson Street Bob',
        customerName: 'Pedro Garcia',
        customerEmail: 'pedro@example.com',
        customerPhone: '+63 918 765 4321',
        startDate: new Date('2024-01-08'),
        endDate: new Date('2024-01-10'),
        duration: 'daily',
        totalAmount: 350,
        status: 'overdue',
        pickupLocation: 'Bon Rental Main Office - Makati',
        returnLocation: 'Bon Rental Main Office - Makati',
        paymentMethod: 'gcash',
        createdAt: new Date('2024-01-07')
      }
    ];

    this.filteredRentals = [...this.rentals];
    this.isLoading = false;
  }

  onStatusChange() {
    this.filterRentals();
  }

  onSearchChange() {
    this.filterRentals();
  }

  filterRentals() {
    let filtered = [...this.rentals];

    // Filter by status
    if (this.selectedStatus !== 'all') {
      filtered = filtered.filter(rental => rental.status === this.selectedStatus);
    }

    // Filter by search term
    if (this.searchTerm) {
      const term = this.searchTerm.toLowerCase();
      filtered = filtered.filter(rental =>
        rental.customerName.toLowerCase().includes(term) ||
        rental.motorcycleName.toLowerCase().includes(term) ||
        rental.id.toLowerCase().includes(term) ||
        rental.customerEmail.toLowerCase().includes(term)
      );
    }

    this.filteredRentals = filtered;
  }

  getStatusClass(status: string): string {
    switch (status) {
      case 'active': return 'badge bg-success';
      case 'completed': return 'badge bg-primary';
      case 'cancelled': return 'badge bg-secondary';
      case 'overdue': return 'badge bg-danger';
      default: return 'badge bg-light text-dark';
    }
  }

  getStatusIcon(status: string): string {
    switch (status) {
      case 'active': return 'fas fa-play-circle';
      case 'completed': return 'fas fa-check-circle';
      case 'cancelled': return 'fas fa-times-circle';
      case 'overdue': return 'fas fa-exclamation-triangle';
      default: return 'fas fa-question-circle';
    }
  }

  getTotalRevenue(): number {
    return this.rentals
      .filter(rental => rental.status === 'completed')
      .reduce((total, rental) => total + rental.totalAmount, 0);
  }

  getActiveRentals(): number {
    return this.rentals.filter(rental => rental.status === 'active').length;
  }

  getOverdueRentals(): number {
    return this.rentals.filter(rental => rental.status === 'overdue').length;
  }

  getTotalRentals(): number {
    return this.rentals.length;
  }
}
