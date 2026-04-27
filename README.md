#  Task Management System (Backend)

##  Overview

The **Task Management System** is a backend application built using **Spring Boot** that enables users to manage tasks efficiently with secure authentication and role-based access control.

It follows a **clean layered architecture** and provides scalable REST APIs suitable for real-world applications.

---

## Objectives

- Provide complete task management (CRUD)
- Implement secure authentication using JWT
- Support role-based authorization (USER / ADMIN)
- Ensure scalability and clean architecture
- Deliver APIs ready for frontend integration

---

## Tech Stack

- **Backend:** Java, Spring Boot  
- **Database:** MySQL  
- **ORM:** Spring Data JPA (Hibernate)  
- **Security:** Spring Security + JWT  
- **Build Tool:** Maven  
- **Testing:** Postman  

---

## Authentication & Security

- JWT-based authentication  
- Role-based access control (USER, ADMIN)  
- Secure password hashing using BCrypt  
- Protected API endpoints  

---

## Features

### User Features

- User registration & login (JWT)
- View profile
- Update profile (name & email)
- Change password (secure)
- Manage own tasks:
  - Create task
  - Update task
  - Delete task
  - View tasks
- Task filtering by status
- Pagination (max 10 tasks per page)
- Sorting (due date, created date, etc.)
- Task statistics:
  - Total tasks
  - TODO tasks
  - IN_PROGRESS tasks
  - DONE tasks

---

### Admin Features

- View all users
- Delete users
- View all tasks (all users)
- Update any task
- Delete any task
- Filtering + pagination + sorting on all tasks
- System statistics:
  - Total users
  - Total tasks
  - TODO tasks
  - IN_PROGRESS tasks
  - DONE tasks

---

## Task Status

- `TODO`
- `IN_PROGRESS`
- `DONE`

---

## Project Structure

```

src/main/java/com/taskmanagementsystem/backend
├── controller/      # REST Controllers
├── service/         # Business logic
├── repository/      # Database layer (JPA)
├── entity/          # Entity classes
├── dto/             # Data Transfer Objects
├── security/        # JWT & security filters
├── config/          # Spring configurations
├── exception/       # Global exception handling
└── util/            # Utility classes

```

---

## API Endpoints

### Auth APIs

- `POST /api/auth/signup` → Register user  
- `POST /api/auth/login` → Login & get JWT  

---

### User APIs

- `GET /api/tasks/profile` → View profile  
- `PUT /api/tasks/profile` → Update profile  
- `PUT /api/tasks/change-password` → Change password  

---

### Task APIs (User)

- `POST /api/tasks` → Create task  
- `GET /api/tasks` → Get tasks (pagination + filter + sort)  
- `PUT /api/tasks/{id}` → Update task  
- `DELETE /api/tasks/{id}` → Delete task  

**Example:**
```

GET /api/tasks?page=0&status=TODO&sort=dueDate,desc

```

---

### User Stats

- `GET /api/tasks/stats` → Task statistics for logged-in user  

---

###  Admin APIs

- `GET /api/admin/users` → Get all users  
- `DELETE /api/admin/users/{id}` → Delete user  

- `GET /api/admin/tasks` → Get all tasks (pagination + filter + sort)  
- `PUT /api/admin/tasks/{id}` → Update any task  
- `DELETE /api/admin/tasks/{id}` → Delete any task  

**Example:**
```

GET /api/admin/tasks?page=0&size=10&status=DONE&sort=createdAt,asc

````

---

### Admin Stats

- `GET /api/admin/stats` → System-wide statistics  

---

## Setup Instructions

### 1️⃣ Clone Repository

```bash
git clone https://github.com/M-Sufyan003/task-management-system-backend.git
cd task-manager-backend
````

---

### 2️⃣ Configure Database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/task_management_system
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

### 3️⃣ Run Application

```bash
mvn spring-boot:run
```

---

### 4️⃣ Test APIs

Use **Postman**

Add header:

```
Authorization: Bearer <JWT_TOKEN>
```

---

## Key Highlights

* Clean layered architecture
* Secure JWT authentication
* Role-based authorization
* Pagination + filtering + sorting
* DTO-based API design (no entity exposure)
* Global exception handling

---

## Future Enhancements

* Swagger API documentation
* Refresh tokens (JWT)
* Email notifications
* File attachments for tasks
* Advanced search & filtering
* Frontend integration (React)

---

## Author

**Muhammad Sufyan**

* BS Information Technology
* Backend Developer (Java | Spring Boot)

---

## Notes

This project was developed as part of an internship and demonstrates:

* Backend system design
* REST API development
* Security implementation
* Real-world scalable architecture.