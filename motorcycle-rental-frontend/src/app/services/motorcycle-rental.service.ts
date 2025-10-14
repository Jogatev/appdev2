import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Motorcycle, MotorcycleCategory, Rental, RentalItem, Menu } from '../models/motorcycle.models';

@Injectable({
  providedIn: 'root'
})
export class MotorcycleRentalService {
  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }

  // Menu endpoints
  getMenus(): Observable<Menu[]> {
    return this.http.get<Menu[]>(`${this.apiUrl}/menu`);
  }

  // Motorcycle endpoints
  getMotorcycleCategories(): Observable<MotorcycleCategory[]> {
    return this.http.get<MotorcycleCategory[]>(`${this.apiUrl}/motorcycle`);
  }

  getMotorcycle(id: number): Observable<Motorcycle> {
    return this.http.get<Motorcycle>(`${this.apiUrl}/motorcycle/${id}`);
  }

  getAvailableMotorcycles(): Observable<Motorcycle[]> {
    return this.http.get<Motorcycle[]>(`${this.apiUrl}/motorcycle/available`);
  }

  addMotorcycle(motorcycle: Motorcycle): Observable<Motorcycle> {
    return this.http.put<Motorcycle>(`${this.apiUrl}/motorcycle`, motorcycle);
  }

  updateMotorcycle(motorcycle: Motorcycle): Observable<Motorcycle> {
    return this.http.post<Motorcycle>(`${this.apiUrl}/motorcycle`, motorcycle);
  }

  deleteMotorcycle(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/motorcycle/${id}`);
  }

  // Rental endpoints
  getAllRentals(): Observable<Rental[]> {
    return this.http.get<Rental[]>(`${this.apiUrl}/rental`);
  }

  getRental(id: number): Observable<Rental> {
    return this.http.get<Rental>(`${this.apiUrl}/rental/${id}`);
  }

  getRentalsByCustomer(customerId: string): Observable<Rental[]> {
    return this.http.get<Rental[]>(`${this.apiUrl}/rental/customer/${customerId}`);
  }

  createRental(rental: Rental): Observable<Rental> {
    return this.http.post<Rental>(`${this.apiUrl}/rental`, rental);
  }

  updateRental(id: number, rental: Rental): Observable<Rental> {
    return this.http.put<Rental>(`${this.apiUrl}/rental/${id}`, rental);
  }

  cancelRental(id: number): Observable<Rental> {
    return this.http.delete<Rental>(`${this.apiUrl}/rental/${id}`);
  }

  returnMotorcycle(id: number, rental: Rental): Observable<Rental> {
    return this.http.post<Rental>(`${this.apiUrl}/rental/${id}/return`, rental);
  }

  // Rental Item endpoints
  getAllRentalItems(): Observable<RentalItem[]> {
    return this.http.get<RentalItem[]>(`${this.apiUrl}/rentalitem`);
  }

  getRentalItems(customerId: number): Observable<RentalItem[]> {
    return this.http.get<RentalItem[]>(`${this.apiUrl}/rentalitem/customer/${customerId}`);
  }

  getCartItems(customerId: number): Observable<RentalItem[]> {
    return this.http.get<RentalItem[]>(`${this.apiUrl}/rentalitem/cart/${customerId}`);
  }

  addRentalItem(rentalItem: RentalItem): Observable<RentalItem> {
    return this.http.put<RentalItem>(`${this.apiUrl}/rentalitem`, rentalItem);
  }

  updateRentalItem(rentalItem: RentalItem): Observable<RentalItem> {
    return this.http.post<RentalItem>(`${this.apiUrl}/rentalitem`, rentalItem);
  }

  deleteRentalItem(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/rentalitem/${id}`);
  }

  returnRentalItem(id: number, rentalItem: RentalItem): Observable<RentalItem> {
    return this.http.post<RentalItem>(`${this.apiUrl}/rentalitem/${id}/return`, rentalItem);
  }
}

