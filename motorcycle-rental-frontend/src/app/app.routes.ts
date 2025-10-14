import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home';
import { MotorcycleListComponent } from './components/motorcycle-list/motorcycle-list';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'motorcycles', component: MotorcycleListComponent },
  { path: 'home', redirectTo: '', pathMatch: 'full' },
  { path: '**', redirectTo: '' }
];