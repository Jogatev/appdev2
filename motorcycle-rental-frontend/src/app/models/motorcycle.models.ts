export interface Motorcycle {
  id: number;
  name: string;
  description: string;
  bikeType: string; // Sport, Cruiser, Touring, Adventure, Scooter, etc.
  brand: string; // Honda, Yamaha, Kawasaki, Suzuki, Ducati, etc.
  imageFile: string;
  engineSize: string; // 125cc, 250cc, 500cc, 1000cc, etc.
  fuelType: string; // Gasoline, Electric, Hybrid
  transmission: string; // Manual, Automatic, CVT
  hourlyRate: string; // Price per hour
  dailyRate: string; // Price per day
  weeklyRate: string; // Price per week
  available: boolean;
  bikeCondition: string; // Excellent, Good, Fair
  features: string; // ABS, GPS, Bluetooth, etc.
  color: string;
  year: number;
  licensePlate: string;
  mileage: number;
}

export interface Rental {
  id: number;
  customerId: string;
  customerName: string;
  customerPhone: string;
  customerEmail: string;
  items: RentalItem[];
  rentalStartDate: Date;
  rentalEndDate: Date;
  rentalDuration: string; // Hours, Days, Weeks
  totalAmount: number;
  paymentStatus: string; // Pending, Paid, Refunded
  rentalStatus: string; // Active, Completed, Cancelled
  notes: string;
}

export interface RentalItem {
  id: number;
  rentalId: number;
  customerId: number;
  customerName: string;
  motorcycleId: number;
  motorcycleName: string;
  motorcycleDescription: string;
  motorcycleType: string;
  motorcycleBrand: string;
  engineSize: string;
  fuelType: string;
  transmission: string;
  motorcycleImageFile: string;
  licensePlate: string;
  rentalDuration: number; // Hours, Days, or Weeks
  hourlyRate: number;
  dailyRate: number;
  weeklyRate: number;
  totalAmount: number;
  status: RentalItemStatus;
  rentalStartDate: Date;
  rentalEndDate: Date;
  created: Date;
  lastUpdated: Date;
  notes: string;
  pickupLocation: string;
  returnLocation: string;
  startMileage: number;
  endMileage: number;
}

export enum RentalItemStatus {
  RESERVED = 'RESERVED',
  ACTIVE = 'ACTIVE',
  RETURNED = 'RETURNED',
  OVERDUE = 'OVERDUE',
  DAMAGED = 'DAMAGED',
  MAINTENANCE = 'MAINTENANCE',
  FUEL_LOW = 'FUEL_LOW',
  ACCIDENT = 'ACCIDENT',
  CANCELLED = 'CANCELLED'
}

export interface MotorcycleCategory {
  categoryName: string;
  motorcycles: Motorcycle[];
}

export interface Menu {
  id: number;
  name: string;
  description: string;
  routerPath: string;
  categoryName: string;
  icon: string;
}

