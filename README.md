# CareerConnect : A Job Application Management System

## Introduction
> CarrerConnect is a Job Application Management System based on Spring Boot based microservice web application designed for searching job, compnay information and their reviews. 

## Prerequisites

- Java 8 or later
- Maven
- Basic understanding of Spring Boot and microservices architecture
- Testing tools Junit and Mockito, Postman (API's testing)
- Docker

## Microservices

1. **Gateway** : Acts as the single entry point for all API requests, routing them to the appropriate microservices
2. **Service-Registry** : Enables service discovery, allowing microservices to locate each other dynamically.
3. **Job** : Uses Company Service for managing job posted by particular company and performs CRUD operations to manage data.
4. **Company** : Handles operations related to company to manage data and provide RESTful service.
5. **Review** : Performs CRUD (create, read, update, delete) operations on reviews to manage data.

## Installation

**Clone this repository:**
```Bash
git clone https://github.com/AkshayBaviskar07/CareerConnect
```

**Install dependencies:**
```Bash
mvn install
```

### Running the Application

1. Start the service-registry (Eureka Server):
```Bash
mvn spring-boot:run -p service-reg
```

2. Start the gateway:
```Bash
mvn spring-boot:run -p gateway
```

3. Start the job-service:
```Bash
mvn spring-boot:run -p jobms
```

4. Start the company-service:
```Bash
mvn spring-boot:run -p companyms
```

5. Start the review-service:
```Bash
mvn spring-boot:run -p reviewms
```

Once all services are running, you can interact with the application through the API gateway using tools like Postman.

## Health Checks:

Spring Actuator exposes health endpoints at the following paths:

- `/health`: Overall application health
- `/info`: This endpoint provides information about your application, such as the version and build details.
-  `/metrics`: This endpoint provides metrics about your application, such as CPU usage and memory usage.

## Development

1. Clone the repository as mentioned above.
2. Install dependencies using `mvn install`.
3. Use an IDE like Spring Tool Suite or IntelliJ IDEA for development.
4. Start the services as shown in the "Running the Application" section.
5. Make code changes and test them using JUnit and Mockito.
6. Commit your changes and push them to your remote repository.

## Api-Testing :

1. [Job-Service](https://web.postman.co/workspace/Job-Application~030a0559-860c-4698-9b47-9644dfbdace5/collection/31938115-ede4ae5f-a9a6-4cf9-a07a-8409f799bc70?action=share&source=copy-link&creator=31938115) : Provides documentation about job service.
2. [Compnay-Service](https://web.postman.co/workspace/Job-Application~030a0559-860c-4698-9b47-9644dfbdace5/collection/31938115-4fc58214-73b9-44c8-b47f-a07a5e257d47?action=share&source=copy-link&creator=31938115) :  Provides documentation about compnay service.
3. [Review-Service](https://web.postman.co/workspace/Job-Application~030a0559-860c-4698-9b47-9644dfbdace5/collection/31938115-48eab072-9f22-4cae-8e9d-27b588222322?action=share&source=copy-link&creator=31938115) :  Provides documentation about review service.
4. [Gateway](https://web.postman.co/workspace/Job-Application~030a0559-860c-4698-9b47-9644dfbdace5/collection/31938115-e2601a2d-08c9-4a50-94a7-e33c0722870a?action=share&source=copy-link&creator=31938115) :  Provides documentation about gateway.

