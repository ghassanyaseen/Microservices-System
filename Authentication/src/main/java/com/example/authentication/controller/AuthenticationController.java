package com.example.authentication.controller;

import com.example.authentication.Model.*;
import com.example.authentication.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationservice;

    @PostMapping("/login")
    public ResponseEntity<Boolean> authenticate(@RequestBody Users user) {
        boolean isValid = authenticationservice.checkUser(user.getUsername(), user.getPassword());
        return ResponseEntity.ok(isValid);
    }
}


