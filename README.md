# 🛒 Kreditcart - Microservices E-Commerce Backend

Kreditcart is a distributed microservices-based backend system for a scalable e-commerce platform. It uses Spring Boot and Spring Cloud to manage independent services such as product catalog, order processing, inventory, payments, and user management. The system is containerized and ready for cloud deployment with features like centralized API gateway, service discovery, and centralized Swagger UI.

---

## 🚀 Deployment Info

The backend API was deployed temporarily at:

🔗 **[http://13.233.253.181:8080/swagger-ui/index.html](http://13.233.253.181:8080/swagger-ui/index.html)**

⚠️ **Note:** This is hosted on a **free-tier EC2 instance** (t2.micro). Due to high memory usage across multiple services, the instance **crashed under load**. For stable testing or demos, please run the project locally.

---

## 🚀 Prerequisites

To run or develop this project locally, ensure you have the following installed:

### 🔧 Tools
- **Java 17** – Required to build and run Spring Boot services
- **Maven** – For dependency management and builds
- **Docker** – To run infrastructure dependencies like Kafka
- **Kafka** – Used for inter-service asynchronous messaging
  - You can run Kafka locally using Docker Compose, [see here](https://kafka.apache.org/quickstart)
- **IntelliJ IDEA** (or similar) – Recommended IDE with Spring Boot support


## 🚀 Features

- 📦 **Modular microservices** architecture with separate database per service
- 🔐 **Centralized API Gateway** using Spring Cloud Gateway
- 🌍 **Service Discovery** with Eureka
- 🔊 **Kafka-based communication** between services for event-driven architecture
- 🧾 **Centralized Swagger UI** for all microservices at the API gateway
- 🧪 **CORS handled dynamically** via environment-based configuration


---

## 🔄 Microservices

| Service           | Description                          | Path Prefix           | Port |
|------------------|--------------------------------------|------------------------|------|
| Service Discovery | Eureka-based service registry        | `/`                    | 8761 |
| API Gateway       | Central entry point for all traffic  | `/`                    | 8080 |
| Inventory Service | Stock and warehouse management       | `/inventory-svc/**`    | 8081 |
| Order Service     | Order placement & history            | `/order-svc/**`        | 8082 |
| Product Service   | Product catalog operations           | `/product-svc/**`      | 8083 |
| User Service      | Authentication & user profile        | `/user-svc/**`         | 8084 |
| Payment Service   | Payment processing and records       | `/payment-svc/**`      | 8085 |
| Email Service     | Email notifications & communication  | `/email-svc/**`        | 8086 |

---

## 📚 Centralized Swagger UI

Swagger for all services is accessible via the API Gateway: http://localhost:8080/swagger-ui.html

---

## ✅ Things to Ensure

1. **Eureka Server**:
   - **First**, start the Eureka server and ensure it is running on `http://localhost:8761`.

2. **API Gateway**:
   - **Next**, start the API Gateway and ensure it is running.
   - The API Gateway should be accessible at `http://localhost:8080`.

3. **Microservices**:
   - **Finally**, start your microservices (e.g., `order-svc`, `payment-svc`).
   - Ensure each service is properly registered in Eureka and can be accessed via the API Gateway.

4. **Check Service Registration in Eureka**:
   - Open the Eureka dashboard at `http://localhost:8761`.
   - Ensure all your services are visible and registered.

5. **Kafka**:
   - If using Kafka, ensure Kafka is running on `localhost:9092`.
   - Make sure that your services can communicate with Kafka.

---

### ✅ Verify All Services Are Running Properly

- **Eureka**: Ensure all services are registered and visible in the Eureka dashboard at `http://localhost:8761`.
- **API Gateway**: Test the routing by accessing the services through the API Gateway. 
  - For example, if your `order-svc` service is registered, you can test it by visiting `http://localhost:8080/order-svc/api-docs` (adjust the path according to your API's exposed endpoints).
  - If everything is correctly set up, you should be able to access the Swagger UI or any defined API endpoints of your microservices through the API Gateway.
- **Kafka**: Ensure Kafka is operational and services can communicate with it, if applicable.

---

Make sure all of the above services are running in the correct order:

**1. Eureka Server → 2. API Gateway → 3. Remaining Microservices**
