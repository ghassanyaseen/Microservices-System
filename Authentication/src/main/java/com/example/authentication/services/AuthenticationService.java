package com.example.authentication.services;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthenticationService {

    public boolean checkUser(String userName, String password) {
        return (Objects.equals(userName, "ghassan") && Objects.equals(password, "123456$")
                || Objects.equals(userName, "tareq") && Objects.equals(password, "123456$"));
    }
}






