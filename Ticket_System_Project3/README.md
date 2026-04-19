# Ticket Queue System

The Ticket Queue System is a backend application built with Spring Boot to manage customer flow using a structured queue process. It allows users to create tickets, assign them to queues and service types, and track their lifecycle through statuses such as waiting, called, and completed.

The project is developed in phases, starting with system design and database modeling, followed by implementing repository, service, and controller layers. It includes core features like FIFO queue handling, ticket number generation, and multi-counter support, with optional enhancements such as concurrency control and real-time updates.

The final deliverable is a fully functional REST API with proper testing and documentation, designed to be scalable and suitable for real-world queue management scenarios.

---

## Technologies Used

### Backend
- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security (JWT Authentication)
- Hibernate
- Jackson (JSON serialization/deserialization)

### Database
- H2 (development)
- PostgreSQL


---

## Major hurdles

**Concurrency and Race Conditions**

One of the main challenges was preventing multiple counters from calling the same ticket at the same time. Without proper locking, duplicate processing could occur. This was partially addressed using transactional methods, but a fully robust solution (such as pessimistic locking or distributed locking) remains an area for improvement.

**Error Handling and Validation**

Basic error handling was implemented, but comprehensive validation (e.g., invalid states, missing dependencies, edge cases) is still limited. A global exception handling strategy could improve consistency.

---

## API Endpoints

| Request Type | URL | Functionality |Access | Role |
|------|---------|------------|------|------|
|POST|/auth/users/register|Create new user with assign user role|Public|ALL
|Post|/auth/users/login|Sign in of the user|Public|ALL
|PUT|/auth/users/set-image|Assign user profile image|Public|ALL
|GET|/auth/users/reset-password|User reset password|Public|ALL
|Post|/auth/users/reset-password-activator|User reset password validation|Public|ALL
|PUT|/auth/users/change-password|User **change** password|Public|ALL
|DELETE|/api/admin/users/softDelete|User soft delete|Privete|ADMIN
|GET|/api/admin/tickets/getAll|Get all tickets|Privete|ADMIN
|GET|/api/admin/tickets/{id}|Get ticket by id|Privete|ADMIN
|DELETE|/api/admin/tickets/{id}|Ticket soft delete|Privete|ADMIN
|POST|/api/tickets/create|Create a ticket|Privete|USER
|GET|/api/tickets/next|Get and assigne to counter the next ticket|Privete|USER
|PUT|/api/tickets/{id}/close|Close the ticket and remove from the queue and counter|Privete|USER
|PUT|/api/tickets/{id}/reopen|Ticket reopen|Privete|USER
|PUT|/api/tickets/update|Update ticket with status and service type|Privete|USER

---

### Link of the project
  
**ERD diagram**
```bash
https://drawsql.app/teams/sameh-4/diagrams/ticket-system
```
**planning documentation**
```bash
https://github.com/sameh0el0sayed/JavaBibfBootCamp/blob/master/Ticket_System_Project3/Planning%20documentation.docx
```

### Dependencies
- Spring Boot Starter Web (3.2.0)
- Spring Boot DevTools (3.2.0)
- Spring Boot Starter Data JPA (3.2.0)
- PostgreSQL Driver (42.7.3)
- JJWT API (0.11.5)
- JJWT Impl (0.11.5)
- JJWT Jackson (0.11.5)
- Lombok (1.18.30)
- Spring Boot Starter Security (3.2.0)
- Cloudinary HTTP5 (2.0.0)
- Cloudinary Taglib (2.0.0)
- Spring Boot Starter Mail (3.2.0)
- Spring Boot Starter Thymeleaf (3.2.0)
- Apache Commons Lang3 (3.14.0)
