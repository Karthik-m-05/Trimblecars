# Trimble Cars - Car Lease Management System

This is a Java Spring Boot backend for managing car leases, acting as an interface between car owners and leasers.

## Personas
- **Car Owner**: Register/enroll cars, view status, and lease history.
- **End Customer**: Register, view cars, start/end leases (max 2 active), view own history.
- **Admin**: Full access to all operations for all cars and customers.

## Tech Stack
- Java 17, Spring Boot, Gradle
- H2 in-memory database
- JPA, Lombok
- Logging, Exception Handling
- Unit and Functional API Tests

## Getting Started
1. `./gradlew bootRun` to start the server.
2. Access H2 console at `/h2-console` (default credentials in `application.properties`).
3. Use Postman or similar tool to test APIs.

## Features
- Car registration and status tracking
- Lease lifecycle management
- Admin oversight and control
- Ready for 3rd party authentication and car model integration

## Testing
- Run `./gradlew test` for unit and functional tests.

---

For more details, see code comments and API documentation.
