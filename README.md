# Authentication API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Maven](https://img.shields.io/badge/maven-%23007DCC.svg?style=for-the-badge&logo=apache-maven&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![H2](https://img.shields.io/badge/H2-%23406BA5.svg?style=for-the-badge&logo=h2-database&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

This project is an API built with **Java, Spring Boot, Liquibase for database migrations, PostgreSQL, H2 for tests, Spring Security, and JWT for authentication**.

## Table of Contents

- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Authentication](#authentication)
- [Database](#database)
- [Contributing](#contributing)

## Installation

1. Clone the repository:

```bash
git clone https://github.com/santosediego/cobaia-test-auth.git
```

2. Install dependencies with Maven:

```bash
mvn clean install
```

3. Install [PostgreSQL](https://www.postgresql.org/) if not already installed.

## Configuration

- Configure `application-dev.properties` or `application-test.properties` for H2 database and testing
- JWT settings: `jwt.secret`, `jwt.duration`, `jwt.issuer`

## Usage

1. Start the application:

```bash
mvn spring-boot:run
```

2. The API will be accessible at: `http://localhost:8080`

## API Endpoints

| Method | Endpoint    | Description               | Role          |
| ------ | ----------- | ------------------------- | ------------- |
| GET    | /users      | Retrieve all users        | ADMIN         |
| GET    | /users/{id} | Retrieve a user by ID     | Authenticated |
| PUT    | /users/{id} | Update a user             | Authenticated |
| DELETE | /users/{id} | Delete a user             | ADMIN         |
| POST   | /auth/login | Log in to the application | Public        |

### Example Login Request

```bash
POST /auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "123456"
}
```

**Response:**

```json
{
  "access_token": "eyJhbGciOiJIUzI1NiIsInR..."
}
```

Use the JWT token in the `Authorization` header for protected endpoints:

```
Authorization: Bearer <access_token>
```

## Authentication

The API uses **Spring Security** for authentication control. The following roles are available:

```
USER  -> Standard user role for logged-in users
ADMIN -> Admin role for managing users (registering, deleting, updating)
```

## Database

- **PostgreSQL**: Main database for production. Managed using Liquibase migrations.
- **H2 Database**: Used for local development and testing, allowing fast and isolated tests without affecting the main database.

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request to the repository.

When contributing to this project, please follow the existing code style, [commit conventions](https://www.conventionalcommits.org/en/v1.0.0/), and submit your changes in a separate branch.
