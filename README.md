
# Redis Spring Boot Application

This project demonstrates the integration of **Redis** caching with a **Spring Boot** application for managing user data. The application performs CRUD (Create, Read, Update, and Delete) operations on a `User` entity, while utilizing Redis as a caching layer to optimize performance by reducing database hits.

## Features

- **Redis Caching**: Caches the user data to improve response times for repeated requests.
- **CRUD Operations**: Supports creating, reading, updating, and deleting user data.
- **Spring Boot**: A fully functional Spring Boot application with Redis configuration.

## Technologies Used

- **Spring Boot 2.6.3**
- **Redis**
- **Spring Data JPA**
- **Lettuce** (Redis client)
- **JPA** for Database interaction
- **H2 Database** (in-memory for development purposes)

## Setup and Installation

### Prerequisites

Before running the application, make sure you have the following installed:

- **Java 17 or later**
- **Maven** (for building the project)
- **Redis** (Redis server must be running)

You can install Redis on your local machine by following the official guide: [Redis Installation](https://redis.io/download).

### Steps to Run the Application

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/balajiganesan1220/Redis-Caching.git
   cd Redis-Caching
