# Quick Start Guide

## Building and Running the Application

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Step 1: Build the Project
```bash
cd "second assignment"
mvn clean install
```

### Step 2: Run the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### Step 3: Test an Endpoint

**Using curl (Command Prompt/PowerShell):**
```bash
curl -X GET "http://localhost:8080/api/books"
```

**Using Postman:**
1. Open Postman
2. Create a new GET request
3. Enter URL: `http://localhost:8080/api/books`
4. Click Send

### Example Responses

**GET /api/books**
```json
[
  {
    "id": 1,
    "title": "Clean Code",
    "author": "Robert Martin",
    "isbn": "978-0132350884",
    "publicationYear": 2008
  }
]
```

**POST /api/books** (Create new book)
```bash
curl -X POST "http://localhost:8080/api/books" \
  -H "Content-Type: application/json" \
  -d '{"title":"Effective Java","author":"Joshua Bloch","isbn":"978-0134685991","publicationYear":2018}'
```

## Available Endpoints Summary

### Question 1 - Library Books
- GET `/api/books` - All books
- GET `/api/books/{id}` - Specific book
- GET `/api/books/search?title={title}` - Search by title
- POST `/api/books` - Create book
- DELETE `/api/books/{id}` - Delete book

### Question 2 - Students
- GET `/api/students` - All students
- GET `/api/students/{studentId}` - Specific student
- GET `/api/students/major/{major}` - By major
- GET `/api/students/filter?gpa={gpa}` - By GPA
- POST `/api/students` - Create student
- PUT `/api/students/{studentId}` - Update student

### Question 3 - Menu Items
- GET `/api/menu` - All items
- GET `/api/menu/{id}` - Specific item
- GET `/api/menu/category/{category}` - By category
- GET `/api/menu/available?available={true/false}` - Available items
- GET `/api/menu/search?name={name}` - Search by name
- POST `/api/menu` - Create item
- PUT `/api/menu/{id}/availability` - Toggle availability
- DELETE `/api/menu/{id}` - Delete item

### Question 4 - Products
- GET `/api/products?page={page}&limit={limit}` - All products (paginated)
- GET `/api/products/{productId}` - Specific product
- GET `/api/products/category/{category}` - By category
- GET `/api/products/brand/{brand}` - By brand
- GET `/api/products/search?keyword={keyword}` - Search
- GET `/api/products/price-range?min={min}&max={max}` - By price
- GET `/api/products/in-stock` - In stock only
- POST `/api/products` - Create product
- PUT `/api/products/{productId}` - Update product
- PATCH `/api/products/{productId}/stock?quantity={quantity}` - Update stock
- DELETE `/api/products/{productId}` - Delete product

### Question 5 - Tasks
- GET `/api/tasks` - All tasks
- GET `/api/tasks/{taskId}` - Specific task
- GET `/api/tasks/status?completed={true/false}` - By status
- GET `/api/tasks/priority/{priority}` - By priority
- POST `/api/tasks` - Create task
- PUT `/api/tasks/{taskId}` - Update task
- PATCH `/api/tasks/{taskId}/complete` - Mark complete
- DELETE `/api/tasks/{taskId}` - Delete task

### Bonus - User Profiles
- GET `/api/users` - All users
- GET `/api/users/{userId}` - Specific user
- GET `/api/users/search/username?username={username}` - Search by username
- GET `/api/users/country/{country}` - By country
- GET `/api/users/age-range?minAge={min}&maxAge={max}` - By age range
- POST `/api/users` - Create user
- PUT `/api/users/{userId}` - Update user
- PATCH `/api/users/{userId}/activate` - Activate user
- PATCH `/api/users/{userId}/deactivate` - Deactivate user
- DELETE `/api/users/{userId}` - Delete user

## Sample Data

All endpoints come pre-populated with sample data:
- Books: 3 books
- Students: 5 students
- Menu Items: 8 items (2 appetizers, 2 main courses, 2 desserts, 2 beverages)
- Products: 10 products
- Tasks: 5 tasks
- Users: 5 users

## Troubleshooting

### Port Already in Use
If port 8080 is already in use, change it in `src/main/resources/application.properties`:
```properties
server.port=8081
```

### Maven Build Issues
```bash
mvn clean
mvn install -DskipTests=true
```

### Class Not Found
Ensure Java 17 is installed:
```bash
java -version
```

## Project Structure

```
src/main/java/com/restapi/
├── RestApiApplication.java          # Main entry point
├── model/                           # Data models
│   ├── library/Book.java
│   ├── student/Student.java
│   ├── restaurant/MenuItem.java
│   ├── ecommerce/Product.java
│   ├── task/Task.java
│   └── userprofile/UserProfile.java
├── controller/                      # REST endpoints
│   ├── library/BookController.java
│   ├── student/StudentController.java
│   ├── restaurant/MenuController.java
│   ├── ecommerce/ProductController.java
│   ├── task/TaskController.java
│   └── userprofile/UserProfileController.java
└── util/                            # Utilities
    └── ApiResponse.java             # Response wrapper
```

## HTTP Status Codes

- `200 OK` - Successful GET/PUT request
- `201 CREATED` - Successful POST request
- `204 NO CONTENT` - Successful DELETE request
- `404 NOT FOUND` - Resource doesn't exist

## Next Steps

1. Build and run the application
2. Test each endpoint with Postman or curl
3. Verify all responses are correct
4. Review the detailed README.md for complete documentation

For detailed API documentation, see [README.md](README.md)
