import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MotorcycleRentalService } from '../../services/motorcycle-rental.service';
import { Motorcycle } from '../../models/motorcycle.models';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './home.html',
  styleUrls: ['./home.css']
})
export class HomeComponent implements OnInit {
  featuredMotorcycles: Motorcycle[] = [];
  isLoading = true;

  constructor(private motorcycleRentalService: MotorcycleRentalService) {}

  ngOnInit() {
    this.loadFeaturedMotorcycles();
  }

  loadFeaturedMotorcycles() {
    this.motorcycleRentalService.getAvailableMotorcycles().subscribe({
      next: (motorcycles: Motorcycle[]) => {
        this.featuredMotorcycles = motorcycles.slice(0, 6); // Show first 6 available motorcycles
        this.isLoading = false;
      },
      error: (error: any) => {
        console.error('Error loading featured motorcycles:', error);
        this.isLoading = false;
      }
    });
  }

  getMotorcycleTypes(): string[] {
    const types = new Set<string>();
    this.featuredMotorcycles.forEach(motorcycle => {
      types.add(motorcycle.bikeType);
    });
    return Array.from(types);
  }

  getMotorcycleBrands(): string[] {
    const brands = new Set<string>();
    this.featuredMotorcycles.forEach(motorcycle => {
      brands.add(motorcycle.brand);
    });
    return Array.from(brands);
  }
}