package com.example.capstoneproject.service;

import com.example.capstoneproject.entity.User;
import com.example.capstoneproject.repository.UserRepository;
import com.example.capstoneproject.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Override
    public User AddUser(User user) {
        return repository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) repository.findAll();
    }

    @Override
    public List<User> getAllByUserType(String userType) {
        return repository.findAllByUserType(userType);
    }

    @Override
    public User getUserById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(User user) {
        return repository.save(user);
    }

    @Override
    public String deleteUser(int id) {
        repository.deleteById(id);
        return "User removed" + id;
    }
}
