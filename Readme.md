# 🛒 Product Catalog Microservice

This is a Spring Boot microservice that allows you to manage product data (CRUD) with PostgreSQL as the database and a simulated Google Pub/Sub event publisher.

---

## Features

- Add, retrieve, update, and delete products
- Publishes product-related events
- RESTful API with Postman collection
- Unit tests with JUnit

---

## Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- JUnit
- Postman
- Pub/Sub
- IDE: Eclipse

---

### Prerequisites

- Java 21+
- Maven 3.8+
- PostgreSQL
- Eclipse IDE 
- Postman (for API calls)
- pgAdmin (for PostgreSQL)

---

### Clone or Extract Project

```bash
git clone https://github.com/your-repo/product-catalog.git
cd product-catalog

```
---

### To Run The Project

mvn clean install

mvn spring-boot:run

---

### Using Postman

-Open Postman

-Import the postman_collection.json file (provided with project)

-Use predefined requests:

	Create Product

	Get All Products

	Get by ID

	Update Product

---

### API Endpoints

| Method | Endpoint         | Description          |

| ------ | ---------------- | -------------------- |

| GET    | `/products`      | Get all products     |

| GET    | `/products/{id}` | Get product by ID    |

| POST   | `/products`      | Add a new product    |

| PUT    | `/products/{id}` | Update existing      |
