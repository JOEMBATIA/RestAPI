# RestAPI
This project implements RestAPI with codebase mainly focused with:

Java as the main language ,
Spring Boot as the framework which reduces tight coupling by implementing dependency injection and IoC,
PostgreSQL as the database for the QA and PROD profiles
and H2 in Mem database for the Unit TEST scenarios for the various Layers including repository layer, service layer and the Controller Layer.

# Code Structure

rest-api/
├── Logs/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── joe/
│   │   │           └── rest-api/
│   │   │               ├── config/
│   │   │               │   ├── FeaturesConfig.java
│   │   │               ├── controller/
│   │   │               │   └── DepartmentController.java
│   │   │               ├── entity/
│   │   │               │   └── Department.java
|   |   |               ├── error/
|   |   |               |   └── DepartmentExistsExcepetion.java
|   |   |               |   └── DepartmentNonExistentExcepetion.java
|   |   |               |   └── DepartmentNotFoundExcepetion.java
|   |   |               |   └── DepartmentPropertiesExcepetion.java
│   │   │               ├── repository/
│   │   │               │   └── DepartmentRepository.java
│   │   │               ├── service/
│   │   │               │   ├── DepartmentService.java
│   │   │               │   └── DepartmentServiceImpl.java
│   │   │               └── RestApiApplication.java
│   │   └── resources/
│   │       ├── static/
│   │       ├── templates/
│   │       ├── application.yml
│   │       └── logback-spring.xml (for logging configuration)
│   ├── test/
│   │   └── java/
│   │       └── com/
│   │           └── mycompany/
│   │               └── myapp/
│   │                   ├── controller/
│   │                   │   └── DepartmentControllerTest.java
│   │                   ├── repository/
│   │                   │   └── DepartmentRepositoryTest.java
│   │                   ├── service/
│   │                   │   └── DepartmentServiceTest.java
│   │                   └── RestApiApplicationTest.java
├── target/ (compiled output)
├── .gitignore (specifies files to be ignored by Git)
├── README.md
├── mvnw (Maven wrapper script for Unix-like systems)
├── mvnw.cmd (Maven wrapper script for Windows)
└── pom.xml (Maven project configuration)


