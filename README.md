# Spring Boot RESTful API - Practical Question

This project contains implementations of 5 main questions and 1 bonus question for Spring Boot RESTful API development.

## Project Structure

```text
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
Technologies UsedSpring Boot 3.2.0Java 17MavenSpring WebHow to Run the ApplicationPrerequisitesJava 17 or higher installedMaven installedGit installedClone and NavigateBashgit clone [https://github.com/Murenz467/web-tech-assignment-2.git](https://github.com/Murenz467/web-tech-assignment-2.git)
cd web-tech-assignment-2
Build the ProjectBashmvn clean install
Run the ApplicationBashmvn spring-boot:run
The application will start on http://localhost:8080API Endpoints DocumentationQuestion 1: Library Book Management APIBase URL: http://localhost:8080/api/booksMethodEndpointDescriptionGET/api/booksGet all booksGET/api/books/{id}Get a specific book by IDGET/api/books/search?title={title}Search books by titlePOST/api/booksAdd a new bookDELETE/api/books/{id}Delete a book by IDSample POST Request:Bashcurl -X POST "http://localhost:8080/api/books" \
  -H "Content-Type: application/json" \
  -d '{"title": "Clean Code", "author": "Robert Martin", "isbn": "978-0132350884", "publicationYear": 2008}'
Question 2: Student Registration APIBase URL: http://localhost:8080/api/studentsMethodEndpointDescriptionGET/api/students/major/{major}Get all students by majorGET/api/students/filter?gpa={minGpa}Filter students with GPA >= minGpaPOST/api/studentsRegister a new studentPUT/api/students/{studentId}Update student informationQuestion 3: Restaurant Menu APIBase URL: http://localhost:8080/api/menuMethodEndpointDescriptionGET/api/menu/category/{category}Get items by categoryGET/api/menu/available?available=trueGet available itemsPUT/api/menu/{id}/availabilityToggle item availabilityDELETE/api/menu/{id}Remove menu itemQuestion 4: E-Commerce Product APIBase URL: http://localhost:8080/api/productsMethodEndpointDescriptionGET/api/products?page=1&limit=5Paginated productsGET/api/products/price-range?min=100&max=500Filter by price rangePATCH/api/products/{id}/stock?quantity=15Update stock quantityQuestion 5: Task Management APIBase URL: http://localhost:8080/api/tasksMethodEndpointDescriptionGET/api/tasks/status?completed=falseGet pending tasksGET/api/tasks/priority/HIGHGet high priority tasksPATCH/api/tasks/{taskId}/completeMark task as completedBonus Question: User Profile APIBase URL: http://localhost:8080/api/usersMethodEndpointDescriptionGET/api/users/search/username?username=johnSearch by usernameGET/api/users/age-range?minAge=20&maxAge=30Filter by age rangePATCH/api/users/{userId}/activateActivate user profilePATCH/api/users/{userId}/deactivateDeactivate user profileHTTP Status Codes Used200 OK - Successful GET, PUT, or PATCH request.201 Created - Successful POST request (resource created).204 No Content - Successful DELETE request.400 Bad Request - Missing or invalid request parameters.404 Not Found - Resource with the specified ID does not exist.Code Quality Standards✅ Separation of Concerns: Logic divided into model, controller, and util packages.✅ Naming Conventions: Follows standard Java camelCase and PascalCase rules.✅ RESTful Design: Uses proper HTTP verbs (GET, POST, PUT, PATCH, DELETE).✅ Response Wrapping: Consistent API responses using the ApiResponse utility class.Developed by: Murenzi CharlesStudent ID: 27386
