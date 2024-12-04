# **BookController API Testing**

Follow the steps below to test the `BookController` API endpoints.

---

## **Test the API**

### Start Your Application

Run the following command to start your Spring Boot application:

```bash
mvn spring-boot:run
```

#### 1. Get all books:
```bash
GET http://localhost:8081/api/books
```

#### 2. Get a book by ID:
```bash
GET http://localhost:8081/api/books/{id}
```

#### 3. Create a book:
```bash
POST http://localhost:8081/api/books
Content-Type: application/json

{
    "title": "Spring Boot Basics",
    "author": "John Doe",
    "isbn": "1234567890",
    "price": 19.99
}
```

#### 4. Update a book:
```bash
PUT http://localhost:8081/api/books/{id}
Content-Type: application/json

{
    "title": "Updated Title",
    "author": "Jane Doe",
    "isbn": "0987654321",
    "price": 24.99
}
```

#### 5. Delete a book:
```bash
DELETE http://localhost:8081/api/books/{id}
```

## **Unit Test the API Using Junit**
You can run JUnit 5 tests using the following methods:
```bash
mvn test
```
