# Rideshare Backend (Spring Boot + MongoDB + JWT)

A simple backend API for a rideshare application built with **Spring Boot**, **MongoDB**, and **JWT-based authentication**.  
Supports user registration/login with roles (user/driver) and basic ride management.

---

## Tech Stack

- Java 21
- Spring Boot 3
  - Spring Web
  - Spring Data MongoDB
  - Spring Security
- MongoDB (local or Atlas)
- JSON Web Tokens (JWT) with `jjwt`
- Maven

---

## Features

### Authentication

- **Register**
  - `POST /api/auth/register`
  - Request body:
    ```
    {
      "username": "raaj",
      "password": "1234",
      "role": "ROLE_USER"
    }
    ```
  - Stores user in MongoDB with **BCrypt-hashed** password.

- **Login**
  - `POST /api/auth/login`
  - Request body:
    ```
    {
      "username": "john",
      "password": "1234"
    }
    ```
  - Validates credentials and returns a **JWT token**:
    ```
    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    ```

### Users & Roles

- User fields: `id`, `username`, `password`, `role`
- Roles: `ROLE_USER`, `ROLE_DRIVER`
- Passwords stored as **BCrypt hashes**, never in plain text.

### Rides (basic)

Depending on your implementation, typical endpoints:

- `POST /api/rides` – create a ride
- `GET /api/rides/user/{userId}` – get rides by user
- `GET /api/rides/status/{status}` – get rides by status


---

## Project Structure

- `controller/`
  - `AuthController` – `/api/auth/register`, `/api/auth/login`
  - `RideController` – ride-related endpoints
- `service/`
  - `AuthService` – register, login, password check, JWT generation
  - `RideService` – ride business logic
- `repository/`
  - `UserRepository` – MongoDB CRUD for users
  - `RideRepository` – MongoDB CRUD for rides
- `model/`
  - `User` – user entity
  - `Ride` – ride entity
- `dto/`
  - `LoginRequest`, `RegisterRequest`, (optional `AuthResponse`)
- `config/`
  - `SecurityConfig` – Spring Security + `PasswordEncoder` bean
  - `JwtUtil` – JWT generation

---


---

## Future Improvements

- Protect ride endpoints with JWT (only logged-in users).
- Role-based access: users vs drivers.
- Validation on DTOs (`@Valid`, `@NotBlank`, etc.).
- Global exception handling (`@ControllerAdvice`).
- Docker for MongoDB and the app.

---

## License

This project is for learning and portfolio purposes. Feel free to fork and modify.


In `src/main/resources/application.properties`:

