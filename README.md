# Task Management System (Backend)
## Overview

The Task Management System is a backend application designed to manage tasks efficiently for individuals and teams. It provides RESTful APIs to create, update, assign, and track tasks with role-based access control.

This system is built using Java (Spring Boot) and follows a clean, modular architecture suitable for real-world applications.

## Objectives
- Manage tasks efficiently (Create, Read, Update, Delete)
- Support multiple users with role-based permissions
- Ensure secure and scalable backend services
- Provide a structured API for frontend integration

---

## Tech Stack
- Backend: Java, Spring Boot
- Database: MySQL
- ORM: Spring Data JPA (Hibernate)
- Build Tool: Maven
- API Testing: Postman

---

## System Features (Functional Requirements)

### User Management
- User registration and login
- Role-based access control (Admin/User)
- Secure user data handling

### Task Management
- Create new tasks
- Update task details
- Delete tasks
- View all tasks

### Role-Based Access Control
- Admin
- Manage all tasks
- Manage users
- User
- Manage only their own tasks

### Task Tracking
- Track task status (Pending, In Progress, Completed)
- Assign tasks to users
- View task history

## Non-Functional Requirements
- Performance: Efficient API response time
- Scalability: Modular architecture for future expansion
- Security: Role-based authorization
- Usability: Clean API design for frontend integration
- Maintainability: Well-structured and readable code

### Project Structure
src/
 ├── controller/     # Handles API requests
 ├── service/        # Business logic
 ├── repository/     # Database interaction
 ├── model/          # Entity classes
 └── config/         # Configuration files

---

### API Endpoints (Sample)
#### User APIs
- POST /api/users/register → Register user
- POST /api/users/login → Login user

#### Task APIs
- POST /api/tasks → Create task
- GET /api/tasks → Get all tasks
- PUT /api/tasks/{id} → Update task
- DELETE /api/tasks/{id} → Delete task

---

### Setup Instructions
1- Clone Repository
git clone https://github.com/M-Sufyan003/task-management-system-backend.git

2- Navigate to Project
cd task-manager-backend

3- Configure Database
Update application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/task_management_system
spring.datasource.username=root
spring.datasource.password=

4- Run Application
mvn spring-boot:run

### Testing

Use Postman to test API endpoints.

---

## Future Enhancements
- JWT Authentication
- Email Notifications
- Task deadlines & reminders
- File attachments
- Frontend integration (React)

---

## Author

Muhammad Sufyan
- BS Information Technology
- Backend Developer (Java)

---

## Notes

This project is developed as part of an internship task and demonstrates backend development skills, REST API design, and role-based system implementation.