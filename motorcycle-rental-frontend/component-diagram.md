# Motorcycle Rental Frontend - Component Diagram

## Architecture Overview

This Angular application follows a component-based architecture with standalone components, services, and models.

## Component Diagram

```mermaid
graph TD
    %% Entry Point
    Index[index.html] --> App[app.html<br/>AppComponent]
    
    %% Main App Structure
    App --> Header[app-header<br/>Header Component]
    App --> RouterOutlet[router-outlet<br/>RouterModule]
    App --> Footer[app-footer<br/>Footer Component]
    
    %% Routed Components
    RouterOutlet --> Home[Home<br/>HomeComponent]
    RouterOutlet --> MotorcycleList[MotorcycleList<br/>MotorcycleListComponent]
    RouterOutlet --> RentalDashboard[RentalDashboard<br/>RentalDashboardComponent]
    
    %% Component Interactions
    Home --> MotorcycleService[MotorcycleRentalService]
    MotorcycleList --> RentalBooking[RentalBooking<br/>RentalBookingComponent]
    MotorcycleList --> RentalConfirmation[RentalConfirmation<br/>RentalConfirmationComponent]
    RentalDashboard --> MotorcycleService
    
    %% Service Dependencies
    MotorcycleService --> BackendAPI[Backend API<br/>localhost:8080]
    
    %% Data Storage
    MotorcycleService --> LocalStorage[LocalStorage<br/>Browser Storage]
    
    %% Environment Configuration
    MotorcycleService --> Environment[Environment<br/>Config]
    
    %% Styling
    classDef entry fill:#ffeb3b,stroke:#f57f17,stroke-width:2px
    classDef mainApp fill:#2196f3,stroke:#0d47a1,stroke-width:2px
    classDef routed fill:#4caf50,stroke:#1b5e20,stroke-width:2px
    classDef subComponent fill:#ff9800,stroke:#e65100,stroke-width:2px
    classDef service fill:#9c27b0,stroke:#4a148c,stroke-width:2px
    classDef external fill:#f44336,stroke:#b71c1c,stroke-width:2px
    classDef storage fill:#607d8b,stroke:#263238,stroke-width:2px
    
    class Index entry
    class App,Header,RouterOutlet,Footer mainApp
    class Home,MotorcycleList,RentalDashboard routed
    class RentalBooking,RentalConfirmation subComponent
    class MotorcycleService service
    class BackendAPI,Environment external
    class LocalStorage storage
```

## Component Details

### Main Components

1. **AppComponent** - Root component that serves as the application shell
2. **HomeComponent** - Landing page displaying featured motorcycles
3. **MotorcycleListComponent** - Main browsing interface for motorcycles with filtering and booking
4. **RentalDashboardComponent** - Administrative interface for managing rentals

### Sub-components

1. **RentalBookingComponent** - Modal form for creating motorcycle rental bookings
2. **RentalConfirmationComponent** - Confirmation screen after successful booking

### Services

1. **MotorcycleRentalService** - Central service handling all API communications with the backend

### Key Features

- **Standalone Components**: All components are standalone (no NgModules)
- **Reactive Forms**: Uses Angular reactive forms for data handling
- **Service Injection**: Uses `inject()` function for dependency injection
- **TypeScript Interfaces**: Strong typing with comprehensive model definitions
- **API Integration**: RESTful communication with backend services

### Data Flow

1. Components request data through `MotorcycleRentalService`
2. Service makes HTTP calls to backend API
3. Data flows back through observables to components
4. Components update UI based on received data
5. User interactions trigger new service calls or component state changes

### Routing Structure

- `/` - HomeComponent (landing page)
- `/motorcycles` - MotorcycleListComponent (browse motorcycles)
- `/dashboard` - RentalDashboardComponent (manage rentals)
- `/contact` - HomeComponent (placeholder)

## Technology Stack

- **Framework**: Angular (Standalone Components)
- **Language**: TypeScript
- **Styling**: CSS
- **HTTP Client**: Angular HttpClient
- **State Management**: Component-level state with signals
- **Routing**: Angular Router
