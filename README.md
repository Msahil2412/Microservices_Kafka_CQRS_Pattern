# Bank Account CQRS/ES Microservices System

This repository implements a **bank account management system** using **CQRS (Command Query Responsibility Segregation)** and **Event Sourcing** patterns with **Spring Boot**, **Kafka**, **MongoDB**, and **Spring Cloud Gateway**. The system is designed as a set of microservices for scalability, maintainability, and extensibility.

## Architecture Overview

- **CQRS/ES**: Separates write (command) and read (query) responsibilities, using event sourcing for state changes.
- **Kafka**: Used for event publishing and inter-service communication.
- **MongoDB**: Used for event and state persistence.
- **Spring Cloud Gateway**: Serves as the API gateway, routing requests to backend services.

## Microservices

### 1. account.cmd
- Handles commands (open, close, deposit, withdraw) for bank accounts.
- Publishes events to Kafka.
- Exposes REST APIs for command operations.

### 2. account.query
- Listens to account events from Kafka.
- Updates query-side views in MongoDB.
- Exposes REST APIs for querying account data.

### 3. account.common
- Contains shared DTOs, events, and enums used by other services.

### 4. logging microservice (optional)
- Consumes all Kafka topics and logs event activity to a database.

### 5. apisgatewayconfig
- Spring Cloud Gateway project.
- Routes API requests to the appropriate backend microservices.

## Key Endpoints

| Service         | Endpoint Example                        | Description                  |
|-----------------|-----------------------------------------|------------------------------|
| account.cmd     | `/api/v1/openBankAccount`               | Open a new account           |
| account.cmd     | `/api/v1/closeBankAccount`              | Close an account             |
| account.cmd     | `/api/v1/depositFunds`                  | Deposit funds                |
| account.cmd     | `/api/v1/withdrawFunds`                 | Withdraw funds               |
| account.query   | `/api/v1/query/accounts`                | Query account info           |
| logging (opt.)  | `/api/v1/logs`                          | View event logs              |

All endpoints are accessible via the API Gateway (default port: `8084`).

## Getting Started

### Prerequisites
- Java 17+
- Maven 3.8+
- MongoDB
- Kafka

### Build & Run

1. **Start MongoDB and Kafka** (ensure both are running on their default ports).
2. **Build all projects:**
   ```sh
   mvn clean install
   ```
3. **Run each microservice:**
   ```sh
   # In separate terminals
   cd bank-account/account.cmd && mvn spring-boot:run
   cd bank-account/account.query && mvn spring-boot:run
   cd apisgatewayconfig && mvn spring-boot:run
   # (Optional) cd logging-microservice && mvn spring-boot:run
   ```
4. **Access APIs via Gateway:**
   - Example: `http://localhost:8084/api/v1/openBankAccount`

## Configuration

- **Kafka, MongoDB, and service ports** are configured in each service's `application.yml`.
- **Gateway routes** are defined in `apisgatewayconfig/src/main/resources/application.yml`.

## Extending the System
- Add new events and handlers in `account.common` and propagate to other services.
- Add new microservices and register their routes in the gateway.

## License

This project is licensed under the MIT License.
