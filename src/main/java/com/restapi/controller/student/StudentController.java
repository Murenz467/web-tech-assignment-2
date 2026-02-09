package com.restapi.controller.student;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.restapi.model.student.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * StudentController for managing student registration and information
 */
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private List<Student> students = new ArrayList<>();
    private Long nextId = 6L;

    // Initialize sample data
    public StudentController() {
        students.add(new Student(1L, "John", "Doe", "john@example.com", "Computer Science", 3.8));
        students.add(new Student(2L, "Jane", "Smith", "jane@example.com", "Computer Science", 3.9));
        students.add(new Student(3L, "Bob", "Johnson", "bob@example.com", "Mathematics", 3.5));
        students.add(new Student(4L, "Alice", "Brown", "alice@example.com", "Physics", 3.6));
        students.add(new Student(5L, "Charlie", "Davis", "charlie@example.com", "Computer Science", 3.4));
    }

    /**
     * GET /api/students - Get all students
     */
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(students);
    }

    /**
     * GET /api/students/{studentId} - Get student by ID
     */
    @GetMapping("/{studentId}")
    public ResponseEntity<?> getStudentById(@PathVariable Long studentId) {
        return students.stream()
                .filter(student -> student.getStudentId().equals(studentId))
                .findFirst()
                .map(student -> ResponseEntity.ok((Object) student))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Student with ID " + studentId + " not found"));
    }

    /**
     * GET /api/students/major/{major} - Get all students by major
     */
    @GetMapping("/major/{major}")
    public ResponseEntity<List<Student>> getStudentsByMajor(@PathVariable String major) {
        List<Student> results = students.stream()
                .filter(student -> student.getMajor().equalsIgnoreCase(major))
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(results);
        }
        return ResponseEntity.ok(results);
    }

    /**
     * GET /api/students/filter - Filter students with GPA greater than or equal to minimum
     */
    @GetMapping("/filter")
    public ResponseEntity<List<Student>> filterStudentsByGpa(@RequestParam Double gpa) {
        List<Student> results = students.stream()
                .filter(student -> student.getGpa() >= gpa)
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(results);
        }
        return ResponseEntity.ok(results);
    }

    /**
     * POST /api/students - Register a new student
     */
    @PostMapping
    public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
        student.setStudentId(nextId++);
        students.add(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    /**
     * PUT /api/students/{studentId} - Update student information
     */
    @PutMapping("/{studentId}")
    public ResponseEntity<?> updateStudent(@PathVariable Long studentId, @RequestBody Student updatedStudent) {
        return students.stream()
                .filter(student -> student.getStudentId().equals(studentId))
                .findFirst()
                .map(student -> {
                    student.setFirstName(updatedStudent.getFirstName());
                    student.setLastName(updatedStudent.getLastName());
                    student.setEmail(updatedStudent.getEmail());
                    student.setMajor(updatedStudent.getMajor());
                    student.setGpa(updatedStudent.getGpa());
                    return ResponseEntity.ok((Object) student);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Student with ID " + studentId + " not found"));
    }
}
