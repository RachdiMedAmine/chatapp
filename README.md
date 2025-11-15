# Social Media Microservices Platform

A distributed social media platform built with Spring Boot microservices architecture, featuring user management, posts & engagement, and real-time messaging capabilities.

## 🏗️ Architecture Overview

This project implements a microservices architecture with service discovery, featuring:

- **Eureka Discovery Server** - Service registry for microservice discovery
- **User Service** - User management, profiles, and friend requests
- **Engagement Service** - Posts, likes, and comments
- **Chat Service** - Real-time messaging between users

## 🚀 Services

### 1. Eureka Discovery Server
**Port:** `8761`

Service registry that enables microservices to discover and communicate with each other.

**Access:** `http://localhost:8761`

### 2. User Service
**Port:** `8003`

Manages user accounts, profiles, and social connections.

**Features:**
- User registration and management
- User profiles with bio
- Friend request system (send, accept, view pending)

**Endpoints:**
```
GET    /users              - Get all users
GET    /users/{id}         - Get user by ID
POST   /users/register     - Register new user

GET    /profiles           - Get all profiles
GET    /profiles/{id}      - Get profile by ID

POST   /friendRequests/send             - Send friend request
GET    /friendRequests/pending/{userId} - Get pending requests
POST   /friendRequests/accept/{id}      - Accept friend request
```

### 3. Engagement Service
**Port:** `8001`

Handles social interactions including posts, likes, and comments.

**Features:**
- Create and view posts
- Like posts
- Comment on posts

**Endpoints:**
```
POST   /posts/create        - Create a post
GET    /posts/user/{userId} - Get posts by user

POST   /likes/add          - Like a post
GET    /likes/post/{postId} - Get likes for a post

POST   /comments/add        - Add comment to post
GET    /comments/post/{postId} - Get comments for a post
```

### 4. Chat Service
**Port:** `8002`

Manages direct messaging between users.

**Features:**
- Send messages between users
- Retrieve conversation history
- Message management

**Endpoints:**
```
POST   /messages/send                         - Send a message
GET    /messages/between/{senderId}/{receiverId} - Get conversation
GET    /messages                              - Get all messages
GET    /messages/{id}                         - Get message by ID
DELETE /messages/{id}                         - Delete message
```

## 🛠️ Technology Stack

- **Framework:** Spring Boot 3.4.5
- **Language:** Java 17/21
- **Build Tool:** Gradle (Kotlin DSL)
- **Service Discovery:** Netflix Eureka
- **Database:** H2 (in-memory)
- **ORM:** Spring Data JPA / Hibernate
- **Cloud:** Spring Cloud 2024.0.1

## 📋 Prerequisites

- Java 17 or higher (Java 21 for Eureka server)
- Gradle 8.13 (included via wrapper)

## 🚦 Getting Started

### 1. Start Eureka Discovery Server
```bash
cd eureka-discovery-server
./gradlew bootRun
```

Wait for Eureka to fully start before launching other services.

### 2. Start User Service
```bash
cd SOA-User-Service
./gradlew bootRun
```

### 3. Start Engagement Service
```bash
cd SOA-Engagement-Service
./gradlew bootRun
```

### 4. Start Chat Service
```bash
cd SOA-chat-service
./gradlew bootRun
```

### Verify Services

Visit Eureka Dashboard: `http://localhost:8761`

You should see all three services registered:
- `USER-SERVICE`
- `ENGAGEMENT-SERVICE`
- `CHAT-SERVICE`

## 📊 Database Access

Each service uses an H2 in-memory database with console access enabled:

- **User Service:** `http://localhost:8003/h2-console`
  - JDBC URL: `jdbc:h2:mem:userdb`
  
- **Engagement Service:** `http://localhost:8001/h2-console`
  - JDBC URL: `jdbc:h2:mem:engagementdb`
  
- **Chat Service:** `http://localhost:8002/h2-console`
  - JDBC URL: `jdbc:h2:mem:chatdb`

**Credentials:**
- Username: `sa`
- Password: `password`

## 📁 Project Structure
```
├── eureka-discovery-server/     # Service registry
│   └── Port: 8761
├── SOA-User-Service/            # User management
│   ├── user/                    # User entities & services
│   ├── profile/                 # User profiles
│   └── friendrequest/           # Friend request system
├── SOA-Engagement-Service/      # Social engagement
│   ├── post/                    # Post management
│   ├── like/                    # Like system
│   └── comment/                 # Comment system
└── SOA-chat-service/            # Messaging
    └── message/                 # Message management
```

## 🔄 Service Communication

Services communicate through:
1. **Service Discovery** - Eureka enables dynamic service location
2. **REST APIs** - Services expose RESTful endpoints
3. **ID References** - Services store foreign entity IDs (no cross-service JPA relations)

## 🧪 Testing with cURL

### Register a User
```bash
curl -X POST http://localhost:8003/users/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "profile": {
      "bio": "Software developer"
    }
  }'
```

### Create a Post
```bash
curl -X POST http://localhost:8001/posts/create \
  -H "Content-Type: application/json" \
  -d '{
    "authorId": 1,
    "content": "Hello, microservices!"
  }'
```

### Send a Message
```bash
curl -X POST http://localhost:8002/messages/send \
  -H "Content-Type: application/json" \
  -d '{
    "channelId": "channel-1",
    "senderId": 1,
    "receiverId": 2,
    "content": "Hey there!"
  }'
```

## 🗂️ Data Models

### User Service
- **User:** id, username, profile
- **Profile:** id, bio, user
- **FriendRequest:** id, requester, receiver, accepted, timestamp

### Engagement Service
- **Post:** id, authorId, content, timestamp, comments[], likes[]
- **Like:** id, userId, post, timestamp
- **Comment:** id, commenterId, post, content, timestamp

### Chat Service
- **Message:** id, channelId, senderId, receiverId, content

## 🔧 Configuration

Each service can be configured via `application.properties`:
```properties
# Service name for Eureka registration
spring.application.name=service-name

# Server port
server.port=80XX

# H2 Database
spring.datasource.url=jdbc:h2:mem:dbname
spring.h2.console.enabled=true

# Eureka Client (auto-configured)
# eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
```

## 🚀 Building for Production

Build all services:
```bash
# User Service
cd SOA-User-Service && ./gradlew build

# Engagement Service
cd SOA-Engagement-Service && ./gradlew build

# Chat Service
cd SOA-chat-service && ./gradlew build

# Eureka Server
cd eureka-discovery-server && ./gradlew build
```

JAR files will be in `build/libs/` directory of each service.

## 🐛 Troubleshooting

**Services not registering with Eureka:**
- Ensure Eureka server is running first
- Check `eureka.client.service-url.defaultZone` in application.properties
- Verify no port conflicts

**Database connection issues:**
- Verify H2 console is enabled
- Check JDBC URL matches configuration
- Ensure no other application is using the same database name

**Port conflicts:**
- Change `server.port` in application.properties
- Ensure no other applications are using ports 8001-8003, 8761

## 📝 API Documentation

For detailed API documentation, import the Postman collection or use tools like Swagger (can be added as dependency).

## 🔮 Future Enhancements

- [ ] Add API Gateway
- [ ] Implement authentication/authorization
- [ ] Add message queues (RabbitMQ/Kafka)
- [ ] Implement circuit breakers (Resilience4j)
- [ ] Add distributed tracing (Zipkin)
- [ ] Add centralized configuration (Spring Cloud Config)
- [ ] Implement WebSocket for real-time chat
- [ ] Add persistent databases (PostgreSQL/MySQL)
- [ ] Add comprehensive unit/integration tests
- [ ] Containerize services with Docker
- [ ] Add Kubernetes deployment configs

## 📄 License

This project is available for educational purposes.

---

**Built with Spring Boot & Spring Cloud**
