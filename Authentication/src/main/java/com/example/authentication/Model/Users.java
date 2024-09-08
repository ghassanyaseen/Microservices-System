package com.example.authentication.Model;

import lombok.Getter;

@Getter
public class Users {
    String username;
    String password;

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
