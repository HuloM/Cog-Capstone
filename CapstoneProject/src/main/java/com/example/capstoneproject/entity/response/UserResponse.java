package com.example.capstoneproject.entity.response;

import com.example.capstoneproject.entity.User;

public class UserResponse {
    private int id;
    private String name;
    private String username;
    private String email;
    private String userType;

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.userType = user.getUserType();
    }
}
