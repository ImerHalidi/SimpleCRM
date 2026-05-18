# Simple CRM API

## Overview

Simple CRM API is a RESTful Customer Relationship Management backend application developed using Java, Jersey (JAX-RS), JDBC, Maven, Apache Tomcat, and MySQL.

The project was built to simulate a real-world CRM system that allows businesses to manage:

* customers
* contact persons
* sales opportunities
* customer interactions

The system includes:

* CRUD operations
* filtering & search
* business logic endpoints
* SQL JOIN queries
* aggregation queries
* validation layer
* global exception handling
* REST API architecture

---

# Technologies Used

| Technology      | Purpose                |
| --------------- | ---------------------- |
| Java            | Backend development    |
| Jersey (JAX-RS) | REST API framework     |
| JDBC            | Database communication |
| MySQL           | Relational database    |
| Apache Tomcat   | Application server     |
| Maven           | Dependency management  |
| Gson            | JSON serialization     |
| Postman         | API testing            |

---

# Architecture

The project follows a layered architecture:

```text
Resource Layer → Service Layer → Database Layer
```

## Project Structure

```text
src/main/java/org/example
│
├── common
├── config
├── domain
├── exceptions
├── resources
├── services
└── utils
```

---

# Database Tables

## customer

Stores customer information.

## contact_person

Stores customer representatives linked to customers using foreign keys.

## opportunity

Stores sales opportunities and opportunity statuses.

## interaction_log

Stores customer communication history.

---

# Features

# Customer Management

* Create customer
* Update customer
* Delete customer
* Find customer by ID
* Get all customers
* Search customer by name
* Top customers endpoint

# Contact Person Management

* Create contact person
* Update contact person
* Delete contact person
* Find contact person by ID
* Get all contact persons

# Opportunity Management

* Create opportunity
* Update opportunity
* Delete opportunity
* Find opportunity by ID
* Filter opportunities
* Change opportunity status
* Opportunity summary endpoint
* Opportunity status count endpoint

# Interaction Log Management

* Create interaction log
* Update interaction log
* Delete interaction log
* Find interaction log by ID
* Customer interaction history
* Detailed interaction endpoint using SQL JOIN

---

# API Endpoints

# Customers

| Method | Endpoint                      | Description        |
| ------ | ----------------------------- | ------------------ |
| POST   | `/api/customer`               | Create customer    |
| GET    | `/api/customer`               | Get all customers  |
| GET    | `/api/customer/{id}`          | Get customer by ID |
| PUT    | `/api/customer/{id}`          | Update customer    |
| DELETE | `/api/customer/{id}`          | Delete customer    |
| GET    | `/api/customer/search?name=`  | Search customers   |
| GET    | `/api/customer/top-customers` | Top customers      |

---

# Contact Persons

| Method | Endpoint                   | Description              |
| ------ | -------------------------- | ------------------------ |
| POST   | `/api/contact-person`      | Create contact person    |
| GET    | `/api/contact-person`      | Get all contact persons  |
| GET    | `/api/contact-person/{id}` | Get contact person by ID |
| PUT    | `/api/contact-person/{id}` | Update contact person    |
| DELETE | `/api/contact-person/{id}` | Delete contact person    |

---

# Opportunities

| Method | Endpoint                                | Description           |
| ------ | --------------------------------------- | --------------------- |
| POST   | `/api/opportunity`                      | Create opportunity    |
| GET    | `/api/opportunity`                      | Filter opportunities  |
| GET    | `/api/opportunity/{id}`                 | Get opportunity by ID |
| PUT    | `/api/opportunity/{id}`                 | Update opportunity    |
| DELETE | `/api/opportunity/{id}`                 | Delete opportunity    |
| PUT    | `/api/opportunity/{id}/status/{status}` | Change status         |
| GET    | `/api/opportunity/summary`              | Opportunity summary   |
| GET    | `/api/opportunity/status-count`         | Status count          |

---

# Interaction Logs

| Method | Endpoint                                   | Description           |
| ------ | ------------------------------------------ | --------------------- |
| POST   | `/api/interaction-log`                     | Create interaction    |
| GET    | `/api/interaction-log`                     | Get all interactions  |
| GET    | `/api/interaction-log/{id}`                | Get interaction by ID |
| PUT    | `/api/interaction-log/{id}`                | Update interaction    |
| DELETE | `/api/interaction-log/{id}`                | Delete interaction    |
| GET    | `/api/customer/{id}/interactions`          | Customer interactions |
| GET    | `/api/customer/{id}/interactions/detailed` | Detailed interactions |

---

# Example Request

## Create Customer

```json
{
  "name": "BMW",
  "industry": "Automotive",
  "email": "bmw@test.com",
  "phone": "+38970111222",
  "status": "ACTIVE"
}
```

---

# Example Error Response

```json
{
  "errorCode": "VALIDATION_ERROR",
  "message": "Status is Required"
}
```

---

# Validation & Exception Handling

The application includes:

* ValidationException handling
* NotFoundException handling
* Global exception handling

All errors are returned in JSON format.

---

# SQL Features Used

The project demonstrates:

* INNER JOIN
* GROUP BY
* Aggregate functions
* Filtering
* Foreign keys
* CRUD queries
* Prepared Statements

---

# API Testing

All endpoints were tested using Postman.

The Postman collection is organized into:

* Customers
* Contact Persons
* Opportunities
* Interaction Logs

---

# How To Run

## 1. Clone Repository

```bash
git clone https://github.com/ImerHalidi/SimpleCRM.git
```

## 2. Configure Database

Create a MySQL database and configure database credentials.

## 3. Build Project

```bash
mvn clean install
```

## 4. Deploy on Tomcat

Deploy the generated WAR file into Apache Tomcat.

## 5. Run Application

Base URL:

```text
http://localhost:8081/simple-crm-api/api
```

---

# Future Improvements

* JWT Authentication
* Pagination
* Swagger/OpenAPI Documentation
* Unit Testing
* Docker Support

---

# Author

Developed by Imer Halidi
