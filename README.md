# Spring Boot RESTful API - Practical Questions

This project contains implementations of 5 main questions and 1 bonus question for Spring Boot RESTful API development.

## Project Structure

```
src/main/java/com/restapi/
├── RestApiApplication.java          (Main application class)
├── model/
│   ├── library/
│   │   └── Book.java
│   ├── student/
│   │   └── Student.java
│   ├── restaurant/
│   │   └── MenuItem.java
│   ├── ecommerce/
│   │   └── Product.java
│   ├── task/
│   │   └── Task.java
│   └── userprofile/
│       └── UserProfile.java
├── controller/
│   ├── library/
│   │   └── BookController.java
│   ├── student/
│   │   └── StudentController.java
│   ├── restaurant/
│   │   └── MenuController.java
│   ├── ecommerce/
│   │   └── ProductController.java
│   ├── task/
│   │   └── TaskController.java
│   └── userprofile/
│       └── UserProfileController.java
└── util/
    └── ApiResponse.java
```

## Technologies Used

- Spring Boot 3.2.0
- Java 17
- Maven
- Spring Web

## How to Run the Application

1. **Prerequisites**
   - Java 17 or higher installed
   - Maven installed
   - Git installed

2. **Clone or Extract the Project**
   ```bash
   cd "second assignment"
   ```

3. **Build the Project**
   ```bash
   mvn clean install
   ```

4. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```
   
   The application will start on `http://localhost:8080`

## API Endpoints Documentation

### Question 1: Library Book Management API (20 Points)

**Base URL**: `http://localhost:8080/api/books`

#### Endpoints:

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/books` | Get all books |
| GET | `/api/books/{id}` | Get a specific book by ID |
| GET | `/api/books/search?title={title}` | Search books by title |
| POST | `/api/books` | Add a new book |
| DELETE | `/api/books/{id}` | Delete a book by ID |

#### Sample Requests/Responses:

**GET /api/books**
```bash
curl -X GET "http://localhost:8080/api/books"
```

Response:
```json
[
  {
    "id": 1,
    "title": "Clean Code",
    "author": "Robert Martin",
    "isbn": "978-0132350884",
    "publicationYear": 2008
  },
  {
    "id": 2,
    "title": "The Pragmatic Programmer",
    "author": "David Thomas",
    "isbn": "978-0201616224",
    "publicationYear": 1999
  }
]
```

**POST /api/books**
```bash
curl -X POST "http://localhost:8080/api/books" \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Effective Java",
    "author": "Joshua Bloch",
    "isbn": "978-0134685991",
    "publicationYear": 2018
  }'
```

Response: Status 201 Created
```json
{
  "id": 4,
  "title": "Effective Java",
  "author": "Joshua Bloch",
  "isbn": "978-0134685991",
  "publicationYear": 2018
}
```

**GET /api/books/{id}**
```bash
curl -X GET "http://localhost:8080/api/books/1"
```

**GET /api/books/search**
```bash
curl -X GET "http://localhost:8080/api/books/search?title=Clean"
```

**DELETE /api/books/{id}**
```bash
curl -X DELETE "http://localhost:8080/api/books/1"
```

Response: Status 204 No Content

---

### Question 2: Student Registration API (20 Points)

**Base URL**: `http://localhost:8080/api/students`

#### Endpoints:

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/students` | Get all students |
| GET | `/api/students/{studentId}` | Get student by ID |
| GET | `/api/students/major/{major}` | Get all students by major |
| GET | `/api/students/filter?gpa={minGpa}` | Filter students with GPA >= minGpa |
| POST | `/api/students` | Register a new student |
| PUT | `/api/students/{studentId}` | Update student information |

#### Sample Requests/Responses:

**GET /api/students**
```bash
curl -X GET "http://localhost:8080/api/students"
```

Response:
```json
[
  {
    "studentId": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john@example.com",
    "major": "Computer Science",
    "gpa": 3.8
  },
  {
    "studentId": 2,
    "firstName": "Jane",
    "lastName": "Smith",
    "email": "jane@example.com",
    "major": "Computer Science",
    "gpa": 3.9
  }
]
```

**GET /api/students/major/{major}**
```bash
curl -X GET "http://localhost:8080/api/students/major/Computer%20Science"
```

**GET /api/students/filter**
```bash
curl -X GET "http://localhost:8080/api/students/filter?gpa=3.5"
```

This returns all students with GPA >= 3.5

**POST /api/students**
```bash
curl -X POST "http://localhost:8080/api/students" \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Robert",
    "lastName": "Taylor",
    "email": "robert@example.com",
    "major": "Computer Science",
    "gpa": 3.7
  }'
```

Response: Status 201 Created

**PUT /api/students/{studentId}**
```bash
curl -X PUT "http://localhost:8080/api/students/1" \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "major": "Computer Science",
    "gpa": 3.9
  }'
```

---

### Question 3: Restaurant Menu API (20 Points)

**Base URL**: `http://localhost:8080/api/menu`

#### Endpoints:

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/menu` | Get all menu items |
| GET | `/api/menu/{id}` | Get specific menu item |
| GET | `/api/menu/category/{category}` | Get items by category |
| GET | `/api/menu/available?available={true/false}` | Get available/unavailable items |
| GET | `/api/menu/search?name={name}` | Search menu items by name |
| POST | `/api/menu` | Add new menu item |
| PUT | `/api/menu/{id}/availability` | Toggle item availability |
| DELETE | `/api/menu/{id}` | Remove menu item |

#### Sample Requests/Responses:

**GET /api/menu**
```bash
curl -X GET "http://localhost:8080/api/menu"
```

Response:
```json
[
  {
    "id": 1,
    "name": "Spring Rolls",
    "description": "Crispy spring rolls with vegetables",
    "price": 5.99,
    "category": "Appetizer",
    "available": true
  },
  {
    "id": 2,
    "name": "Bruschetta",
    "description": "Toasted bread with tomato and basil",
    "price": 4.99,
    "category": "Appetizer",
    "available": true
  }
]
```

**GET /api/menu/category/{category}**
```bash
curl -X GET "http://localhost:8080/api/menu/category/Appetizer"
```

**GET /api/menu/available**
```bash
curl -X GET "http://localhost:8080/api/menu/available?available=true"
```

**GET /api/menu/search**
```bash
curl -X GET "http://localhost:8080/api/menu/search?name=Salmon"
```

**POST /api/menu**
```bash
curl -X POST "http://localhost:8080/api/menu" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Pad Thai",
    "description": "Thai noodle stir-fry",
    "price": 12.99,
    "category": "Main Course",
    "available": true
  }'
```

**PUT /api/menu/{id}/availability**
```bash
curl -X PUT "http://localhost:8080/api/menu/1/availability"
```

This toggles the availability of menu item with ID 1

**DELETE /api/menu/{id}**
```bash
curl -X DELETE "http://localhost:8080/api/menu/1"
```

---

### Question 4: E-Commerce Product API (25 Points)

**Base URL**: `http://localhost:8080/api/products`

#### Endpoints:

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/products?page={page}&limit={limit}` | Get all products with pagination |
| GET | `/api/products/{productId}` | Get product details |
| GET | `/api/products/category/{category}` | Get products by category |
| GET | `/api/products/brand/{brand}` | Get products by brand |
| GET | `/api/products/search?keyword={keyword}` | Search products by keyword |
| GET | `/api/products/price-range?min={min}&max={max}` | Get products within price range |
| GET | `/api/products/in-stock` | Get products with stock > 0 |
| POST | `/api/products` | Add new product |
| PUT | `/api/products/{productId}` | Update product details |
| PATCH | `/api/products/{productId}/stock?quantity={quantity}` | Update stock quantity |
| DELETE | `/api/products/{productId}` | Delete product |

#### Sample Requests/Responses:

**GET /api/products**
```bash
curl -X GET "http://localhost:8080/api/products?page=1&limit=5"
```

Response:
```json
[
  {
    "productId": 1,
    "name": "Laptop Dell XPS",
    "description": "High-performance laptop with Intel i7",
    "price": 1299.99,
    "category": "Electronics",
    "stockQuantity": 5,
    "brand": "Dell"
  },
  {
    "productId": 2,
    "name": "iPhone 15",
    "description": "Latest Apple smartphone with 5G",
    "price": 999.99,
    "category": "Electronics",
    "stockQuantity": 10,
    "brand": "Apple"
  }
]
```

**GET /api/products/{productId}**
```bash
curl -X GET "http://localhost:8080/api/products/1"
```

**GET /api/products/category/{category}**
```bash
curl -X GET "http://localhost:8080/api/products/category/Electronics"
```

**GET /api/products/brand/{brand}**
```bash
curl -X GET "http://localhost:8080/api/products/brand/Apple"
```

**GET /api/products/search**
```bash
curl -X GET "http://localhost:8080/api/products/search?keyword=laptop"
```

**GET /api/products/price-range**
```bash
curl -X GET "http://localhost:8080/api/products/price-range?min=100&max=500"
```

**GET /api/products/in-stock**
```bash
curl -X GET "http://localhost:8080/api/products/in-stock"
```

**POST /api/products**
```bash
curl -X POST "http://localhost:8080/api/products" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Mechanical Keyboard",
    "description": "RGB Mechanical Gaming Keyboard",
    "price": 149.99,
    "category": "Electronics",
    "stockQuantity": 20,
    "brand": "Corsair"
  }'
```

Response: Status 201 Created

**PUT /api/products/{productId}**
```bash
curl -X PUT "http://localhost:8080/api/products/1" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Laptop Dell XPS 15",
    "description": "High-performance laptop with Intel i9",
    "price": 1499.99,
    "category": "Electronics",
    "stockQuantity": 3,
    "brand": "Dell"
  }'
```

**PATCH /api/products/{productId}/stock**
```bash
curl -X PATCH "http://localhost:8080/api/products/1/stock?quantity=15"
```

**DELETE /api/products/{productId}**
```bash
curl -X DELETE "http://localhost:8080/api/products/1"
```

---

### Question 5: Task Management API (15 Points)

**Base URL**: `http://localhost:8080/api/tasks`

#### Endpoints:

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/tasks` | Get all tasks |
| GET | `/api/tasks/{taskId}` | Get task by ID |
| GET | `/api/tasks/status?completed={true/false}` | Get tasks by completion status |
| GET | `/api/tasks/priority/{priority}` | Get tasks by priority |
| POST | `/api/tasks` | Create new task |
| PUT | `/api/tasks/{taskId}` | Update task |
| PATCH | `/api/tasks/{taskId}/complete` | Mark task as completed |
| DELETE | `/api/tasks/{taskId}` | Delete task |

#### Sample Requests/Responses:

**GET /api/tasks**
```bash
curl -X GET "http://localhost:8080/api/tasks"
```

Response:
```json
[
  {
    "taskId": 1,
    "title": "Complete Project",
    "description": "Finish the Spring Boot project",
    "completed": false,
    "priority": "HIGH",
    "dueDate": "2026-02-15"
  },
  {
    "taskId": 2,
    "title": "Review Code",
    "description": "Review team's code changes",
    "completed": false,
    "priority": "MEDIUM",
    "dueDate": "2026-02-12"
  }
]
```

**GET /api/tasks/status**
```bash
curl -X GET "http://localhost:8080/api/tasks/status?completed=false"
```

**GET /api/tasks/priority/{priority}**
```bash
curl -X GET "http://localhost:8080/api/tasks/priority/HIGH"
```

**POST /api/tasks**
```bash
curl -X POST "http://localhost:8080/api/tasks" \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Design Database",
    "description": "Design the database schema",
    "completed": false,
    "priority": "HIGH",
    "dueDate": "2026-02-20"
  }'
```

Response: Status 201 Created

**PUT /api/tasks/{taskId}**
```bash
curl -X PUT "http://localhost:8080/api/tasks/1" \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Complete Project Documentation",
    "description": "Finish the Spring Boot project documentation",
    "completed": false,
    "priority": "HIGH",
    "dueDate": "2026-02-18"
  }'
```

**PATCH /api/tasks/{taskId}/complete**
```bash
curl -X PATCH "http://localhost:8080/api/tasks/1/complete"
```

This marks task 1 as completed

**DELETE /api/tasks/{taskId}**
```bash
curl -X DELETE "http://localhost:8080/api/tasks/1"
```

---

### Bonus Question: User Profile API (20 Points)

**Base URL**: `http://localhost:8080/api/users`

#### Endpoints:

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/users` | Get all user profiles |
| GET | `/api/users/{userId}` | Get user profile by ID |
| GET | `/api/users/search/username?username={username}` | Search by username |
| GET | `/api/users/country/{country}` | Get users by country |
| GET | `/api/users/age-range?minAge={min}&maxAge={max}` | Get users by age range |
| POST | `/api/users` | Create new user profile |
| PUT | `/api/users/{userId}` | Update user profile |
| PATCH | `/api/users/{userId}/activate` | Activate user profile |
| PATCH | `/api/users/{userId}/deactivate` | Deactivate user profile |
| DELETE | `/api/users/{userId}` | Delete user profile |

#### Sample Requests/Responses:

**GET /api/users**
```bash
curl -X GET "http://localhost:8080/api/users"
```

Response:
```json
{
  "success": true,
  "message": "Users retrieved successfully",
  "data": [
    {
      "userId": 1,
      "username": "john_doe",
      "email": "john@example.com",
      "fullName": "John Doe",
      "age": 28,
      "country": "USA",
      "bio": "Software Developer passionate about coding",
      "active": true
    }
  ]
}
```

**GET /api/users/{userId}**
```bash
curl -X GET "http://localhost:8080/api/users/1"
```

**GET /api/users/search/username**
```bash
curl -X GET "http://localhost:8080/api/users/search/username?username=john_doe"
```

**GET /api/users/country/{country}**
```bash
curl -X GET "http://localhost:8080/api/users/country/USA"
```

**GET /api/users/age-range**
```bash
curl -X GET "http://localhost:8080/api/users/age-range?minAge=25&maxAge=35"
```

**POST /api/users**
```bash
curl -X POST "http://localhost:8080/api/users" \
  -H "Content-Type: application/json" \
  -d '{
    "username": "michael_scott",
    "email": "michael@example.com",
    "fullName": "Michael Scott",
    "age": 45,
    "country": "USA",
    "bio": "Business Manager and leader"
  }'
```

Response: Status 201 Created
```json
{
  "success": true,
  "message": "User profile created successfully",
  "data": {
    "userId": 6,
    "username": "michael_scott",
    "email": "michael@example.com",
    "fullName": "Michael Scott",
    "age": 45,
    "country": "USA",
    "bio": "Business Manager and leader",
    "active": true
  }
}
```

**PUT /api/users/{userId}**
```bash
curl -X PUT "http://localhost:8080/api/users/1" \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe_updated",
    "email": "john.doe@example.com",
    "fullName": "John Michael Doe",
    "age": 29,
    "country": "USA",
    "bio": "Senior Software Developer"
  }'
```

**PATCH /api/users/{userId}/activate**
```bash
curl -X PATCH "http://localhost:8080/api/users/4/activate"
```

**PATCH /api/users/{userId}/deactivate**
```bash
curl -X PATCH "http://localhost:8080/api/users/1/deactivate"
```

**DELETE /api/users/{userId}**
```bash
curl -X DELETE "http://localhost:8080/api/users/1"
```

Response: Status 204 No Content

---

## HTTP Status Codes Used

- **200 OK** - Successful GET, PUT request
- **201 Created** - Successful POST request (resource created)
- **204 No Content** - Successful DELETE request
- **404 Not Found** - Resource not found
- **400 Bad Request** - Invalid request parameters

## Testing with Postman

1. **Import Collection**: You can import the above curl commands into Postman
2. **Create Environment Variables**: Set `{{base_url}}` as `http://localhost:8080`
3. **Test Each Endpoint**: Run through all endpoints to verify functionality

## Code Quality Standards

- ✅ Meaningful variable names
- ✅ Comments added for complex logic
- ✅ Java naming conventions followed (camelCase for variables/methods, PascalCase for classes)
- ✅ Proper indentation and formatting
- ✅ RESTful design principles followed
- ✅ Separation of concerns with model and controller packages

## Project Features

1. **Question 1 - Library Book Management**: Complete CRUD operations for books
2. **Question 2 - Student Registration**: Advanced filtering by major and GPA
3. **Question 3 - Restaurant Menu**: Dynamic availability toggling and category filtering
4. **Question 4 - E-Commerce**: Advanced search, filtering, and pagination features
5. **Question 5 - Task Management**: Task status tracking and priority filtering
6. **Bonus - User Profile**: Comprehensive user management with custom response wrappers

All endpoints are fully functional and ready for testing!
#   w e b - t e c h - a s s i g n m e n t - 2 
 
 