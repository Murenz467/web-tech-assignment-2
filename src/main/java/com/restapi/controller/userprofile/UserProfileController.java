package com.restapi.controller.userprofile;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.restapi.model.userprofile.UserProfile;
import com.restapi.util.ApiResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * UserProfileController for managing user profiles
 */
@RestController
@RequestMapping("/api/users")
public class UserProfileController {

    private List<UserProfile> userProfiles = new ArrayList<>();
    private Long nextId = 6L;

    // Initialize sample data
    public UserProfileController() {
        userProfiles.add(new UserProfile(1L, "john_doe", "john@example.com", "John Doe", 28, "USA", "Software Developer passionate about coding", true));
        userProfiles.add(new UserProfile(2L, "jane_smith", "jane@example.com", "Jane Smith", 32, "Canada", "Data Scientist and AI enthusiast", true));
        userProfiles.add(new UserProfile(3L, "bob_wilson", "bob@example.com", "Bob Wilson", 45, "USA", "Project Manager with 15 years experience", true));
        userProfiles.add(new UserProfile(4L, "alice_brown", "alice@example.com", "Alice Brown", 26, "UK", "Full-stack developer", false));
        userProfiles.add(new UserProfile(5L, "charlie_davis", "charlie@example.com", "Charlie Davis", 35, "Australia", "DevOps Engineer", true));
    }

    /**
     * GET /api/users - Get all user profiles
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserProfile>>> getAllUsers() {
        ApiResponse<List<UserProfile>> response = new ApiResponse<>(true, "Users retrieved successfully", userProfiles);
        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/users/{userId} - Get user profile by ID
     */
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<?>> getUserById(@PathVariable Long userId) {
        return userProfiles.stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst()
                .map(user -> {
                    ApiResponse<UserProfile> response = new ApiResponse<>(true, "User retrieved successfully", user);
                    return ResponseEntity.ok((ResponseEntity<ApiResponse<?>>) (Object) ResponseEntity.ok(response));
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false, "User with ID " + userId + " not found", null)));
    }

    /**
     * GET /api/users/search/username - Search by username
     */
    @GetMapping("/search/username")
    public ResponseEntity<ApiResponse<UserProfile>> searchByUsername(@RequestParam String username) {
        return userProfiles.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .map(user -> {
                    ApiResponse<UserProfile> response = new ApiResponse<>(true, "User found", user);
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false, "User with username " + username + " not found", null)));
    }

    /**
     * GET /api/users/country/{country} - Get users by country
     */
    @GetMapping("/country/{country}")
    public ResponseEntity<ApiResponse<List<UserProfile>>> getUsersByCountry(@PathVariable String country) {
        List<UserProfile> results = userProfiles.stream()
                .filter(user -> user.getCountry().equalsIgnoreCase(country))
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "No users found in " + country, null));
        }
        ApiResponse<List<UserProfile>> response = new ApiResponse<>(true, "Users from " + country + " retrieved successfully", results);
        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/users/age-range - Get users by age range
     */
    @GetMapping("/age-range")
    public ResponseEntity<ApiResponse<List<UserProfile>>> getUsersByAgeRange(
            @RequestParam int minAge,
            @RequestParam int maxAge) {
        List<UserProfile> results = userProfiles.stream()
                .filter(user -> user.getAge() >= minAge && user.getAge() <= maxAge)
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "No users found in age range " + minAge + "-" + maxAge, null));
        }
        ApiResponse<List<UserProfile>> response = new ApiResponse<>(true, "Users in age range retrieved successfully", results);
        return ResponseEntity.ok(response);
    }

    /**
     * POST /api/users - Create new user profile
     */
    @PostMapping
    public ResponseEntity<ApiResponse<UserProfile>> createUser(@RequestBody UserProfile userProfile) {
        userProfile.setUserId(nextId++);
        userProfile.setActive(true);
        userProfiles.add(userProfile);
        ApiResponse<UserProfile> response = new ApiResponse<>(true, "User profile created successfully", userProfile);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * PUT /api/users/{userId} - Update user profile
     */
    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<?>> updateUser(@PathVariable Long userId, @RequestBody UserProfile updatedUser) {
        return userProfiles.stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst()
                .map(user -> {
                    user.setUsername(updatedUser.getUsername());
                    user.setEmail(updatedUser.getEmail());
                    user.setFullName(updatedUser.getFullName());
                    user.setAge(updatedUser.getAge());
                    user.setCountry(updatedUser.getCountry());
                    user.setBio(updatedUser.getBio());
                    ApiResponse<UserProfile> response = new ApiResponse<>(true, "User profile updated successfully", user);
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false, "User with ID " + userId + " not found", null)));
    }

    /**
     * PATCH /api/users/{userId}/activate - Activate user profile
     */
    @PatchMapping("/{userId}/activate")
    public ResponseEntity<ApiResponse<?>> activateUser(@PathVariable Long userId) {
        return userProfiles.stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst()
                .map(user -> {
                    user.setActive(true);
                    ApiResponse<UserProfile> response = new ApiResponse<>(true, "User profile activated successfully", user);
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false, "User with ID " + userId + " not found", null)));
    }

    /**
     * PATCH /api/users/{userId}/deactivate - Deactivate user profile
     */
    @PatchMapping("/{userId}/deactivate")
    public ResponseEntity<ApiResponse<?>> deactivateUser(@PathVariable Long userId) {
        return userProfiles.stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst()
                .map(user -> {
                    user.setActive(false);
                    ApiResponse<UserProfile> response = new ApiResponse<>(true, "User profile deactivated successfully", user);
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false, "User with ID " + userId + " not found", null)));
    }

    /**
     * DELETE /api/users/{userId} - Delete user profile
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<?>> deleteUser(@PathVariable Long userId) {
        boolean removed = userProfiles.removeIf(user -> user.getUserId().equals(userId));
        if (removed) {
            ApiResponse<?> response = new ApiResponse<>(true, "User profile deleted successfully", null);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, "User with ID " + userId + " not found", null));
    }
}
