package org.launchcode.BingeBuddy.controller;


import org.launchcode.BingeBuddy.data.UserEntityRepository;
import org.launchcode.BingeBuddy.model.*;
import org.launchcode.BingeBuddy.service.AuthService;
import org.launchcode.BingeBuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    public UserController(UserService userAuthService, AuthService authService) {
        this.userService = userAuthService;
        this.authService = authService;
    }

    public UserService userService;

    private final AuthService authService;

    // http://localhost:8080/register
    @PostMapping("/register")
    public ResponseEntity<User> registerNewUser(@RequestBody User user) {

        Optional<User> optionalUser = Optional.ofNullable(userEntityRepository.findByUsername(user.getUsername()));

        if (optionalUser.isEmpty()) {
            User newUser = userService.addUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } else {
            System.out.println("User doesn't exist");
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }
    }

//    // http://localhost:8080/login
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
//        try {
//            boolean isAuthenticated = userService.authenticate(
//                    loginRequest.getUsername(),
//                    loginRequest.getPassword());
//
//            if (isAuthenticated) {
//                return ResponseEntity.ok("Login was successful!");
//            } else {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unknown error occurred");
//        }
//    }


    @PostMapping("/loginjwt")
    public ResponseEntity<?> login(@RequestBody JwtRequest authRequest) {
        JwtResponse authResponse = authService.authenticate(authRequest);

        if (authResponse.getUser() == null || authResponse.getToken() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid username or password"));
        }

        return ResponseEntity.ok(authResponse);
    }


    // http://localhost:8080/user-profile/{userId}
    @GetMapping("user-profile/{userId}")
    public ResponseEntity<User> getUserProfileByID(@PathVariable Integer userId) {

        Optional<User> optionalUser = userEntityRepository.findById(userId);
        User newUser = userService.getUser(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }


    @PostMapping("/userdetails")
    public ResponseEntity<String> addUserDetails(@RequestParam Integer userId,
                                                 @RequestParam(required = false) String genre,
                                                 @RequestParam(required = false) String anotherGenre) {

        Optional<User> userOptional = userEntityRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found.");
        }

        User user = userOptional.get();
        if (genre != null) user.setGenre(genre);
        if (anotherGenre != null) user.setAnotherGenre(anotherGenre);

        userEntityRepository.save(user);

        return ResponseEntity.ok("User details updated successfully.");
    }

//    @GetMapping("/search/{userId}")
//    public ResponseEntity<List<User>> getUserById(@PathVariable String userId) {
//        List<User> searchUser = userEntityRepository.findAll();
//        List<User> users = new ArrayList<>();
//
//
//
//        return ResponseEntity.ok(user);
//    }
@GetMapping("/search/{searchTerm}") // Changed to searchTerm for clarity
public ResponseEntity<List<User>> searchUsers(@PathVariable String searchTerm) {

    List<User> searchUser = userEntityRepository.findAll();

    if (searchTerm == null || searchTerm.isEmpty()) {
        return ResponseEntity.ok(searchUser);
    }

//    for (User user : searchUser) {
//        if (user.getUsername().toLowerCase().contains(searchTerm.toLowerCase())) {
//            users.add(user);
//        }
//    }

    List<User> users = userEntityRepository.findByUsernameContaining(searchTerm);

    return ResponseEntity.ok(users);
}

//    @GetMapping("/search{userName}")
//    public ResponseEntity<List>User>> getUsers (@PathVariable String searchTerm){
//        List<User> users = userEntityRepository.findAll();
//        List<User> searchUsers = new ArrayList<>();
//
//        return R
//    }



    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userEntities = userEntityRepository.findAll();
        return ResponseEntity.ok(userEntities);
    }


    @PostMapping("/add-test-user")
    public ResponseEntity<String> addTestUser() {
        Optional<User> existingUser = userEntityRepository.findById(10);
        if (existingUser.isEmpty()) {
            User testUser = new User();
            testUser.setEmail("test@example.com");
            testUser.setUsername("testuser");
            testUser.setFirstName("Test");
            testUser.setLastName("User");
            testUser.setGenre("Action");
            testUser.setAnotherGenre("Comedy");

            userEntityRepository.save(testUser);
            return ResponseEntity.ok("Test user added with ID " + testUser.getId());
        } else {
            return ResponseEntity.ok("Test user already exists at ID 10");
        }
    }



    @PutMapping("update/{userId}")
    public ResponseEntity<User> updateUserDetails(@PathVariable Integer userId,
                                                  @RequestParam(required = false) String email,
                                                  @RequestParam(required = false) String username,
                                                  @RequestParam(required = false) String firstName,
                                                  @RequestParam(required = false) String lastName,
                                                  @RequestParam(required = false) String genre,
                                                  @RequestParam(required = false) String anotherGenre) {
        Optional<User> existingUser = userEntityRepository.findById(userId);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            if (email != null) user.setEmail(email);
            if (username != null) user.setUsername(username);
            if (firstName != null) user.setFirstName(firstName);
            if (lastName != null) user.setLastName(lastName);
            if (genre != null) user.setGenre(genre);
            if (anotherGenre != null) user.setAnotherGenre(anotherGenre);

            userEntityRepository.save(user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @DeleteMapping("/delete")
    public ResponseEntity<User> deleteUserById(@PathVariable Integer userId) {
        Optional<User> user = userEntityRepository.findById(userId);
        if (user.isPresent()) {
            userEntityRepository.delete(user.get());
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.notFound().build();
    }



//    // // Gives a WebSecurity Exception
//    @GetMapping("/search")
//    public ResponseEntity<?> searchUsersByUsername(@RequestParam String username) {
//        List<User> users = userEntityRepository.findByUsernameContaining(username);
//
//        if (users.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users found.");
//        }
//
//        return ResponseEntity.ok(users);
//    }

}

