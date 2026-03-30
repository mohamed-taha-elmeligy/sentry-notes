# Sentry-Notes

A RESTful API for managing personal notes, built with **Java 17** and **Spring Boot 3**, applying **Clean Architecture** and **Domain-Driven Design (DDD)** principles.

---

## Architecture Overview

The core architectural decision in this project is the **Application Service layer** — an orchestration layer that sits between the Web layer and the Domain layer.

```
Controller → AppService → DomainService → Repository
```

| Layer | Responsibility |
|---|---|
| **Controller** | Handles HTTP requests only — no business logic |
| **AppService** | Orchestrates: maps DTOs, collects data from multiple sources, delegates to Domain |
| **DomainService** | Pure business logic — works with Entities only, knows nothing about HTTP or DTOs |
| **Repository** | Data access layer |

**Why this matters:** Any change in the API contract (request/response shape) won't affect the Domain layer or internal services like Schedulers.

---

## Tech Stack

- **Java 17**
- **Spring Boot 3**
- **Spring Security** — JWT-based stateless authentication
- **Spring Data JPA** — ORM with Hibernate
- **MapStruct** — DTO ↔ Entity mapping
- **Lombok** — boilerplate reduction
- **Flyway** — database migrations
- **H2** — in-memory database for development
- **PostgreSQL** — production database
- **Redis + Bucket4j** — rate limiting
- **Spring AOP** — logging, performance monitoring, security aspects
- **SpringDoc OpenAPI** — auto-generated API documentation (Swagger UI)
- **Testcontainers** — integration testing

---

## API Endpoints

### Public (no authentication required)
| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/v1/public/create-account` | Register a new user |
| `GET` | `/api/v1/public/{page}/{size}` | Get public notes (paginated) |
| `GET` | `/api/v1/public/check` | Health check |

### User (authentication required)
| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/v1/user/username` | Get current user profile |
| `GET` | `/api/v1/user/{page}/{size}` | Get all users (paginated) |
| `PUT` | `/api/v1/user/update` | Update current user |
| `DELETE` | `/api/v1/user/delete` | Delete current user |

### Notes (authentication required)
| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/v1/note/create` | Create a new note |
| `PUT` | `/api/v1/note/update` | Update a note |
| `DELETE` | `/api/v1/note/delete` | Delete a note |
| `GET` | `/api/v1/note/{page}/{size}` | Get current user's notes |

### API Documentation
Available at `/swagger-ui/index.html` after running the project.

---

## Getting Started

### Prerequisites
- Java 17+
- Maven 3.8+

### Run Locally

```bash
git clone https://github.com/mohamed-taha-elmeligy/sentry-notes.git
cd sentry-notes/sentry-notes
```

Set the required environment variable:

```bash
export JWT_SECRET=your_secret_key_here
```

Run the application:

```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8084`

### Environment Variables

| Variable | Description | Required |
|---|---|---|
| `JWT_SECRET` | Secret key used to sign JWT tokens | Yes |
| `DB_PASSWORD` | Database password (defaults to `password` for H2 dev) | No |

---

## Project Structure

```
src/main/java/com/sentry/notes/
├── annotation/         # Custom annotations (@RequireRole, @RequireOwnership)
├── aop/                # Cross-cutting concerns (logging, performance, security)
├── appservice/         # Application Service layer (orchestration)
├── config/             # Spring configuration (AOP, Swagger, Pageable)
├── controllers/        # REST controllers (HTTP layer only)
├── dtos/               # Request and Response DTOs
├── entities/           # JPA Domain Entities
├── enums/              # Role enum
├── exceptions/         # Custom exceptions + GlobalExceptionHandler
├── mapper/             # MapStruct mappers (DTO ↔ Entity)
├── repositories/       # Spring Data JPA repositories
├── security/           # JWT filter, SecurityConfig, UserDetails
└── services/           # Domain Services (pure business logic)
```

---

## Key Design Decisions

**Application Service Layer**
The `AppService` classes act as the bridge between HTTP and Domain. They handle DTO conversion, collect data from multiple HTTP sources (path variables, security context, request body), and pass clean Entities to the Domain Service. This means the Domain is fully isolated from API changes.

**AOP for Cross-cutting Concerns**
Logging, performance monitoring, and security checks are handled via Spring AOP aspects — keeping the business logic clean.

**Custom Security Annotations**
`@RequireRole` and `@RequireOwnership` annotations provide declarative security at the method level.

---

## License

MIT License
