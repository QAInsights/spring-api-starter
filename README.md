# Store API

A Spring Boot REST API for an e-commerce store application built with Spring Boot 3.4.1, Java 23, and MySQL. This project provides endpoints for managing users, products, categories, addresses, profiles, and messages.

> This is a starter project for [The Ultimate Spring Boot Course](https://codewithmosh.com/p/spring-boot-building-apis)

## Tech Stack

- **Framework**: Spring Boot 3.4.1
- **Language**: Java 23
- **Database**: MySQL
- **ORM**: Spring Data JPA
- **Database Migrations**: Flyway
- **Build Tool**: Maven
- **Productivity**: Lombok

## Project Structure

```
src/main/java/com/codewithmosh/store/
├── StoreApplication.java          # Main Spring Boot application
├── controllers/                   # REST Controllers
│   ├── HomeController.java
│   ├── UserController.java
│   ├── ProductController.java
│   └── MessageController.java
├── entities/                      # JPA Entities
│   ├── User.java
│   ├── Product.java
│   ├── Category.java
│   ├── Address.java
│   ├── Profile.java
│   └── Message.java
├── repositories/                  # Spring Data JPA Repositories
│   ├── UserRepository.java
│   ├── ProductRepository.java
│   ├── CategoryRepository.java
│   ├── AddressRepository.java
│   └── ProfileRepository.java
├── dtos/                          # Data Transfer Objects
│   ├── UserDto.java
│   ├── ProductDto.java
│   └── CategoryDto.java
└── mappers/                       # Entity to DTO Mappers
    ├── UserMapper.java
    ├── ProductMapper.java
    └── CategoryMapper.java
```

## Getting Started

### Prerequisites

- Java 23 or higher
- Maven 3.6 or higher
- MySQL 5.7 or higher

### Setup

1. Clone the repository:
   ```sh
   git clone https://github.com/mosh-hamedani/spring-api-starter
   cd spring-api-starter
   ```

2. Configure the database in `src/main/resources/application.yaml`:
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/store_api?createDatabaseIfNotExist=true
       username: root
       password: YOUR_PASSWORD
   ```

3. Build and run the application:
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080`

## API Endpoints

### Users
- `GET /api/users` - Get all users
- `POST /api/users` - Create a new user
- `GET /api/users/{id}` - Get user by ID
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user

### Products
- `GET /api/products` - Get all products
- `POST /api/products` - Create a new product
- `GET /api/products/{id}` - Get product by ID
- `PUT /api/products/{id}` - Update product
- `DELETE /api/products/{id}` - Delete product

### Categories
- `GET /api/categories` - Get all categories
- `POST /api/categories` - Create a new category
- `GET /api/categories/{id}` - Get category by ID
- `PUT /api/categories/{id}` - Update category
- `DELETE /api/categories/{id}` - Delete category

### Messages
- `GET /api/messages` - Get all messages
- `POST /api/messages` - Create a new message

### Home
- `GET /` - Welcome endpoint

## Features

- ✓ User management with profile support
- ✓ Product catalog with category organization
- ✓ Address management for users
- ✓ Messaging system
- ✓ Entity-to-DTO mapping for clean API contracts
- ✓ RESTful API design with full CRUD operations
- ✓ Automatic database schema management with Flyway
- ✓ Type-safe database access with Spring Data JPA
- ✓ Reduced boilerplate with Lombok

## Key Dependencies

- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Spring Boot Starter Thymeleaf
- MySQL Connector/J
- Flyway Core & MySQL
- Lombok
- Spring Boot Starter Test

## Database Schema

The application uses Flyway for automatic schema migrations. Migrations are managed in the `db/migration` directory and are executed automatically on application startup.

## Notes

- Sensitive configuration (database passwords) should be moved to environment variables in production
- Database credentials in `pom.xml` are for development only

## Learning Resources

For detailed learning resources, visit [The Ultimate Spring Boot Course](https://codewithmosh.com/p/spring-boot-building-apis)
