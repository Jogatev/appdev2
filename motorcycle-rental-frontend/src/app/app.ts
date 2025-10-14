import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet, RouterModule } from '@angular/router';
import { MotorcycleRentalService } from './services/motorcycle-rental.service';
import { Menu } from './models/motorcycle.models';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterModule],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class AppComponent implements OnInit {
  title = 'Bon Rental';
  menus: Menu[] = [];

  constructor(private motorcycleRentalService: MotorcycleRentalService) {}

  ngOnInit() {
    this.loadMenus();
  }

  loadMenus() {
    this.motorcycleRentalService.getMenus().subscribe({
      next: (menus) => {
        this.menus = menus;
        // Update menu items for motorcycle rental
        this.updateMenusForRental();
      },
      error: (error) => {
        console.error('Error loading menus:', error);
        // Fallback menu items
        this.menus = this.getDefaultMenus();
      }
    });
  }

  updateMenusForRental() {
    // Update existing menu items to reflect motorcycle rental business
    this.menus.forEach(menu => {
      switch(menu.name) {
        case 'Products':
          menu.name = 'Motorcycles';
          menu.description = 'Browse our motorcycle fleet';
          menu.routerPath = 'motorcycles';
          menu.icon = 'motorcycle.svg';
          break;
        case 'Cart':
          menu.name = 'Rental Cart';
          menu.description = 'View rental cart';
          menu.routerPath = 'cart';
          menu.icon = 'cart.svg';
          break;
        case 'Orders':
          menu.name = 'Rentals';
          menu.description = 'View rental history';
          menu.routerPath = 'rentals';
          menu.icon = 'rental.svg';
          break;
        case 'Customer':
          menu.name = 'Customer';
          menu.description = 'Customer information';
          menu.routerPath = 'customer';
          menu.icon = 'customer.svg';
          break;
      }
    });
  }

  getDefaultMenus(): Menu[] {
    return [
      { id: 1, name: 'Home', description: 'Welcome to our motorcycle rental shop', routerPath: '', categoryName: 'main', icon: 'home.svg' },
      { id: 2, name: 'Motorcycles', description: 'Browse our motorcycle fleet', routerPath: 'motorcycles', categoryName: 'main', icon: 'motorcycle.svg' },
      { id: 3, name: 'Customer', description: 'Customer information', routerPath: 'customer', categoryName: 'main', icon: 'customer.svg' },
      { id: 4, name: 'Rental Cart', description: 'View rental cart', routerPath: 'cart', categoryName: 'main', icon: 'cart.svg' },
      { id: 5, name: 'Rentals', description: 'View rental history', routerPath: 'rentals', categoryName: 'main', icon: 'rental.svg' },
      { id: 6, name: 'Contact Us', description: 'See our location', routerPath: 'contact', categoryName: 'main', icon: 'contact.svg' }
    ];
  }
}