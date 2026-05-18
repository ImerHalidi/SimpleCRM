# Simple CRM API

## Project Overview

Simple CRM API is a backend Customer Relationship Management system developed using Java, Jersey (JAX-RS), Maven, Tomcat, and MySQL.

The project provides RESTful API endpoints for managing:

* Customers
* Contact Persons
* Opportunities
* Interaction Logs

The system supports full CRUD operations, filtering, search functionality, business logic endpoints, SQL aggregation queries, JOIN operations, validation, and global exception handling.

---

# Technologies Used

* Java
* Jersey (JAX-RS)
* Maven
* Apache Tomcat
* MySQL
* JDBC
* Gson
* Postman

---

# Project Structure

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

# Features

## Customer Management

* Create customer
* Update customer
* Delete customer
* Get customer by ID
* Get all customers
* Search customer by name

## Contact Person Management

* Create contact person
* Update contact person
* Delete contact person
* Get contact person by ID
* Get all contact persons

## Opportunity Management

* Create opportunity
* Update opportunity
* Delete opportunity
* Get opportunity by ID
* Filter opportunities
* Change opportunity status
* Opportunity statistics
* Opportunity summary by status

## Interaction Log Management

* Create interaction log
* Update interaction log
* Delete interaction log
* Get interaction log by ID
* Get customer interactions
* Detailed interaction endpoint using SQL JOIN

---

# Business Logic Endpoints

## Opportunity Status Update

```http
PUT /api/opportunity/{id}/status/{status}
```

Example:

```http
PUT /api/opportunity/1/status/WON
```

Supported statuses:

* NEW
* IN_PROGRESS
* WON
* LOST

---

## Opportunity Status Count

```http
GET /api/opportunity/status-count
```

Returns the number of opportunities grouped by status.

---

## Opportunity Summary

```http
GET /api/opportunity/summary
```

Returns the total value of opportunities grouped by status.

---

## Customer Detailed Interactions

```http
GET /api/customer/{id}/interactions/detailed
```

Uses SQL JOIN queries to return:

* customer information
* contact person information
* interaction details

---

## Top Customers

```http
GET /api/customer/top-customers
```

Returns customers with the highest opportunity values.

---

# Validation & Error Handling

The project includes:

* ValidationException handling
* NotFoundException handling
* Global exception handling

Errors are returned in JSON format:

```json
{
  "errorCode": "VALIDATION_ERROR",
  "message": "Status is Required"
}
```

---

# Database

The project uses MySQL with relational tables:

* customer
* contact_person
* opportunity
* interaction_log

Relationships are implemented using foreign keys and JOIN queries.

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

Create a MySQL database and update database credentials inside the project configuration.

## 3. Build Project

```bash
mvn clean install
```

## 4. Deploy on Tomcat

Deploy the generated WAR file to Apache Tomcat.

## 5. Run Application

Example base URL:

```http
http://localhost:8081/simple-crm-api/api
```

---

# Author

Imer Halidi

---
