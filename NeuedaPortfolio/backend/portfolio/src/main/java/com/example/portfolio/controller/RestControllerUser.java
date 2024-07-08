package com.example.portfolio.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.portfolio.objects.User;
import com.example.portfolio.service.FirebaseService;

//import java.util.HashMap;
//import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@CrossOrigin("*")
public class RestControllerUser {

    @Autowired
    FirebaseService firebaseService;

    @PostMapping("/saveUserDetails")
    public String saveUserDetails(@RequestBody User user) throws InterruptedException, ExecutionException {
        
          return firebaseService.saveUserDetails(user);
         
    }

    @GetMapping("/getUserDetails")
    public User getUserDetails(@RequestHeader() String name) throws InterruptedException, ExecutionException {
        return firebaseService.getUserDetails(name);
    }
    
    @PutMapping("/updateUserDetails") 
    public String updateUserDetails(@RequestBody User user) throws InterruptedException, ExecutionException{
        
        return firebaseService.updateUserDetails(user);
    }

    @DeleteMapping("/deleteUserDetails")
    public String deleteUserDetails(@RequestHeader String name)
    {
        return firebaseService.deleteUserDetails(name);
    }
}
