package com.example.portfolio.controller;

import com.example.portfolio.objects.User;
import com.example.portfolio.service.FirebaseAuthenticationService;
import com.example.portfolio.service.FirebaseService;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final FirebaseAuthenticationService authService;
    private final FirebaseService firebaseService;

    @Autowired
    public AuthController(FirebaseAuthenticationService authService, FirebaseService firebaseService) {
        this.authService = authService;
        this.firebaseService = firebaseService;
    }

    @PostMapping("/login")
    public ResponseEntity<User> authenticateUser(@RequestParam String email) {
        try {
            // Fetch user details from Firestore
            User userDetails = firebaseService.getUserDetails(email);

            if (userDetails != null) {
                return ResponseEntity.ok(userDetails);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (InterruptedException | ExecutionException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/users/{email}")
    public ResponseEntity<UserRecord> getUserByEmail(@PathVariable String email) {
        try {
            UserRecord userRecord = authService.getUserByEmail(email);
            return ResponseEntity.ok(userRecord);
        } catch (FirebaseAuthException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Or handle error as needed
        }
    }

    @PostMapping("/users")
    public ResponseEntity<UserRecord> createUser(@RequestParam String email, @RequestParam String password) {
        try {
            UserRecord userRecord = authService.createUser(email, password);
            return ResponseEntity.status(HttpStatus.CREATED).body(userRecord);
        } catch (FirebaseAuthException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Or handle error as needed
        }
    }

    @DeleteMapping("/users/{uid}")
    public ResponseEntity<Void> deleteUser(@PathVariable String uid) {
        try {
            authService.deleteUser(uid);
            return ResponseEntity.noContent().build();
        } catch (FirebaseAuthException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Or handle error as needed
        }
    }
}
