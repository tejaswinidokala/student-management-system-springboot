# Student Management System

A full-stack **Student Management System** built with **Java, Spring Boot, Spring MVC, Spring Data JPA, JSP, and MySQL**. The application supports role-based login for teachers and students, student record management, attendance tracking, validation, and REST-based student operations.

## Features

### Authentication and Roles
- Login and logout using session-based authentication
- Separate **Teacher** and **Student** roles
- Student self-sign-up
- Role-based dashboard redirection
- Passwords stored as SHA-256 hashes
- Default demo accounts are created automatically when the database is empty

### Student Management
- Create student records
- View student details
- Update existing student information
- Delete student records
- Search students by ID or email
- Filter students by course, name, email, and mobile number
- Validation and centralized exception handling

### Attendance Management
- Teachers can mark attendance for students
- View all attendance records
- Edit existing attendance entries
- Delete attendance records
- Students can search attendance using email or mobile number
- Attendance status is stored using an enum-based model

### Web Interface
- JSP-based views
- Separate teacher and student dashboards
- Forms for student registration and attendance management
- Custom CSS styling

## Tech Stack

| Technology | Usage |
|---|---|
| Java | Core application development |
| Spring Boot | Application framework |
| Spring MVC | Controllers and web request handling |
| Spring Data JPA | Database access and persistence |
| Hibernate | ORM implementation |
| MySQL | Primary relational database |
| JSP / JSTL | Server-side UI rendering |
| Maven | Dependency and build management |
| Jakarta Validation | Request/data validation |
| SLF4J | Application logging |

## Project Structure

```text
src/
├── main/
│   ├── java/com/demo/
│   │   ├── config/        # Interceptors, web config and data seeding
│   │   ├── controller/    # MVC and REST controllers
│   │   ├── entity/        # JPA entities
│   │   ├── exception/     # Global exception handling
│   │   ├── payload/       # DTO classes
│   │   ├── repository/    # Spring Data repositories
│   │   ├── service/       # Business logic
│   │   └── util/          # Utility classes
│   ├── resources/
│   │   ├── static/css/    # Application styling
│   │   └── application.properties
│   └── webapp/WEB-INF/views/ # JSP pages
└── test/                  # Application tests
```

## Main Modules

### Authentication
`AuthController` handles login, logout, and student sign-up. User sessions store the logged-in username and role, and users are redirected to the appropriate dashboard.

### Student REST API
`StudentController` provides REST endpoints for student CRUD operations, searching, and filtering.

### Attendance
`AttendanceController` provides teacher-side attendance management and student-side attendance search functionality.

### Persistence Layer
The application uses Spring Data JPA repositories for `Student`, `User`, and `Attendance` entities and stores application data in MySQL.

## Getting Started

### Prerequisites

Make sure the following are installed:

- Java 25
- MySQL
- Maven, or use the included Maven Wrapper
- Git

### 1. Clone the repository

```bash
git clone https://github.com/tejaswinidokala/student-management-system-springboot.git
cd student-management-system-springboot
```

### 2. Create the MySQL database

Create a database named:

```sql
CREATE DATABASE demo_API_D_db;
```

### 3. Configure the database

Open:

```text
src/main/resources/application.properties
```

Update the database credentials for your local MySQL installation:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/demo_API_D_db
spring.datasource.username=YOUR_MYSQL_USERNAME
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

The project uses:

```properties
spring.jpa.hibernate.ddl-auto=update
```

so Hibernate will create/update the required tables automatically when the application starts.

### 4. Run the application

On Windows:

```bash
mvnw.cmd spring-boot:run
```

On macOS/Linux:

```bash
./mvnw spring-boot:run
```

Then open:

```text
http://localhost:8080
```

## Demo Login

When the application runs with an empty user table, two demo accounts are created automatically:

| Role | Username | Password |
|---|---|---|
| Teacher | `teacher` | `teacher123` |
| Student | `student` | `student123` |

Students can also create a new account from the sign-up page.

> These credentials are intended only for local/demo use.

## Key Routes

| Route | Purpose |
|---|---|
| `/login` | Login page |
| `/signup` | Student registration |
| `/teacherDashboard` | Teacher dashboard |
| `/studentDashboard` | Student dashboard |
| `/attendance/mark` | Mark attendance |
| `/attendance/list` | View attendance records |
| `/attendance/search` | Search attendance |

The application also includes REST endpoints for student creation, lookup, filtering, updating, and deletion.

## Database Entities

### Student
Stores student information such as:
- Name
- Course
- Email
- Mobile number

### User
Stores application login information:
- Username
- Hashed password
- Role (`TEACHER` or `STUDENT`)

### Attendance
Stores:
- Student reference
- Attendance date
- Attendance status

## What This Project Demonstrates

- Building REST APIs with Spring Boot
- Layered architecture using Controller, Service, Repository, DTO, and Entity classes
- Spring Data JPA and Hibernate integration
- MySQL database connectivity
- Server-side JSP rendering
- Session-based authentication and role handling
- Form validation and global exception handling
- Entity relationships with JPA
- Search and filter operations using repository queries
- Attendance tracking and CRUD workflows

## Future Improvements

- Replace custom session authentication with Spring Security
- Use BCrypt/Argon2 instead of SHA-256 for password hashing
- Add pagination to student and attendance listings
- Add stronger role-based authorization for individual endpoints
- Improve test coverage
- Add API documentation with Swagger/OpenAPI
- Add Docker support for easier deployment
- Add deployment configuration for a cloud environment

## Author

**Tejaswini Dokala**

GitHub: [tejaswinidokala](https://github.com/tejaswinidokala)

---

If you find this project useful, feel free to explore the code and contribute improvements.
