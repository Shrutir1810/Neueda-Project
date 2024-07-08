package com.example.portfolio.service;

import org.springframework.stereotype.Service;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

@Service
public class FirebaseAuthenticationService {

    public UserRecord getUserByEmail(String email) throws FirebaseAuthException {
        return FirebaseAuth.getInstance().getUserByEmail(email);
    }

    public UserRecord createUser(String email, String password) throws FirebaseAuthException {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
            .setEmail(email)
            .setPassword(password);
        

        return FirebaseAuth.getInstance().createUser(request);
    }

    public UserRecord updateUser(String uid, UserRecord.UpdateRequest request) throws FirebaseAuthException {
        return FirebaseAuth.getInstance().updateUser(request);
    }

    public void deleteUser(String uid) throws FirebaseAuthException {
        FirebaseAuth.getInstance().deleteUser(uid);
    }

    // Additional methods for handling authentication, password reset, etc.
}

