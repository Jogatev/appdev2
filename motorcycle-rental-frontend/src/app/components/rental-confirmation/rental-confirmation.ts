import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RentalBooking } from '../rental-booking/rental-booking';
import { Motorcycle } from '../../models/motorcycle.models';

@Component({
  selector: 'app-rental-confirmation',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './rental-confirmation.html',
  styleUrls: ['./rental-confirmation.css']
})
export class RentalConfirmationComponent {
  @Input() booking: RentalBooking | null = null;
  @Input() motorcycle: Motorcycle | null = null;
  @Output() confirmationClosed = new EventEmitter<void>();
  @Output() printReceipt = new EventEmitter<void>();

  rentalId: string = this.generateRentalId();
  confirmationNumber: string = this.generateConfirmationNumber();

  private generateRentalId(): string {
    return 'BR' + Date.now().toString().substr(-6);
  }

  private generateConfirmationNumber(): string {
    return 'CONF' + Math.random().toString(36).substr(2, 8).toUpperCase();
  }

  onClose() {
    this.confirmationClosed.emit();
  }

  onPrintReceipt() {
    this.printReceipt.emit();
    window.print();
  }

  getPickupInstructions(): string[] {
    return [
      'Arrive 15 minutes before your scheduled pickup time',
      'Bring a valid driver\'s license and government-issued ID',
      'Have your confirmation number ready',
      'Security deposit of ₱5,000 will be collected',
      'Inspect the motorcycle thoroughly before leaving',
      'Take photos of any existing damage'
    ];
  }

  getReturnInstructions(): string[] {
    return [
      'Return the motorcycle at the scheduled time',
      'Fill the fuel tank to the same level as pickup',
      'Remove all personal belongings',
      'Return all accessories (helmet, keys, etc.)',
      'Inspect for any new damage',
      'Collect your security deposit refund'
    ];
  }
}
