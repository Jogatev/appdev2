import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home';
import { MotorcycleListComponent } from './components/motorcycle-list/motorcycle-list';
import { RentalDashboardComponent } from './components/rental-dashboard/rental-dashboard';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'motorcycles', component: MotorcycleListComponent },
  { path: 'dashboard', component: RentalDashboardComponent },
  { path: 'contact', component: HomeComponent }, // Placeholder for contact page
  { path: '**', redirectTo: '' }
];
