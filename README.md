# SkillSwap – Backend (Spring Boot)

SkillSwap is a mentorship platform backend where students can request skills from mentors and mentors can manage those requests.  
This repository contains the backend implementation built using Spring Boot with JWT-based authentication and role-based access control.

---

## 🚀 Tech Stack
- Java 17
- Spring Boot
- Spring Security (JWT)
- Spring Data JPA
- MySQL
- Maven

---

## 🔐 Authentication & Roles
- JWT-based authentication
- Role-based authorization:
  - STUDENT
  - MENTOR

---

## ✨ Features

### 👩‍🎓 Student
- Register & Login
- View available skills
- Request mentorship for a skill
- View accepted courses

### 👨‍🏫 Mentor
- Register & Login
- Add skills
- View mentorship requests
- Accept / Reject student requests
- View accepted students (dashboard)

---

## 📦 API Endpoints

### 🔑 Authentication
- POST `/api/register`
- POST `/api/login`

### 📘 Skills
- GET `/api/skills`
- POST `/api/mentor/add-skill` (MENTOR)

### 📩 Mentorship Requests
- POST `/api/student/request/{skillId}` (STUDENT)
- GET `/api/mentor/requests` (MENTOR)
- POST `/api/mentor/request/{id}/accept`
- POST `/api/mentor/request/{id}/reject`

### 📊 Dashboards
- GET `/api/mentor/dashboard`
- GET `/api/student/my-courses`

---

## 🗂 Project Structure
# SkillSwap – Backend (Spring Boot)

SkillSwap is a mentorship platform backend where students can request skills from mentors and mentors can manage those requests.  
This repository contains the backend implementation built using Spring Boot with JWT-based authentication and role-based access control.

---

## 🚀 Tech Stack
- Java 17
- Spring Boot
- Spring Security (JWT)
- Spring Data JPA
- MySQL
- Maven

---

## 🔐 Authentication & Roles
- JWT-based authentication
- Role-based authorization:
  - STUDENT
  - MENTOR

---

## ✨ Features

### 👩‍🎓 Student
- Register & Login
- View available skills
- Request mentorship for a skill
- View accepted courses

### 👨‍🏫 Mentor
- Register & Login
- Add skills
- View mentorship requests
- Accept / Reject student requests
- View accepted students (dashboard)

---

## 📦 API Endpoints

### 🔑 Authentication
- POST `/api/register`
- POST `/api/login`

### 📘 Skills
- GET `/api/skills`
- POST `/api/mentor/add-skill` (MENTOR)

### 📩 Mentorship Requests
- POST `/api/student/request/{skillId}` (STUDENT)
- GET `/api/mentor/requests` (MENTOR)
- POST `/api/mentor/request/{id}/accept`
- POST `/api/mentor/request/{id}/reject`

### 📊 Dashboards
- GET `/api/mentor/dashboard`
- GET `/api/student/my-courses`

---

## 🗂 Project Structure
src/main/java/com/skillswap/skillswap
├── config → Security & password configuration
├── controller → REST controllers
├── dto → Request & response DTOs
├── filter → JWT authentication filter
├── model → Entity classes
├── repository → JPA repositories
├── service → Business logic
└── exception → Global exception handling

## ⚙️ How to Run Locally

### 1️⃣ Clone Repository
```bash
git clone https://github.com/ABReeshma/skillswap.git
cd skillswap

### 2 Configure Database

Update application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/skillswap
spring.datasource.username=your_username
spring.datasource.password=your_password

### 3 Run Application
mvn spring-boot:run


Backend will run on:

http://localhost:8080
