# Order Management System: CustomerOrder & ManufacturingOrder

##  Purpose

The goal of this project is to implement two independent modules based on **Domain-Driven Design (DDD)** principles and **hexagonal architecture (ports & adapters)**:

* `customerorder` – handles customer orders.
* `manufacturingorder` – handles manufacturing orders.

Although the modules are independent, they can communicate:

* **Synchronously** via application-level ports (facade)
* **Asynchronously** via domain events

---

##  Project Setup

1. **Start PostgreSQL databases** using Docker:

   ```bash
   docker compose up -d --build
   ```

2. **Run the application** by starting the `ItsolutionsppApplication` class.

Application uses lombok - make sure to use java 21

After the application starts, a full overview of available endpoints is accessible via **Swagger UI**:

```
http://localhost:8080/swagger-ui/index.html
```

---

##  Module Communication

* **Synchronous:** through application facades (application ports).
* **Asynchronous:** through domain events and messaging.

---

##  Technologies

* Java 21
* Spring Boot 3+
* 2 PostgreSQL databases (one for each module)
* JPA + Hibernate
* Maven
* JUnit 5 + Mockito (unit testing)

The entire application is covered by unit tests - line coverage 98%.

---

##  REST API Endpoints (Swagger)

### 🔹 Customer Order (`/api/v1/customer-order`)

| Method | Endpoint         | Description                                     |
| ------ | ---------------- | ----------------------------------------------- |
| POST   | `/create`        | Create a new customer order                     |
| GET    | `/{id}`          | Get details of a specific customer order        |
| PATCH  | `/cancel`        | Cancel a customer order                         |
| PATCH  | `/update-status` | Update statuses of related manufacturing orders |

---

### 🔸 Manufacturing Order (`/api/v1/manufacturing-orders`)

| Method | Endpoint               | Description                                                   |
| ------ | ---------------------- | ------------------------------------------------------------- |
| POST   | `/create`              | Create a new manufacturing order                              |
| GET    | `/{id}`                | Get details of a specific manufacturing order                 |
| GET    | `/customer-order/{id}` | Get manufacturing orders related to a specific customer order |
| PATCH  | `/cancel`              | Cancel a manufacturing order                                  |
| PATCH  | `/update-status`       | Update status of a manufacturing order                        |


Application deliberately does not implement delete operations as it is assumed that orders should not be deleted but only canceled.

This applies to update operations as well, where only the status of an order can be changed but not the order itself.
If the order is to be changed, a new order should be created instead.
