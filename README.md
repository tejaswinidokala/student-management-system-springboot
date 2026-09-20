Student Management System

A full-stack Student Management System built using Java, Spring Boot, Spring MVC, Spring Data JPA, JSP, and MySQL.
The application provides separate interfaces for teachers and students and supports student record management, attendance tracking, academic performance management, authentication, validation, and role-based access.

Features

Authentication and Roles
Session-based login and logout
Separate Teacher and Student roles
Student self-registration
Role-based dashboard redirection
Passwords stored using SHA-256 hashing
Default demo accounts created automatically when the user table is empty
Authentication checks using interceptors

Teacher Module

Teachers can:
Register new students
Search and manage student records
Update student information
Delete student records
Mark attendance for individual students
Mark attendance for the whole class
View, edit, and delete attendance records
View monthly attendance reports
Add and update semester-wise SGPA
View and manage academic records
Calculate CGPA automatically

Student Module

Students can:
Search student records
Check attendance
View monthly attendance
View semester-wise SGPA
View overall CGPA
Access attendance and academic information through a separate student dashboard

User Interface
JSP-based server-side views
Separate teacher and student dashboards
Student registration and attendance forms
Search and filtering pages
Dark mode and light mode
Custom CSS styling
JavaScript-based theme handling

Tech Stack

Technology Usage
Java
Core application development
Spring Boot
Application framework
Spring MVC
Web request handling
Spring Data JPA
Database access and persistence
Hibernate
ORM implementation
MySQL
Relational database
JSP / JSTL
Server-side UI rendering
HTML / CSS
Frontend structure and styling
JavaScript
UI behavior and theme switching
Maven
Dependency and build management
Jakarta Validation
Request and form validation
SLF4J
Application logging

Project Architecture

The application follows a layered architecture:

User Interface
      |
Controller
      |
Service
      |
Repository
      |
MySQL Database

This structure keeps request handling, business logic, persistence, and data models organized separately.

Project Structure

src/
├── main/
│   ├── java/com/demo/
│   │   ├── config/          # Authentication, web config, data seeding
│   │   ├── controller/      # MVC and REST controllers
│   │   ├── entity/          # JPA entities and enums
│   │   ├── exception/       # Global exception handling
│   │   ├── payload/         # DTO and response classes
│   │   ├── repository/      # Spring Data repositories
│   │   ├── service/         # Business logic
│   │   ├── util/            # Utility classes
│   │   └── DemoApplication.java
│   │
│   ├── resources/
│   │   ├── static/css/
│   │   ├── static/js/
│   │   └── application.properties
│   │
│   └── webapp/WEB-INF/views/   # JSP pages
│
└── test/                        # Application tests

Main Modules

Authentication

AuthController handles login, logout, student sign-up, session creation, and role-based redirection.

After successful login:

Teacher → Teacher Dashboard
Student → Student Dashboard

Student Management

The student module provides CRUD operations and search functionality.

Main flow:

StudentController
       |
StudentService
       |
StudentRepository
       |
MySQL

Functions include:

Create student records
View student information
Update student details
Delete student records
Search and filter students by name, course, email, and mobile number
Attendance Management
The attendance module allows teachers to manage attendance records.

Functions include:
Mark individual attendance
Mark whole-class attendance
View attendance records
Edit attendance
Delete attendance
Generate monthly attendance summaries
Allow students to view attendance information
Attendance status is stored using an enum-based model.
Academic Management
The academic module manages student performance records.

Functions include:

Add semester-wise SGPA
Update SGPA
View academic records
Calculate CGPA
Allow students to view SGPA and CGPA
Database Entities
Student

Stores student information such as:

Name

Email

Mobile number

Course

User

Stores authentication details:

Username

Hashed password

Role

Available roles:

TEACHER
STUDENT

Attendance

Stores:

Student reference

Attendance date

Attendance status

AcademicRecord

Stores:

Student reference

Semester

SGPA

These records are used to calculate CGPA.

Getting Started

Prerequisites

Make sure the following are installed:

Java

MySQL

Maven or the included Maven Wrapper

Git

1. Clone the Repository

git clone https://github.com/tejaswinidokala/student-management-system-springboot.git
cd student-management-system-springboot

2. Create the MySQL Database

CREATE DATABASE demo_API_D_db;

3. Configure the Database

Open:

src/main/resources/application.properties

Update the MySQL credentials:

spring.datasource.url=jdbc:mysql://localhost:3306/demo_API_D_db
spring.datasource.username=YOUR_MYSQL_USERNAME
spring.datasource.password=YOUR_MYSQL_PASSWORD
spring.jpa.hibernate.ddl-auto=update

Hibernate will automatically create or update the required tables when the application starts.

4. Run the Application

On Windows:

mvnw.cmd spring-boot:run

On macOS/Linux:

./mvnw spring-boot:run

Then open:

http://localhost:8080

Demo Login

When the application starts with an empty user table, two demo accounts are created automatically.

Role

Username

Password

Teacher

teacher

teacher123

Student

student

student123

Students can also create a new account from the sign-up page.

These credentials are intended only for local testing and demonstration.

Key Routes

Route

Purpose

/login

Login page

/signup

Student sign-up

/teacherDashboard

Teacher dashboard

/studentDashboard

Student dashboard

/attendance/mark

Mark attendance

/attendance/list

View attendance records

/attendance/search

Search attendance

The application also includes routes and REST endpoints for student CRUD operations, searching, filtering, attendance management, and academic records.

What This Project Demonstrates

This project demonstrates:

Full-stack Java web application development

Spring Boot and Spring MVC

Layered architecture

REST API development

CRUD operations

Spring Data JPA and Hibernate

MySQL integration

JSP-based server-side rendering

Session-based authentication

Role-based access

Form validation

DTO usage

Global exception handling

Search and filtering

Attendance tracking

Monthly attendance reporting

SGPA and CGPA management

Dark and light mode support

Future Improvements

Possible improvements include:

Replace custom authentication with Spring Security

Use BCrypt or Argon2 instead of SHA-256

Add stronger endpoint-level authorization

Automatically load logged-in student records

Add student ID / roll number

Add attendance percentage and low-attendance alerts

Add dashboard statistics and charts

Add pagination and sorting

Improve test coverage

Add Swagger/OpenAPI documentation

Add Docker support

Add cloud deployment configuration

Add PDF/Excel export support

Author

Tejaswini Dokala

GitHub:
https://github.com/tejaswinidokala

Repository:
https://github.com/tejaswinidokala/student-management-system-springboot

Note

This project was developed for educational and academic purposes and demonstrates practical implementation of student record management, attendance tracking, role-based access, and academic performance management using a Java Spring Boot stack.