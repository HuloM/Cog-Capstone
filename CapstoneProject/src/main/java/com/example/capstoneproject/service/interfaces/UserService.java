package com.example.capstoneproject.service.interfaces;

import com.example.capstoneproject.entity.User;

import java.util.List;

public interface UserService {
    User AddUser(User user);
    List<User> getAllUsers();
    List<User> getAllByUserType(String userType);
    User getUserById(int id);
    User updateUser(User user);
    String deleteUser(int id);
}
