package com.example.capstoneproject.controller;

import com.example.capstoneproject.entity.AuthRequest;
import com.example.capstoneproject.entity.dto.UserRegisterDTO;
import com.example.capstoneproject.entity.response.JWTTokenResponse;
import com.example.capstoneproject.entity.response.Response;
import com.example.capstoneproject.entity.User;
import com.example.capstoneproject.entity.response.UserResponse;
import com.example.capstoneproject.service.interfaces.UserService;
import com.example.capstoneproject.util.JWTUtil;
import com.example.capstoneproject.util.SessionUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private SessionUserUtil sessionUserUtil = new SessionUserUtil();

    @GetMapping("/")
    public Response home() {
        // -1 value just means there is no data to return
        return new Response("Welcome to the home page", -1, HttpStatus.OK.value());
    }

    @PostMapping("/register")
    public Response registerUser(@RequestBody UserRegisterDTO dto) {
        // Check if user already exists in the database
        if(userService.getUserByUsername(dto.getUsername()) != null)
            return new Response("User already exists", -1, HttpStatus.BAD_REQUEST.value());

        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        User user = new User(dto);

        user.setUserType("user");

        userService.AddUser(user);

        // returns the user object without the password field on successful registration
        return new Response("Successfully registered", new UserResponse(user), HttpStatus.CREATED.value());
    }
    @PostMapping("/registerAdmin")
    public Response registerAdmin(@RequestBody UserRegisterDTO dto) {
        User sessionUser = userService.getUserByUsername(sessionUserUtil.getSessionUser());

        if(!sessionUser.getUserType().equals("admin")) {
            return new Response("You are not authorized to register an admin", -1, HttpStatus.UNAUTHORIZED.value());
        }

        // Check if user already exists in the database
        if(userService.getUserByUsername(dto.getUsername()) != null)
            return new Response("User already exists", -1, HttpStatus.BAD_REQUEST.value());

        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        User user = new User(dto);
        user.setUserType("admin");

        userService.AddUser(user);

        // returns the user object without the password field on successful registration
        return new Response("Successfully registered New Admin", new UserResponse(user), HttpStatus.CREATED.value());
    }

    @PutMapping("/update")
    public Response updateUser(@RequestBody User user) {
        // Check if currently authenticated user is the same as the user being updated

        if (!Objects.equals(sessionUserUtil.getSessionUser(), user.getUsername()))
            return new Response("You are not authorized to update this user", -1, HttpStatus.UNAUTHORIZED.value());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.updateUser(user);

        // returns the user object without the password field on successful update
        return new Response("Successfully updated user", new UserResponse(user), HttpStatus.CREATED.value());
    }

    @PostMapping("/authenticate")
    public Response authenticateUser(@RequestBody AuthRequest authRequest) {
        System.out.println(authRequest);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            // response if invalid username/password
            return new Response("invalid username/password", -1, HttpStatus.UNAUTHORIZED.value());
        }
        User user = userService.getUserByUsername(authRequest.getUsername());
        return new Response("Successfully logged in",
                new JWTTokenResponse(jwtUtil.generateToken(authRequest.getUsername()), user.getUsername(), user.getUserType()), HttpStatus.OK.value());
    }
}
