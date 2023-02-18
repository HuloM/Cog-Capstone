package com.example.capstoneproject.controller;

import com.example.capstoneproject.entity.AuthRequest;
import com.example.capstoneproject.entity.JWTToken;
import com.example.capstoneproject.entity.Response;
import com.example.capstoneproject.entity.User;
import com.example.capstoneproject.service.interfaces.UserService;
import com.example.capstoneproject.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public Response Home() {
        return new Response("Welcome to the home page", 1, 200);
    }

    @PostMapping("/adduser")
    public Response AddUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.AddUser(user);
        return new Response("Successfully registered", user, 201);
    }

    @PutMapping("/updateuser")
    public Response UpdateUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.updateUser(user);
        return new Response("Successfully updated user", user, 201);
    }

    @PostMapping("/getByLogin")
    public Response Login(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            return new Response("inavalid username/password", new Object(), 401);
        }
        return new Response("Successfully logged in",
                new JWTToken(jwtUtil.generateToken(authRequest.getUserName())), 201);
    }
}
