package com.example.showdata.Model;

import lombok.Getter;

@Getter
public class Users {
    String username;
    String password;

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "user name: " + username;
    }

}
