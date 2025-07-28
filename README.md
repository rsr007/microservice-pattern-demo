# Microservices Patterns Demo

A demo project showcasing core microservices patterns using Spring Boot, Spring Cloud, Redis, and Resilience4j. This project includes service discovery, API gateway routing, Redis-backed rate limiting, and circuit breaker handling.

## 🧱 Project Structure

microservices-patterns-demo/
│
├── discovery-server/ # Eureka service registry
├── api-gateway/ # Spring Cloud Gateway with rate limiting and routing
├── user-service/ # Sample user service
├── order-service/ # Sample order service with circuit breaker protection
└── README.md # Project documentation


---

## 🔌 Services Overview

| Service          | Port  | Description |
|------------------|-------|-------------|
| `discovery-server` | `8761` | Eureka Server for service registration and discovery |
| `api-gateway`       | `8080` | Entry point for routing, rate limiting, and circuit breaker |
| `user-service`      | `8082` | Exposes user-related endpoints |
| `order-service`     | `8081` | Exposes order-related endpoints with circuit breaker |

---

### ⚙️ Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Cloud (Eureka, Gateway)
- Spring WebFlux
- Redis (for rate limiting)
- Resilience4j (Circuit Breaker)
- Maven
- IntelliJ IDEA

---

## 🔄 Microservice Patterns Implemented

### Service Discovery (Eureka)
- `discovery-server` runs on port `8761`
- All other services register themselves with Eureka
- `api-gateway` uses load-balanced URLs (`lb://service-name`) to route

### 🚦 API Gateway with Rate Limiting
- `api-gateway` routes requests to microservices
- Rate limiting is configured using `RequestRateLimiter` filter with Redis
- Limits defined per client IP (via `KeyResolver`)
- Example config:
  ```yaml
  filters:
    - name: RequestRateLimiter
      args:
        redis-rate-limiter.replenishRate: 5
        redis-rate-limiter.burstCapacity: 10
        key-resolver: "#{@ipKeyResolver}"
### Circuit Breaker with Resilience4j
1. Implemented in order-service to handle failures from user-service
2. Uses @CircuitBreaker annotation
3. Fallback methods provide graceful degradation

### Run the Services
You can start each service individually or use IntelliJ's multi-module run config:
1. start redis-server
2. discovery-service
3. api-gateway
4. user-service
5. order-service

### Testing the System
1. Service Registry
    Access http://localhost:8761 to see registered services
2. Rate Limiting
    Send repeated requests to: http://localhost:8080/order/hello
Exceeding limit should return 429 Too Many Requests
