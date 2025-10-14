-- Bon Rental Motorcycle Data Population Script
-- This script populates the motorcycle_data table with sample motorcycles

-- Clear existing data
DELETE FROM motorcycle_data;

-- Reset auto-increment
ALTER TABLE motorcycle_data AUTO_INCREMENT = 1;

-- Insert sample motorcycles
INSERT INTO motorcycle_data (name, description, bikeType, brand, engineSize, fuelType, transmission, hourlyRate, dailyRate, weeklyRate, isAvailable, bikeCondition, features, color, year, licensePlate, mileage, imageFile) VALUES

-- Sport Bikes
('Honda CBR600RR', 'High-performance sport bike perfect for track days and spirited riding', 'Sport', 'Honda', '600cc', 'Gasoline', 'Manual', '25.00', '150.00', '800.00', true, 'Excellent', 'ABS, Digital Display, LED Lights', 'Red', 2023, 'CBR-001', 1250.5, 'honda_cbr600rr.jpg'),
('Yamaha R1', 'Flagship sport bike with cutting-edge technology and incredible power', 'Sport', 'Yamaha', '1000cc', 'Gasoline', 'Manual', '35.00', '200.00', '1100.00', true, 'Excellent', 'ABS, Traction Control, Quick Shifter, LED Lights', 'Blue', 2023, 'R1-001', 890.2, 'yamaha_r1.jpg'),
('Kawasaki Ninja ZX-10R', 'Track-focused sport bike with championship-winning pedigree', 'Sport', 'Kawasaki', '1000cc', 'Gasoline', 'Manual', '32.00', '180.00', '1000.00', true, 'Good', 'ABS, Traction Control, Launch Control', 'Green', 2022, 'ZX10-001', 2100.8, 'kawasaki_ninja_zx10r.jpg'),

-- Cruiser Bikes
('Harley-Davidson Street Bob', 'Classic American cruiser with authentic Harley-Davidson styling', 'Cruiser', 'Harley-Davidson', '107ci', 'Gasoline', 'Manual', '30.00', '175.00', '950.00', true, 'Excellent', 'Cruise Control, ABS, LED Headlight', 'Black', 2023, 'HD-001', 750.3, 'harley_street_bob.jpg'),
('Indian Scout', 'Modern cruiser with classic styling and smooth performance', 'Cruiser', 'Indian', '1133cc', 'Gasoline', 'Manual', '28.00', '160.00', '900.00', true, 'Good', 'ABS, Digital Display, LED Lights', 'Red', 2022, 'IND-001', 1450.7, 'indian_scout.jpg'),
('Honda Shadow', 'Reliable cruiser perfect for comfortable long-distance riding', 'Cruiser', 'Honda', '750cc', 'Gasoline', 'Manual', '22.00', '130.00', '750.00', true, 'Excellent', 'ABS, Comfortable Seat', 'Silver', 2023, 'SH-001', 980.4, 'honda_shadow.jpg'),

-- Touring Bikes
('BMW R1250GS Adventure', 'Ultimate adventure touring bike for long-distance travel', 'Touring', 'BMW', '1254cc', 'Gasoline', 'Manual', '40.00', '220.00', '1200.00', true, 'Excellent', 'ABS, Traction Control, GPS, Heated Grips, Cruise Control', 'White', 2023, 'BMW-001', 3200.1, 'bmw_r1250gs.jpg'),
('Honda Gold Wing', 'Premium touring bike with luxury features and smooth ride', 'Touring', 'Honda', '1833cc', 'Gasoline', 'Automatic', '45.00', '250.00', '1400.00', true, 'Excellent', 'ABS, Traction Control, GPS, Audio System, Heated Seats', 'Black', 2023, 'GW-001', 2100.5, 'honda_goldwing.jpg'),
('Yamaha FJR1300', 'Sport touring bike combining performance with comfort', 'Touring', 'Yamaha', '1298cc', 'Gasoline', 'Manual', '38.00', '210.00', '1150.00', true, 'Good', 'ABS, Traction Control, Cruise Control, Heated Grips', 'Blue', 2022, 'FJR-001', 2800.9, 'yamaha_fjr1300.jpg'),

-- Adventure Bikes
('KTM 1290 Super Adventure', 'High-performance adventure bike for off-road and on-road', 'Adventure', 'KTM', '1301cc', 'Gasoline', 'Manual', '42.00', '230.00', '1250.00', true, 'Excellent', 'ABS, Traction Control, GPS, Off-road Mode', 'Orange', 2023, 'KTM-001', 1800.6, 'ktm_1290_super_adventure.jpg'),
('Ducati Multistrada V4', 'Italian adventure bike with sport bike performance', 'Adventure', 'Ducati', '1158cc', 'Gasoline', 'Manual', '48.00', '260.00', '1400.00', true, 'Excellent', 'ABS, Traction Control, GPS, Adaptive Cruise Control', 'Red', 2023, 'DUC-001', 950.2, 'ducati_multistrada_v4.jpg'),
('Triumph Tiger 900', 'Versatile adventure bike for all types of terrain', 'Adventure', 'Triumph', '888cc', 'Gasoline', 'Manual', '35.00', '190.00', '1050.00', true, 'Good', 'ABS, Traction Control, Off-road Mode', 'Green', 2022, 'TRI-001', 2200.3, 'triumph_tiger_900.jpg'),

-- Scooters
('Vespa GTS 300', 'Classic Italian scooter perfect for city commuting', 'Scooter', 'Vespa', '300cc', 'Gasoline', 'CVT', '15.00', '80.00', '450.00', true, 'Excellent', 'ABS, Digital Display, LED Lights', 'White', 2023, 'VES-001', 450.7, 'vespa_gts_300.jpg'),
('Honda PCX 150', 'Efficient scooter ideal for urban transportation', 'Scooter', 'Honda', '150cc', 'Gasoline', 'CVT', '12.00', '65.00', '350.00', true, 'Good', 'ABS, Digital Display', 'Blue', 2022, 'PCX-001', 1200.4, 'honda_pcx_150.jpg'),
('Yamaha XMAX 300', 'Maxi scooter with sporty styling and comfort', 'Scooter', 'Yamaha', '292cc', 'Gasoline', 'CVT', '18.00', '95.00', '500.00', true, 'Excellent', 'ABS, Traction Control, Digital Display', 'Black', 2023, 'XMAX-001', 680.9, 'yamaha_xmax_300.jpg'),

-- Electric Bikes
('Zero SR/F', 'High-performance electric motorcycle with instant torque', 'Sport', 'Zero', 'Electric', 'Electric', 'Automatic', '30.00', '170.00', '950.00', true, 'Excellent', 'ABS, Traction Control, Regenerative Braking, Mobile App', 'White', 2023, 'ZERO-001', 250.1, 'zero_srf.jpg'),
('Harley-Davidson LiveWire', 'Electric motorcycle with Harley-Davidson heritage', 'Sport', 'Harley-Davidson', 'Electric', 'Electric', 'Automatic', '35.00', '190.00', '1050.00', true, 'Good', 'ABS, Traction Control, Regenerative Braking', 'Black', 2022, 'LW-001', 180.5, 'harley_livewire.jpg'),

-- Additional Sport Bikes
('Suzuki GSX-R750', 'Middleweight sport bike with race-proven performance', 'Sport', 'Suzuki', '750cc', 'Gasoline', 'Manual', '28.00', '160.00', '900.00', true, 'Good', 'ABS, Traction Control, Quick Shifter', 'Blue', 2022, 'GSX-001', 1650.2, 'suzuki_gsxr750.jpg'),
('Ducati Panigale V2', 'Italian sport bike with racing DNA and stunning design', 'Sport', 'Ducati', '955cc', 'Gasoline', 'Manual', '40.00', '220.00', '1200.00', true, 'Excellent', 'ABS, Traction Control, Quick Shifter, LED Lights', 'Red', 2023, 'PAN-001', 890.7, 'ducati_panigale_v2.jpg'),

-- Additional Cruiser Bikes
('Victory Vegas', 'American cruiser with bold styling and powerful engine', 'Cruiser', 'Victory', '106ci', 'Gasoline', 'Manual', '32.00', '180.00', '1000.00', true, 'Good', 'ABS, Digital Display', 'Black', 2022, 'VIC-001', 2100.8, 'victory_vegas.jpg'),
('Yamaha VMAX', 'Muscle cruiser with incredible acceleration and power', 'Cruiser', 'Yamaha', '1679cc', 'Gasoline', 'Manual', '38.00', '210.00', '1150.00', true, 'Excellent', 'ABS, Traction Control, Digital Display', 'Black', 2023, 'VMX-001', 750.3, 'yamaha_vmax.jpg');

-- Update the sequence for auto-increment
SELECT * FROM motorcycle_data ORDER BY id;
