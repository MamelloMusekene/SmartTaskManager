# Smart Task Manager

A modern task management web application built with **Java, Spring Boot, MySQL, Thymeleaf, Bootstrap, and JavaScript**.

Smart Task Manager helps users organize, track, and manage their tasks through a centralized dashboard with task priorities, due dates, status tracking, and multiple task views.

## Features

* User registration and login
* Secure authentication
* Task creation, editing, and deletion
* Task priorities
* Task due dates
* Task status management
* Dashboard statistics
* Task board
* Drag-and-drop task organization
* Calendar task view
* Responsive web interface
* MySQL database integration

## Technology Stack

### Backend

* Java
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate

### Frontend

* HTML5
* CSS3
* JavaScript
* Thymeleaf
* Bootstrap 5

### Database

* MySQL

### Development Tools

* IntelliJ IDEA
* Maven
* Git
* GitHub

## Project Structure

```text
SmartTaskManager/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── smarttaskmanager/
│   │   └── resources/
│   │       ├── static/
│   │       ├── templates/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

## Getting Started

### Prerequisites

Before running the application, make sure you have:

* Java JDK 21 or later
* IntelliJ IDEA
* MySQL
* Maven

### 1. Clone the repository

```bash
git clone https://github.com/MamelloMusekene/SmartTaskManager.git
```

### 2. Open the project

Open the cloned project in IntelliJ IDEA.

Allow IntelliJ to download and configure the Maven dependencies.

### 3. Configure MySQL

Create a MySQL database for the application.

Update the database configuration in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/smart_task_manager
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

Use your own MySQL username and password.

### 4. Run the application

Open the main Spring Boot application class in IntelliJ and click **Run**.

Alternatively, run:

```bash
mvn spring-boot:run
```

### 5. Open the application

Once the application starts, open:

```text
http://localhost:8080
```

## Application Overview

### Dashboard

The dashboard provides an overview of tasks and their current status, allowing users to quickly understand their workload.

### Task Management

Users can create and manage tasks with information such as:

* Task title
* Description
* Priority
* Due date
* Status

### Task Board

The task board provides a visual way to organize tasks according to their current status.

### Calendar

The calendar view provides a date-based overview of scheduled tasks and deadlines.

## Purpose

Smart Task Manager was developed as a portfolio project to demonstrate practical skills in:

* Java development
* Spring Boot
* Database-driven web applications
* RESTful/backend architecture
* Authentication and security
* CRUD operations
* Frontend development
* Agile task management concepts
* Git and GitHub

## Future Improvements

Potential future enhancements include:

* Email notifications
* Task reminders
* User profile management
* Team collaboration
* Task sharing
* Advanced filtering
* Search functionality
* Dark mode
* Deployment to a cloud platform

## Author

**Mamello Musekene**

Diploma in System Development

GitHub: [MamelloMusekene](https://github.com/MamelloMusekene)
