# 🎬 Movie Ticketing System

A full-stack web application for managing movie showtimes and ticket reservations.
Developed as part of the **DAW (Desarrollo de Aplicaciones Web)** transversal project.

---

## 📌 Project Overview

This application allows users to:

- Browse available movies and showtimes
- View detailed information about each session
- Reserve tickets (with capacity control)
- Manage their reservations

It also includes an **admin panel** for managing movies, showtimes, and users.

---

## 🧱 Tech Stack

### Backend

- Java 21
- Spring Boot
- Maven
- Lombok
- Swagger (OpenAPI)

### Frontend

- Angular 21
- Bootstrap

### Database

- MySQL 8

---

## 👥 User Roles

### 👑 Admin (`ROLE_ADMIN`)

- Manage movies and showtimes (CRUD)
- Manage users
- Monitor reservations

### 👤 Client (`ROLE_CLIENT`)

- View available showtimes
- Reserve tickets (max 10 per reservation)
- View and cancel their reservations

### 👀 Guest

- Browse movies and showtimes
- View details
- Cannot make reservations

---

## ⚙️ Core Features

- 🎬 Movie and showtime listing
- 🔍 Filtering by status (active, cancelled, finished)
- 🎟️ Ticket reservation system
- 🪑 Capacity control (no overbooking)
- 🔐 Authentication & authorization (JWT planned)
- 📊 Admin dashboard (planned)

---

## 🧠 Business Rules

- A user can reserve **maximum 10 tickets per showtime**
- Reservations cannot exceed the showtime capacity
- Showtimes have states:
  - `ACTIVE`
  - `CANCELLED`
  - `FINISHED`

---

## 🗄️ Database Design (Main Entities)

- `users`
- `movies`
- `genres`
- `showtimes`
- `reservations`

---

## 📡 API Endpoints (Example)

### Movies & Showtimes

- `GET /movies`
- `GET /showtimes/active`
- `GET /showtimes/{id}`

### Reservations

- `POST /reservations/{showtimeId}`
- `GET /reservations/my`
- `DELETE /reservations/{id}`

### Admin

- `POST /admin/showtimes`
- `PUT /admin/showtimes/{id}`
- `DELETE /admin/showtimes/{id}`

---

## 🚀 Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/your-username/movie-ticketing-system.git
```

---

### 2. Backend setup

```bash
cd backend
./mvnw spring-boot:run
```

Make sure you configure your `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/movie_db
spring.datasource.username=your_user
spring.datasource.password=your_password
```

---

### 3. Frontend setup

```bash
cd frontend
npm install
ng serve
```

App will run at:

```
http://localhost:4200
```

---

## 📄 API Documentation

Swagger UI available at:

```
http://localhost:8080/swagger-ui.html
```

---

## 👥 Team Workflow

- Repository shared via GitHub
- Work with feature branches:
  - `feature/backend-*`
  - `feature/frontend-*`

- Pull Requests required before merging to `main`

---

## 📦 Project Structure

```
movie-ticketing-system/
│
├── backend/        # Spring Boot API
├── frontend/       # Angular application
├── database/       # SQL scripts
└── docs/           # Documentation & diagrams
```

---

## 🎯 Future Improvements

- 🎯 Seat selection system
- 📧 Email notifications
- 📊 Admin analytics dashboard
- 🔐 Full JWT authentication
- 🐳 Docker deployment

---

## 📚 Academic Context

This project is part of the **DAW 2º Transversal Challenge**, integrating:

- DWES (Backend)
- DWEC (Frontend)
- DIW (UI/UX)
- DAW (Deployment)

---

## ✨ Authors

- Nelson Rosales
- Bryan Paico
- Adrian Ramses

---

## 📃 License

This project is for educational purposes.
